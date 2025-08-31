BLE protocol reverse-engineering (discovery, connect, UUIDs, framing)

Scope
- Source-proven BLE details from native plugin package com.nky.nkyble
- Verified library: FastBle (com.clj.fastble) for scan/connect/notify/write
- Concrete GATT UUIDs, connection flow, MTU handling, notification, and write modes
- Frame integrity (length + CRC16) and AES-CBC payload handling inferred from code

Discovery and library
- Native modules (registered in assets/dcloud_uniplugins.json):
  - UniPermissionModule, UniBleModule, UniWiFiModule, UniJLBleScanModule; hooksClass com.nky.nkyble.App
- BLE stack uses FastBle APIs:
  - BleManager.init, enableLog, setReConnectCount, setConnectOverTime, setOperateTimeout
  - initScanRule(BleScanRuleConfig), scan(BleScanCallback), connect(BleGattCallback)
  - notify(..., BleNotifyCallback), write(..., BleWriteCallback), setMtu(..., BleMtuChangedCallback)
  - Evidence in code imports: com.clj.fastble.* and BleManager usage
  - Reference: https://github.com/Jasonchenlijian/FastBle (Apache-2.0)
 - Additional native entry: UniJLBleScanModule can launch com.ginlong.near_tool_sdk BleConnectActivity (likely a vendor tool flow) but core BLE logic for this app uses BleClient2/FastBle.

GATT UUIDs (from BleClient2)
- Service UUID: 000000FF-0000-1000-8000-00805f9b34fb
- Characteristics:
  - 0000ff01-0000-1000-8000-00805f9b34fb — notify + write (with response=false in code path sendDataResepone)
  - 0000ff04-0000-1000-8000-00805f9b34fb — write with response and split mode for long payloads (sendDataNoResepone)

Connection sequence (BleClient2)
1) Initialize FastBle and global timeouts:
   - enableLog(true), setReConnectCount(3, WebAppActivity.SPLASH_SECOND), setConnectOverTime(10000), setOperateTimeout(15000)
2) Connect:
   - BleManager.getInstance().connect(bleDevice, BleGattCallback)
   - onConnectSuccess: verify the service 000000FF exists, then proceed to MTU
3) MTU negotiation:
   - Target mtu starts at 500; onSetMTUFailure backs off by 100 until 23, then fails with MTU_SET_FAIL
   - onMtuChanged(mtu): BleManager.setSplitWriteNum(mtu - 3); status set to connected; then enable notify
4) Notification subscribe:
   - notify(service=000000FF..., characteristic=0000ff01...)
   - onCharacteristicChanged: forwards raw bytes to BleRequestCallback.requestResepon

Writing data
- With response path (sendDataResepone):
  - write(service=000000FF..., characteristic=0000ff01..., data, split=false)
- Chunked write path (sendDataNoResepone):
  - write(service=000000FF..., characteristic=0000ff04..., data, split=true, sendNextWhenLastSuccess=true, interval=50ms)
- Disconnect/cleanup:
  - disconnectAllDevice(); destroy();

Framing, integrity, and decryption (DataProManager + CRC16)
- Incoming frame format checks:
  - First two bytes represent total length-2 (big-endian via DatalogApUtil.byte2Int on [0],[1])
  - If a single notification chunk’s length header doesn’t match buffer, code accumulates into receviceData until full
  - CRC16 over bytes [0..len-3] validated against the last two bytes [len-2],[len-1]
    - CRC16 implementation in com.nky.nkyble.util.CRC16; returns 16-bit int; DatalogApUtil.int2Byte used to compare
- AES payload handling:
  - After integrity, aesPase(): copies payload from offset 8 for length (totalLen-10), decrypts via DatalogApUtil.msgDesCodeByAESCBC
  - Header structure around AES:
    - bytes[0..1]: totalLen-2 (big-endian)
    - bytes[2..3]: unknown header fields (likely protocol/version/cmd-group)
    - bytes[4..5]: inner length field (iByte2Int) for post-decryption reassembly
    - bytes[6..7]: possibly sequence/cmd (reserved, not modified in aesPase)
    - bytes[8..N-3]: AES-CBC encrypted payload
    - bytes[N-2..N-1]: CRC16
  - After decrypt, code truncates to iByte2Int-2, reassembles: header[0..7] + clearPayload + original CRC tail

AES-CBC details (AESCBCUtils)
- Cipher: AES/CBC/NoPadding
- Key/IV: 存在于应用本地常量中，建议在公开仓库中避免直接披露具体值；可在有授权的环境中从应用构建中提取。
- Decrypt method: AESCBCUtils.AESDecryption(byte[]) → used by DatalogApUtil.msgDesCodeByAESCBC
- Note: Because NoPadding is used, the encrypted segment length must be a multiple of 16; framing/length fields ensure alignment.

Implications for reverse protocol
- Advertising/scan: app relies on FastBle scan without explicit name/mac filters in the shown snippet; scan rules are set with a 10s timeout (see BleScanManager)
- MTU and throughput: app attempts large MTU (500); sets splitWriteNum = mtu-3, enabling efficient long writes
- Reliability: write-ack path uses ff01; bulk path uses ff04 with packetization at 50ms intervals
- Integrity: CRC16 validates frames; decryption indicates encrypted application-layer payloads (AES-CBC). Keys/IV likely in DatalogApUtil.

Scan record parsing and device filters (BleScanManager)
- Each advertisement TLV parsed; notable types:
  - Type 0xFF (manufacturer specific, shown as -1 in code): treated as a UTF-8 string like "<type>#<suffix>"; before '#' stored as deviceType, after '#' concatenated with name
  - Type 0x09 (Complete Local Name): read as name string
- A device is emitted if:
  - bleName and address are non-empty, and
  - deviceType matches regex: ^[a-zA-Z]:?\d+(?:_\d+_\d+)?$|^\d+(_\d+)*$
- Events to JS (via UniBleModule):
  - scanBle: code 1 start, 2 addBleData (BleBean JSON), 3 fail, 4 finish (BleDevice list JSON)
  - connectBle: 1 start, 2 connected, fail map includes code/message, and -1 disconnected
  - bleSend: onCharacteristicChanged (code 1 + hex data), writeSuccess (code 2 + current/total/justWrite), writeFail (code 3)

Permissions and prerequisites (UniBleModule)
- Requires location + Bluetooth permissions; on Android 12+ uses BLUETOOTH_SCAN/CONNECT
- Ensures GPS is enabled; prompts user to enable Bluetooth if off

What to test on-device
- Discovery: scan 10s; record device name, manufacturer data, and service UUIDs
- Connect + MTU: note agreed MTU value; confirm notify enabled on ff01
- Read/notify: capture raw frames; verify length header and CRC16; attempt AES-CBC decode using DatalogApUtil key/iv if recoverable
- Write: test small command via ff01; test long transfer via ff04 and confirm packetization

Open items
- Locate and document AES key/iv in DatalogApUtil and any command opcodes
- Map ConnectError codes to user messages via ConnectedCodeErrorUtils
- Identify scan filters (names/mac prefixes) in BleScanManager or UniJLBleScanModule if present

Error mapping (ConnectedCodeErrorUtils → ConnectError)
- 301 BLE_CONNECTED: 设备已经连接
- 302 MTU_SET_FAIL: mtu 设置失败
- 303 BLUETOOTHADAPTER_NOT_INITIALIZED: 蓝牙适配器Adapter未初始化
- 304 BLE_CONNECTING: 正在连接中
- 305 SERVICE_NULL: 找不到service
- 306 ERROR_CODE_TIMEOUT: 连接超时
- 307: Gatt 发生异常中断
- 308: 其他错误

References
- com.nky.nkyble.ble.BleClient2 (UUIDs, MTU, notify/write)
- com.nky.nkyble.ble.DataProManager (length + CRC16 + AES-CBC reassembly)
- com.nky.nkyble.util.CRC16 (CRC tables and calcCrc16)
- com.nky.nkyble.util.AESCBCUtils (AES/CBC/NoPadding, key/iv)
- assets/dcloud_uniplugins.json (plugin registration)
- FastBle documentation: https://github.com/Jasonchenlijian/FastBle
