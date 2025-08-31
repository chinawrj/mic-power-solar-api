package com.nky.nkyble.ble;

import android.bluetooth.BluetoothGatt;
import android.os.Handler;
import android.util.Log;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.nky.nkyble.App;
import com.nky.nkyble.bean.ConnectError;
import com.nky.nkyble.util.ConnectedCodeErrorUtils;
import com.nostra13.dcloudimageloader.core.download.BaseImageDownloader;
import io.dcloud.WebAppActivity;
import java.util.UUID;

/* loaded from: classes.dex */
public class BleClient2 implements IBle {
    public static final UUID SERVICE_UUID = UUID.fromString("000000FF-0000-1000-8000-00805f9b34fb");
    private static final String TAG = "BleClient2";
    private final String NORESPON_WRITE_ID;
    private final String SERVICE_ID;
    private final String WRITE_ID;
    private BleClientStatus clientStatus;
    public BleConnetLiseners connetLiseners;
    private DataProManager dataProManager;
    public BleRequestCallback liseners;
    private BleDevice mBleDevice;
    private int mtu;

    private BleClient2() {
        this.SERVICE_ID = "000000FF-0000-1000-8000-00805f9b34fb";
        this.WRITE_ID = "0000ff01-0000-1000-8000-00805f9b34fb";
        this.NORESPON_WRITE_ID = "0000ff04-0000-1000-8000-00805f9b34fb";
        this.mtu = 500;
        this.clientStatus = BleClientStatus.BLE_DISCONET;
        this.dataProManager = new DataProManager();
        BleManager.getInstance().init(App.context);
        BleManager.getInstance().enableLog(true).setReConnectCount(3, WebAppActivity.SPLASH_SECOND).setConnectOverTime(10000L).setOperateTimeout(BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT);
    }

    public static BleClient2 getInstance() {
        return BleClientHelper.getInstance;
    }

    private static class BleClientHelper {
        public static BleClient2 getInstance = new BleClient2();

        private BleClientHelper() {
        }
    }

    @Override // com.nky.nkyble.ble.IBle
    public void connet(BleDevice bleDevice, final BleConnetLiseners bleConnetLiseners) {
        if (bleDevice == null) {
            bleConnetLiseners.bleConnectedFail(ConnectedCodeErrorUtils.getErrorByCode(ConnectError.BLUETOOTHADAPTER_NOT_INITIALIZED));
            return;
        }
        if (this.clientStatus == BleClientStatus.BLE_CONNETTING) {
            bleConnetLiseners.bleConnectedFail(ConnectedCodeErrorUtils.getErrorByCode(ConnectError.BLE_CONNECTING));
        } else if (this.clientStatus == BleClientStatus.BLE_CONETED) {
            bleConnetLiseners.bleConnectedFail(ConnectedCodeErrorUtils.getErrorByCode(ConnectError.BLE_CONNECTED));
        } else {
            BleManager.getInstance().connect(bleDevice, new BleGattCallback() { // from class: com.nky.nkyble.ble.BleClient2.1
                @Override // com.clj.fastble.callback.BleGattCallback
                public void onStartConnect() {
                    Log.d(BleClient2.TAG, "on start connect");
                    BleClient2.this.clientStatus = BleClientStatus.BLE_CONNETTING;
                    bleConnetLiseners.bleConnectedStart();
                }

                @Override // com.clj.fastble.callback.BleGattCallback
                public void onConnectFail(BleDevice bleDevice2, BleException bleException) {
                    Log.d(BleClient2.TAG, "on start connect fail");
                    BleClient2.this.clientStatus = BleClientStatus.BLE_DISCONET;
                    switch (bleException.getCode()) {
                        case 100:
                            bleConnetLiseners.bleConnectedFail(ConnectedCodeErrorUtils.getErrorByCode(ConnectError.ERROR_CODE_TIMEOUT));
                            break;
                        case 101:
                            bleConnetLiseners.bleConnectedFail(ConnectedCodeErrorUtils.getErrorByCode(307));
                            break;
                        case 102:
                            bleConnetLiseners.bleConnectedFail(ConnectedCodeErrorUtils.getErrorByCode(308));
                            break;
                    }
                }

                @Override // com.clj.fastble.callback.BleGattCallback
                public void onConnectSuccess(BleDevice bleDevice2, BluetoothGatt bluetoothGatt, int i) {
                    Log.d(BleClient2.TAG, "on start connect success");
                    if (bluetoothGatt.getService(BleClient2.SERVICE_UUID) != null) {
                        BleClient2.this.mBleDevice = bleDevice2;
                        BleClient2.this.setMtu(bleConnetLiseners);
                    } else {
                        bleConnetLiseners.bleConnectedFail(ConnectedCodeErrorUtils.getErrorByCode(ConnectError.SERVICE_NULL));
                    }
                }

                @Override // com.clj.fastble.callback.BleGattCallback
                public void onDisConnected(boolean z, BleDevice bleDevice2, BluetoothGatt bluetoothGatt, int i) {
                    Log.d(BleClient2.TAG, "on start dis connect");
                    BleClient2.this.clientStatus = BleClientStatus.BLE_DISCONET;
                    bleConnetLiseners.bleDisConnected();
                }
            });
        }
    }

    /* renamed from: com.nky.nkyble.ble.BleClient2$2, reason: invalid class name */
    class AnonymousClass2 extends BleMtuChangedCallback {
        final /* synthetic */ BleConnetLiseners val$connetLiseners;

        AnonymousClass2(BleConnetLiseners bleConnetLiseners) {
            this.val$connetLiseners = bleConnetLiseners;
        }

        @Override // com.clj.fastble.callback.BleMtuChangedCallback
        public void onSetMTUFailure(BleException bleException) {
            Log.d(BleClient2.TAG, "on set mtu fail");
            if (BleClient2.this.mtu <= 100) {
                if (BleClient2.this.mtu > 23) {
                    BleClient2.this.mtu = 23;
                    BleClient2.this.setMtu(this.val$connetLiseners);
                    return;
                } else {
                    BleClient2.this.clientStatus = BleClientStatus.BLE_DISCONET;
                    this.val$connetLiseners.bleConnectedFail(ConnectedCodeErrorUtils.getErrorByCode(ConnectError.MTU_SET_FAIL));
                    return;
                }
            }
            BleClient2.this.mtu -= 100;
            BleClient2.this.setMtu(this.val$connetLiseners);
        }

        @Override // com.clj.fastble.callback.BleMtuChangedCallback
        public void onMtuChanged(int i) {
            BleManager.getInstance().setSplitWriteNum(i - 3);
            Log.d(BleClient2.TAG, "mtu changed mtu :" + i);
            BleClient2.this.clientStatus = BleClientStatus.BLE_CONETED;
            this.val$connetLiseners.bleConnectedSuccess();
            new Handler().postDelayed(new Runnable() { // from class: com.nky.nkyble.ble.BleClient2$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m232lambda$onMtuChanged$0$comnkynkyblebleBleClient2$2();
                }
            }, 100L);
        }

        /* renamed from: lambda$onMtuChanged$0$com-nky-nkyble-ble-BleClient2$2, reason: not valid java name */
        /* synthetic */ void m232lambda$onMtuChanged$0$comnkynkyblebleBleClient2$2() {
            BleClient2.this.setNotify();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMtu(BleConnetLiseners bleConnetLiseners) {
        BleManager.getInstance().setMtu(this.mBleDevice, this.mtu, new AnonymousClass2(bleConnetLiseners));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNotify() {
        BleManager.getInstance().notify(this.mBleDevice, "000000FF-0000-1000-8000-00805f9b34fb", "0000ff01-0000-1000-8000-00805f9b34fb", new BleNotifyCallback() { // from class: com.nky.nkyble.ble.BleClient2.3
            @Override // com.clj.fastble.callback.BleNotifyCallback
            public void onNotifySuccess() {
                Log.d(BleClient2.TAG, "on notify success");
            }

            @Override // com.clj.fastble.callback.BleNotifyCallback
            public void onNotifyFailure(BleException bleException) {
                Log.d(BleClient2.TAG, "on notify fail");
            }

            @Override // com.clj.fastble.callback.BleNotifyCallback
            public void onCharacteristicChanged(byte[] bArr) {
                if (BleClient2.this.liseners != null) {
                    BleClient2.this.liseners.requestResepon(bArr);
                }
            }
        });
    }

    @Override // com.nky.nkyble.ble.IBle
    public void disConnet() {
        this.clientStatus = BleClientStatus.BLE_DISCONET;
        BleManager.getInstance().disconnectAllDevice();
        BleManager.getInstance().destroy();
        this.mBleDevice = null;
    }

    @Override // com.nky.nkyble.ble.IBle
    public void sendDataResepone(byte[] bArr, final BleRequestCallback bleRequestCallback) {
        if (this.clientStatus != BleClientStatus.BLE_CONETED) {
            bleRequestCallback.sendMsgFail(new BleRequestException(99, "client disconnet"));
            return;
        }
        this.liseners = bleRequestCallback;
        try {
            BleManager.getInstance().write(this.mBleDevice, "000000FF-0000-1000-8000-00805f9b34fb", "0000ff01-0000-1000-8000-00805f9b34fb", bArr, false, new BleWriteCallback() { // from class: com.nky.nkyble.ble.BleClient2.4
                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                    Log.d("liaojinsha", "发送成功");
                }

                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteFailure(BleException bleException) {
                    Log.d("liaojinsha", "发送失败" + bleException.getDescription());
                    bleRequestCallback.sendMsgFail(new BleRequestException(bleException.getCode(), bleException.getDescription()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nky.nkyble.ble.IBle
    public void sendDataNoResepone(byte[] bArr, final BleRequestCallback bleRequestCallback) {
        if (this.clientStatus != BleClientStatus.BLE_CONETED) {
            return;
        }
        this.liseners = bleRequestCallback;
        try {
            BleManager.getInstance().write(this.mBleDevice, "000000FF-0000-1000-8000-00805f9b34fb", "0000ff04-0000-1000-8000-00805f9b34fb", bArr, true, true, 50L, new BleWriteCallback() { // from class: com.nky.nkyble.ble.BleClient2.5
                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                    bleRequestCallback.writeSuccess(i, i2, bArr2);
                }

                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteFailure(BleException bleException) {
                    bleRequestCallback.writeFail();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nky.nkyble.ble.IBle
    public boolean isConneted() {
        return this.clientStatus == BleClientStatus.BLE_CONETED;
    }
}
