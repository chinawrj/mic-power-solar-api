package com.nky.nkyble.ble;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.nky.nkyble.App;
import com.nky.nkyble.bean.BleBean;
import com.nky.nkyble.bean.BleBrocastPro;
import com.nostra13.dcloudimageloader.core.download.BaseImageDownloader;
import io.dcloud.WebAppActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class BleScanManager {
    private String deviceType;
    private Scanlisteners scanlisteners;
    private BleScanStatus scanStatus = BleScanStatus.SCAN_INIT;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.nky.nkyble.ble.BleScanManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            try {
                BleScanManager.this.parseRecord((BleDevice) message.obj);
            } catch (Exception e) {
                Log.d("liaojinsha", "解析异常");
                e.printStackTrace();
            }
        }
    };

    public interface Scanlisteners {
        void addBleData(BleBean bleBean);

        void scanFail();

        void scanFinish(List<BleDevice> list);

        void scanStart();
    }

    public BleScanManager() {
        BleManager.getInstance().init(App.context);
        BleManager.getInstance().enableLog(true).setReConnectCount(3, WebAppActivity.SPLASH_SECOND).setConnectOverTime(10000L).setOperateTimeout(BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT);
        BleManager.getInstance().initScanRule(new BleScanRuleConfig.Builder().setScanTimeOut(10000L).build());
    }

    public static BleScanManager getInstance() {
        return helper.instance;
    }

    private static class helper {
        public static BleScanManager instance = new BleScanManager();

        private helper() {
        }
    }

    public void startScan(Scanlisteners scanlisteners) {
        if (this.scanStatus == BleScanStatus.SCANNIND) {
            return;
        }
        this.scanlisteners = scanlisteners;
        BleManager.getInstance().scan(new BleScanCallback() { // from class: com.nky.nkyble.ble.BleScanManager.2
            @Override // com.clj.fastble.callback.BleScanCallback
            public void onScanFinished(List<BleDevice> list) {
                try {
                    BleScanManager.this.scanStatus = BleScanStatus.SCAN_INIT;
                    BleScanManager.this.scanlisteners.scanFinish(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanStarted(boolean z) {
                BleScanManager.this.scanlisteners.scanStart();
                BleScanManager.this.scanStatus = BleScanStatus.SCAN_START;
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanning(BleDevice bleDevice) {
                BleScanManager.this.scanStatus = BleScanStatus.SCANNIND;
                Message message = new Message();
                message.obj = bleDevice;
                message.what = 0;
                BleScanManager.this.handler.sendMessage(message);
            }
        });
    }

    public void stoptScan() {
        this.scanStatus = BleScanStatus.SCAN_INIT;
        BleManager.getInstance().cancelScan();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseRecord(BleDevice bleDevice) {
        Scanlisteners scanlisteners;
        int i;
        ArrayList arrayList = new ArrayList();
        byte[] scanRecord = bleDevice.getScanRecord();
        String address = bleDevice.getDevice().getAddress();
        int i2 = 0;
        int i3 = 0;
        while (i2 < scanRecord.length && (i = scanRecord[i2] & 255) != 0) {
            int i4 = i - 1;
            byte[] bArr = new byte[i4];
            BleBrocastPro bleBrocastPro = new BleBrocastPro();
            bleBrocastPro.len = i;
            bleBrocastPro.type = scanRecord[i2 + 1];
            System.arraycopy(scanRecord, i2 + 2, bArr, 0, i4);
            bleBrocastPro.data = bArr;
            arrayList.add(bleBrocastPro);
            i3 += i + 1;
            i2 = i3;
        }
        BleBean bleBean = new BleBean();
        bleBean.setAddress(address);
        String strSubstring = "";
        String str = "";
        int i5 = 0;
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            BleBrocastPro bleBrocastPro2 = (BleBrocastPro) arrayList.get(i6);
            byte b = bleBrocastPro2.type;
            int i7 = bleBrocastPro2.len;
            if (b != -1) {
                if (b == 9) {
                    str = new String(bleBrocastPro2.data);
                }
            } else if (i5 < 1) {
                String str2 = new String(bleBrocastPro2.data);
                this.deviceType = str2;
                int iIndexOf = str2.indexOf("#");
                if (iIndexOf != -1) {
                    this.deviceType = str2.substring(0, iIndexOf);
                    strSubstring = str2.substring(iIndexOf + 1);
                }
                i5++;
            }
        }
        bleBean.setDevice(bleDevice);
        bleBean.setBleName(strSubstring + str);
        bleBean.setType(this.deviceType);
        if (TextUtils.isEmpty(bleBean.getBleName()) || TextUtils.isEmpty(bleBean.getAddress()) || !isValidCollectorType(bleBean.getType()) || (scanlisteners = this.scanlisteners) == null) {
            return;
        }
        scanlisteners.addBleData(bleBean);
    }

    public static boolean isValidCollectorType(String str) {
        Pattern patternCompile = Pattern.compile("^[a-zA-Z]:?\\d+(?:\\_\\d+\\_\\d+)?$|^\\d+(\\_\\d+)*$");
        if (str == null) {
            return false;
        }
        return patternCompile.matcher(str).matches();
    }
}
