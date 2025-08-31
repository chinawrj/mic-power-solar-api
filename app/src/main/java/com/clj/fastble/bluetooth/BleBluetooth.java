package com.clj.fastble.bluetooth;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleIndicateCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.callback.BleRssiCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleConnectStateParameter;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleMsg;
import com.clj.fastble.exception.ConnectException;
import com.clj.fastble.exception.OtherException;
import com.clj.fastble.exception.TimeoutException;
import com.clj.fastble.utils.BleLog;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class BleBluetooth {
    private final BleDevice bleDevice;
    private BleGattCallback bleGattCallback;
    private BleMtuChangedCallback bleMtuChangedCallback;
    private BleRssiCallback bleRssiCallback;
    private BluetoothGatt bluetoothGatt;
    private LastState lastState;
    private final HashMap<String, BleNotifyCallback> bleNotifyCallbackHashMap = new HashMap<>();
    private final HashMap<String, BleIndicateCallback> bleIndicateCallbackHashMap = new HashMap<>();
    private final HashMap<String, BleWriteCallback> bleWriteCallbackHashMap = new HashMap<>();
    private final HashMap<String, BleReadCallback> bleReadCallbackHashMap = new HashMap<>();
    private boolean isActiveDisconnect = false;
    private final MainHandler mainHandler = new MainHandler(Looper.getMainLooper());
    private int connectRetryCount = 0;
    private BluetoothGattCallback coreGattCallback = new BluetoothGattCallback() { // from class: com.clj.fastble.bluetooth.BleBluetooth.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            BleLog.i("BluetoothGattCallback：onConnectionStateChange \nstatus: " + i + "\nnewState: " + i2 + "\ncurrentThread: " + Thread.currentThread().getId());
            BleBluetooth.this.bluetoothGatt = bluetoothGatt;
            BleBluetooth.this.mainHandler.removeMessages(7);
            if (i2 == 2) {
                Message messageObtainMessage = BleBluetooth.this.mainHandler.obtainMessage();
                messageObtainMessage.what = 4;
                BleBluetooth.this.mainHandler.sendMessageDelayed(messageObtainMessage, 500L);
                return;
            }
            if (i2 == 0) {
                if (BleBluetooth.this.lastState == LastState.CONNECT_CONNECTING) {
                    Message messageObtainMessage2 = BleBluetooth.this.mainHandler.obtainMessage();
                    messageObtainMessage2.what = 1;
                    messageObtainMessage2.obj = new BleConnectStateParameter(i);
                    BleBluetooth.this.mainHandler.sendMessage(messageObtainMessage2);
                    return;
                }
                if (BleBluetooth.this.lastState == LastState.CONNECT_CONNECTED) {
                    Message messageObtainMessage3 = BleBluetooth.this.mainHandler.obtainMessage();
                    messageObtainMessage3.what = 2;
                    BleConnectStateParameter bleConnectStateParameter = new BleConnectStateParameter(i);
                    bleConnectStateParameter.setActive(BleBluetooth.this.isActiveDisconnect);
                    messageObtainMessage3.obj = bleConnectStateParameter;
                    BleBluetooth.this.mainHandler.sendMessage(messageObtainMessage3);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            BleLog.i("BluetoothGattCallback：onServicesDiscovered \nstatus: " + i + "\ncurrentThread: " + Thread.currentThread().getId());
            BleBluetooth.this.bluetoothGatt = bluetoothGatt;
            if (i == 0) {
                Message messageObtainMessage = BleBluetooth.this.mainHandler.obtainMessage();
                messageObtainMessage.what = 6;
                messageObtainMessage.obj = new BleConnectStateParameter(i);
                BleBluetooth.this.mainHandler.sendMessage(messageObtainMessage);
                return;
            }
            Message messageObtainMessage2 = BleBluetooth.this.mainHandler.obtainMessage();
            messageObtainMessage2.what = 5;
            BleBluetooth.this.mainHandler.sendMessage(messageObtainMessage2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            Handler handler;
            Handler handler2;
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            Iterator it = BleBluetooth.this.bleNotifyCallbackHashMap.entrySet().iterator();
            while (it.hasNext()) {
                Object value = ((Map.Entry) it.next()).getValue();
                if (value instanceof BleNotifyCallback) {
                    BleNotifyCallback bleNotifyCallback = (BleNotifyCallback) value;
                    if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(bleNotifyCallback.getKey()) && (handler2 = bleNotifyCallback.getHandler()) != null) {
                        Message messageObtainMessage = handler2.obtainMessage();
                        messageObtainMessage.what = 19;
                        messageObtainMessage.obj = bleNotifyCallback;
                        Bundle bundle = new Bundle();
                        bundle.putByteArray(BleMsg.KEY_NOTIFY_BUNDLE_VALUE, bluetoothGattCharacteristic.getValue());
                        messageObtainMessage.setData(bundle);
                        handler2.sendMessage(messageObtainMessage);
                    }
                }
            }
            Iterator it2 = BleBluetooth.this.bleIndicateCallbackHashMap.entrySet().iterator();
            while (it2.hasNext()) {
                Object value2 = ((Map.Entry) it2.next()).getValue();
                if (value2 instanceof BleIndicateCallback) {
                    BleIndicateCallback bleIndicateCallback = (BleIndicateCallback) value2;
                    if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(bleIndicateCallback.getKey()) && (handler = bleIndicateCallback.getHandler()) != null) {
                        Message messageObtainMessage2 = handler.obtainMessage();
                        messageObtainMessage2.what = 35;
                        messageObtainMessage2.obj = bleIndicateCallback;
                        Bundle bundle2 = new Bundle();
                        bundle2.putByteArray(BleMsg.KEY_INDICATE_BUNDLE_VALUE, bluetoothGattCharacteristic.getValue());
                        messageObtainMessage2.setData(bundle2);
                        handler.sendMessage(messageObtainMessage2);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            Handler handler;
            Handler handler2;
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            Iterator it = BleBluetooth.this.bleNotifyCallbackHashMap.entrySet().iterator();
            while (it.hasNext()) {
                Object value = ((Map.Entry) it.next()).getValue();
                if (value instanceof BleNotifyCallback) {
                    BleNotifyCallback bleNotifyCallback = (BleNotifyCallback) value;
                    if (bluetoothGattDescriptor.getCharacteristic().getUuid().toString().equalsIgnoreCase(bleNotifyCallback.getKey()) && (handler2 = bleNotifyCallback.getHandler()) != null) {
                        Message messageObtainMessage = handler2.obtainMessage();
                        messageObtainMessage.what = 18;
                        messageObtainMessage.obj = bleNotifyCallback;
                        Bundle bundle = new Bundle();
                        bundle.putInt(BleMsg.KEY_NOTIFY_BUNDLE_STATUS, i);
                        messageObtainMessage.setData(bundle);
                        handler2.sendMessage(messageObtainMessage);
                    }
                }
            }
            Iterator it2 = BleBluetooth.this.bleIndicateCallbackHashMap.entrySet().iterator();
            while (it2.hasNext()) {
                Object value2 = ((Map.Entry) it2.next()).getValue();
                if (value2 instanceof BleIndicateCallback) {
                    BleIndicateCallback bleIndicateCallback = (BleIndicateCallback) value2;
                    if (bluetoothGattDescriptor.getCharacteristic().getUuid().toString().equalsIgnoreCase(bleIndicateCallback.getKey()) && (handler = bleIndicateCallback.getHandler()) != null) {
                        Message messageObtainMessage2 = handler.obtainMessage();
                        messageObtainMessage2.what = 34;
                        messageObtainMessage2.obj = bleIndicateCallback;
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt(BleMsg.KEY_INDICATE_BUNDLE_STATUS, i);
                        messageObtainMessage2.setData(bundle2);
                        handler.sendMessage(messageObtainMessage2);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            Handler handler;
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            Iterator it = BleBluetooth.this.bleWriteCallbackHashMap.entrySet().iterator();
            while (it.hasNext()) {
                Object value = ((Map.Entry) it.next()).getValue();
                if (value instanceof BleWriteCallback) {
                    BleWriteCallback bleWriteCallback = (BleWriteCallback) value;
                    if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(bleWriteCallback.getKey()) && (handler = bleWriteCallback.getHandler()) != null) {
                        Message messageObtainMessage = handler.obtainMessage();
                        messageObtainMessage.what = 50;
                        messageObtainMessage.obj = bleWriteCallback;
                        Bundle bundle = new Bundle();
                        bundle.putInt(BleMsg.KEY_WRITE_BUNDLE_STATUS, i);
                        bundle.putByteArray(BleMsg.KEY_WRITE_BUNDLE_VALUE, bluetoothGattCharacteristic.getValue());
                        messageObtainMessage.setData(bundle);
                        handler.sendMessage(messageObtainMessage);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            Handler handler;
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            Iterator it = BleBluetooth.this.bleReadCallbackHashMap.entrySet().iterator();
            while (it.hasNext()) {
                Object value = ((Map.Entry) it.next()).getValue();
                if (value instanceof BleReadCallback) {
                    BleReadCallback bleReadCallback = (BleReadCallback) value;
                    if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(bleReadCallback.getKey()) && (handler = bleReadCallback.getHandler()) != null) {
                        Message messageObtainMessage = handler.obtainMessage();
                        messageObtainMessage.what = 66;
                        messageObtainMessage.obj = bleReadCallback;
                        Bundle bundle = new Bundle();
                        bundle.putInt(BleMsg.KEY_READ_BUNDLE_STATUS, i);
                        bundle.putByteArray(BleMsg.KEY_READ_BUNDLE_VALUE, bluetoothGattCharacteristic.getValue());
                        messageObtainMessage.setData(bundle);
                        handler.sendMessage(messageObtainMessage);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            Handler handler;
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            if (BleBluetooth.this.bleRssiCallback == null || (handler = BleBluetooth.this.bleRssiCallback.getHandler()) == null) {
                return;
            }
            Message messageObtainMessage = handler.obtainMessage();
            messageObtainMessage.what = 82;
            messageObtainMessage.obj = BleBluetooth.this.bleRssiCallback;
            Bundle bundle = new Bundle();
            bundle.putInt(BleMsg.KEY_READ_RSSI_BUNDLE_STATUS, i2);
            bundle.putInt(BleMsg.KEY_READ_RSSI_BUNDLE_VALUE, i);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            Handler handler;
            super.onMtuChanged(bluetoothGatt, i, i2);
            if (BleBluetooth.this.bleMtuChangedCallback == null || (handler = BleBluetooth.this.bleMtuChangedCallback.getHandler()) == null) {
                return;
            }
            Message messageObtainMessage = handler.obtainMessage();
            messageObtainMessage.what = 98;
            messageObtainMessage.obj = BleBluetooth.this.bleMtuChangedCallback;
            Bundle bundle = new Bundle();
            bundle.putInt(BleMsg.KEY_SET_MTU_BUNDLE_STATUS, i2);
            bundle.putInt(BleMsg.KEY_SET_MTU_BUNDLE_VALUE, i);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    };

    enum LastState {
        CONNECT_IDLE,
        CONNECT_CONNECTING,
        CONNECT_CONNECTED,
        CONNECT_FAILURE,
        CONNECT_DISCONNECT
    }

    static /* synthetic */ int access$304(BleBluetooth bleBluetooth) {
        int i = bleBluetooth.connectRetryCount + 1;
        bleBluetooth.connectRetryCount = i;
        return i;
    }

    public BleBluetooth(BleDevice bleDevice) {
        this.bleDevice = bleDevice;
    }

    public BleConnector newBleConnector() {
        return new BleConnector(this);
    }

    public synchronized void addConnectGattCallback(BleGattCallback bleGattCallback) {
        this.bleGattCallback = bleGattCallback;
    }

    public synchronized void removeConnectGattCallback() {
        this.bleGattCallback = null;
    }

    public synchronized void addNotifyCallback(String str, BleNotifyCallback bleNotifyCallback) {
        this.bleNotifyCallbackHashMap.put(str, bleNotifyCallback);
    }

    public synchronized void addIndicateCallback(String str, BleIndicateCallback bleIndicateCallback) {
        this.bleIndicateCallbackHashMap.put(str, bleIndicateCallback);
    }

    public synchronized void addWriteCallback(String str, BleWriteCallback bleWriteCallback) {
        this.bleWriteCallbackHashMap.put(str, bleWriteCallback);
    }

    public synchronized void addReadCallback(String str, BleReadCallback bleReadCallback) {
        this.bleReadCallbackHashMap.put(str, bleReadCallback);
    }

    public synchronized void removeNotifyCallback(String str) {
        if (this.bleNotifyCallbackHashMap.containsKey(str)) {
            this.bleNotifyCallbackHashMap.remove(str);
        }
    }

    public synchronized void removeIndicateCallback(String str) {
        if (this.bleIndicateCallbackHashMap.containsKey(str)) {
            this.bleIndicateCallbackHashMap.remove(str);
        }
    }

    public synchronized void removeWriteCallback(String str) {
        if (this.bleWriteCallbackHashMap.containsKey(str)) {
            this.bleWriteCallbackHashMap.remove(str);
        }
    }

    public synchronized void removeReadCallback(String str) {
        if (this.bleReadCallbackHashMap.containsKey(str)) {
            this.bleReadCallbackHashMap.remove(str);
        }
    }

    public synchronized void clearCharacterCallback() {
        this.bleNotifyCallbackHashMap.clear();
        this.bleIndicateCallbackHashMap.clear();
        this.bleWriteCallbackHashMap.clear();
        this.bleReadCallbackHashMap.clear();
    }

    public synchronized void addRssiCallback(BleRssiCallback bleRssiCallback) {
        this.bleRssiCallback = bleRssiCallback;
    }

    public synchronized void removeRssiCallback() {
        this.bleRssiCallback = null;
    }

    public synchronized void addMtuChangedCallback(BleMtuChangedCallback bleMtuChangedCallback) {
        this.bleMtuChangedCallback = bleMtuChangedCallback;
    }

    public synchronized void removeMtuChangedCallback() {
        this.bleMtuChangedCallback = null;
    }

    public String getDeviceKey() {
        return this.bleDevice.getKey();
    }

    public BleDevice getDevice() {
        return this.bleDevice;
    }

    public BluetoothGatt getBluetoothGatt() {
        return this.bluetoothGatt;
    }

    public synchronized BluetoothGatt connect(BleDevice bleDevice, boolean z, BleGattCallback bleGattCallback) {
        return connect(bleDevice, z, bleGattCallback, 0);
    }

    public synchronized BluetoothGatt connect(BleDevice bleDevice, boolean z, BleGattCallback bleGattCallback, int i) {
        BleLog.i("connect device: " + bleDevice.getName() + "\nmac: " + bleDevice.getMac() + "\nautoConnect: " + z + "\ncurrentThread: " + Thread.currentThread().getId() + "\nconnectCount:" + (i + 1));
        if (i == 0) {
            this.connectRetryCount = 0;
        }
        addConnectGattCallback(bleGattCallback);
        this.lastState = LastState.CONNECT_CONNECTING;
        BluetoothGatt bluetoothGattConnectGatt = bleDevice.getDevice().connectGatt(BleManager.getInstance().getContext(), z, this.coreGattCallback, 2);
        this.bluetoothGatt = bluetoothGattConnectGatt;
        if (bluetoothGattConnectGatt != null) {
            BleGattCallback bleGattCallback2 = this.bleGattCallback;
            if (bleGattCallback2 != null) {
                bleGattCallback2.onStartConnect();
            }
            Message messageObtainMessage = this.mainHandler.obtainMessage();
            messageObtainMessage.what = 7;
            this.mainHandler.sendMessageDelayed(messageObtainMessage, BleManager.getInstance().getConnectOverTime());
        } else {
            disconnectGatt();
            refreshDeviceCache();
            closeBluetoothGatt();
            this.lastState = LastState.CONNECT_FAILURE;
            BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(this);
            BleGattCallback bleGattCallback3 = this.bleGattCallback;
            if (bleGattCallback3 != null) {
                bleGattCallback3.onConnectFail(bleDevice, new OtherException("GATT connect exception occurred!"));
            }
        }
        return this.bluetoothGatt;
    }

    public synchronized void disconnect() {
        this.isActiveDisconnect = true;
        disconnectGatt();
    }

    public synchronized void destroy() {
        this.lastState = LastState.CONNECT_IDLE;
        disconnectGatt();
        refreshDeviceCache();
        closeBluetoothGatt();
        removeConnectGattCallback();
        removeRssiCallback();
        removeMtuChangedCallback();
        clearCharacterCallback();
        this.mainHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void disconnectGatt() {
        BluetoothGatt bluetoothGatt = this.bluetoothGatt;
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void refreshDeviceCache() {
        BluetoothGatt bluetoothGatt;
        try {
            Method method = BluetoothGatt.class.getMethod("refresh", null);
            if (method != null && (bluetoothGatt = this.bluetoothGatt) != null) {
                BleLog.i("refreshDeviceCache, is success:  " + ((Boolean) method.invoke(bluetoothGatt, null)).booleanValue());
            }
        } catch (Exception e) {
            BleLog.i("exception occur while refreshing device: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void closeBluetoothGatt() {
        BluetoothGatt bluetoothGatt = this.bluetoothGatt;
        if (bluetoothGatt != null) {
            bluetoothGatt.close();
        }
    }

    private final class MainHandler extends Handler {
        MainHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    BleBluetooth.this.disconnectGatt();
                    BleBluetooth.this.refreshDeviceCache();
                    BleBluetooth.this.closeBluetoothGatt();
                    if (BleBluetooth.this.connectRetryCount < BleManager.getInstance().getReConnectCount()) {
                        BleLog.e("Connect fail, try reconnect " + BleManager.getInstance().getReConnectInterval() + " millisecond later");
                        BleBluetooth.access$304(BleBluetooth.this);
                        Message messageObtainMessage = BleBluetooth.this.mainHandler.obtainMessage();
                        messageObtainMessage.what = 3;
                        BleBluetooth.this.mainHandler.sendMessageDelayed(messageObtainMessage, BleManager.getInstance().getReConnectInterval());
                        break;
                    } else {
                        BleBluetooth.this.lastState = LastState.CONNECT_FAILURE;
                        BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(BleBluetooth.this);
                        int status = ((BleConnectStateParameter) message.obj).getStatus();
                        if (BleBluetooth.this.bleGattCallback != null) {
                            BleBluetooth.this.bleGattCallback.onConnectFail(BleBluetooth.this.bleDevice, new ConnectException(BleBluetooth.this.bluetoothGatt, status));
                            break;
                        }
                    }
                    break;
                case 2:
                    BleBluetooth.this.lastState = LastState.CONNECT_DISCONNECT;
                    BleManager.getInstance().getMultipleBluetoothController().removeBleBluetooth(BleBluetooth.this);
                    BleBluetooth.this.disconnect();
                    BleBluetooth.this.refreshDeviceCache();
                    BleBluetooth.this.closeBluetoothGatt();
                    BleBluetooth.this.removeRssiCallback();
                    BleBluetooth.this.removeMtuChangedCallback();
                    BleBluetooth.this.clearCharacterCallback();
                    BleBluetooth.this.mainHandler.removeCallbacksAndMessages(null);
                    BleConnectStateParameter bleConnectStateParameter = (BleConnectStateParameter) message.obj;
                    boolean zIsActive = bleConnectStateParameter.isActive();
                    int status2 = bleConnectStateParameter.getStatus();
                    if (BleBluetooth.this.bleGattCallback != null) {
                        BleBluetooth.this.bleGattCallback.onDisConnected(zIsActive, BleBluetooth.this.bleDevice, BleBluetooth.this.bluetoothGatt, status2);
                        break;
                    }
                    break;
                case 3:
                    BleBluetooth bleBluetooth = BleBluetooth.this;
                    bleBluetooth.connect(bleBluetooth.bleDevice, false, BleBluetooth.this.bleGattCallback, BleBluetooth.this.connectRetryCount);
                    break;
                case 4:
                    if (BleBluetooth.this.bluetoothGatt != null) {
                        if (!BleBluetooth.this.bluetoothGatt.discoverServices()) {
                            Message messageObtainMessage2 = BleBluetooth.this.mainHandler.obtainMessage();
                            messageObtainMessage2.what = 5;
                            BleBluetooth.this.mainHandler.sendMessage(messageObtainMessage2);
                            break;
                        }
                    } else {
                        Message messageObtainMessage3 = BleBluetooth.this.mainHandler.obtainMessage();
                        messageObtainMessage3.what = 5;
                        BleBluetooth.this.mainHandler.sendMessage(messageObtainMessage3);
                        break;
                    }
                    break;
                case 5:
                    BleBluetooth.this.disconnectGatt();
                    BleBluetooth.this.refreshDeviceCache();
                    BleBluetooth.this.closeBluetoothGatt();
                    BleBluetooth.this.lastState = LastState.CONNECT_FAILURE;
                    BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(BleBluetooth.this);
                    if (BleBluetooth.this.bleGattCallback != null) {
                        BleBluetooth.this.bleGattCallback.onConnectFail(BleBluetooth.this.bleDevice, new OtherException("GATT discover services exception occurred!"));
                        break;
                    }
                    break;
                case 6:
                    BleBluetooth.this.lastState = LastState.CONNECT_CONNECTED;
                    BleBluetooth.this.isActiveDisconnect = false;
                    BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(BleBluetooth.this);
                    BleManager.getInstance().getMultipleBluetoothController().addBleBluetooth(BleBluetooth.this);
                    int status3 = ((BleConnectStateParameter) message.obj).getStatus();
                    if (BleBluetooth.this.bleGattCallback != null) {
                        BleBluetooth.this.bleGattCallback.onConnectSuccess(BleBluetooth.this.bleDevice, BleBluetooth.this.bluetoothGatt, status3);
                        break;
                    }
                    break;
                case 7:
                    BleBluetooth.this.disconnectGatt();
                    BleBluetooth.this.refreshDeviceCache();
                    BleBluetooth.this.closeBluetoothGatt();
                    BleBluetooth.this.lastState = LastState.CONNECT_FAILURE;
                    BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(BleBluetooth.this);
                    if (BleBluetooth.this.bleGattCallback != null) {
                        BleBluetooth.this.bleGattCallback.onConnectFail(BleBluetooth.this.bleDevice, new TimeoutException());
                        break;
                    }
                    break;
                default:
                    super.handleMessage(message);
                    break;
            }
        }
    }
}
