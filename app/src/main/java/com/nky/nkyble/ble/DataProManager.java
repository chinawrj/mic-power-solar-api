package com.nky.nkyble.ble;

import com.nky.nkyble.util.CRC16;
import com.nky.nkyble.util.DatalogApUtil;
import java.util.Arrays;

/* loaded from: classes.dex */
public class DataProManager {
    private byte[] receviceData = new byte[0];

    public boolean packData(byte[] bArr) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bArr.length < 8) {
            return false;
        }
        if (DatalogApUtil.byte2Int(new byte[]{bArr[0], bArr[1]}) != bArr.length - 2) {
            byte[] bArr2 = this.receviceData;
            if (bArr2 == null) {
                return false;
            }
            byte[] bArr3 = new byte[bArr2.length + bArr.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(bArr, 0, bArr3, this.receviceData.length, bArr.length);
            this.receviceData = bArr3;
        } else {
            this.receviceData = bArr;
        }
        if (checkAndshow()) {
            try {
                aesPase();
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private boolean checkAndshow() {
        byte[] bArr = this.receviceData;
        int iByte2Int = DatalogApUtil.byte2Int(new byte[]{bArr[0], bArr[1]});
        byte[] bArr2 = this.receviceData;
        boolean z = iByte2Int == bArr2.length - 2;
        byte b = bArr2[bArr2.length - 1];
        byte b2 = bArr2[bArr2.length - 2];
        byte[] bArrInt2Byte = DatalogApUtil.int2Byte(CRC16.calcCrc16(Arrays.copyOfRange(bArr2, 0, bArr2.length - 2)));
        return bArrInt2Byte[0] == b2 && bArrInt2Byte[1] == b && z;
    }

    private void aesPase() throws Exception {
        byte[] bArr = this.receviceData;
        int length = bArr.length - 10;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 8, bArr2, 0, length);
        byte[] bArrMsgDesCodeByAESCBC = DatalogApUtil.msgDesCodeByAESCBC(bArr2);
        byte[] bArr3 = this.receviceData;
        byte[] bArr4 = {bArr3[4], bArr3[5]};
        byte[] bArr5 = {bArr3[bArr3.length - 2], bArr3[bArr3.length - 1]};
        int iByte2Int = DatalogApUtil.byte2Int(bArr4);
        int i = iByte2Int - 2;
        byte[] bArr6 = new byte[i];
        System.arraycopy(bArrMsgDesCodeByAESCBC, 0, bArr6, 0, i);
        byte[] bArr7 = new byte[iByte2Int + 8];
        System.arraycopy(this.receviceData, 0, bArr7, 0, 8);
        System.arraycopy(bArr6, 0, bArr7, 8, i);
        System.arraycopy(bArr5, 0, bArr7, iByte2Int + 6, 2);
        this.receviceData = bArr7;
    }

    public byte[] getReceviceData() {
        return this.receviceData;
    }
}
