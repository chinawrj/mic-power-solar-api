package dc.squareup.okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes3.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;
    final int[] trie;

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    private static void buildTrieRecursive(long j, Buffer buffer, int i, List<ByteString> list, int i2, int i3, List<Integer> list2) {
        int iIntValue;
        int i4;
        int i5;
        int i6;
        int i7;
        Buffer buffer2;
        if (i2 >= i3) {
            throw new AssertionError();
        }
        for (int i8 = i2; i8 < i3; i8++) {
            if (list.get(i8).size() < i) {
                throw new AssertionError();
            }
        }
        ByteString byteString = list.get(i2);
        ByteString byteString2 = list.get(i3 - 1);
        if (i == byteString.size()) {
            int i9 = i2 + 1;
            i4 = i9;
            iIntValue = list2.get(i2).intValue();
            byteString = list.get(i9);
        } else {
            iIntValue = -1;
            i4 = i2;
        }
        if (byteString.getByte(i) == byteString2.getByte(i)) {
            int iMin = Math.min(byteString.size(), byteString2.size());
            int i10 = 0;
            for (int i11 = i; i11 < iMin && byteString.getByte(i11) == byteString2.getByte(i11); i11++) {
                i10++;
            }
            long jIntCount = 1 + j + intCount(buffer) + 2 + i10;
            buffer.writeInt(-i10);
            buffer.writeInt(iIntValue);
            int i12 = i;
            while (true) {
                i5 = i + i10;
                if (i12 >= i5) {
                    break;
                }
                buffer.writeInt(byteString.getByte(i12) & 255);
                i12++;
            }
            if (i4 + 1 == i3) {
                if (i5 != list.get(i4).size()) {
                    throw new AssertionError();
                }
                buffer.writeInt(list2.get(i4).intValue());
                return;
            } else {
                Buffer buffer3 = new Buffer();
                buffer.writeInt((int) ((intCount(buffer3) + jIntCount) * (-1)));
                buildTrieRecursive(jIntCount, buffer3, i5, list, i4, i3, list2);
                buffer.write(buffer3, buffer3.size());
                return;
            }
        }
        int i13 = 1;
        for (int i14 = i4 + 1; i14 < i3; i14++) {
            if (list.get(i14 - 1).getByte(i) != list.get(i14).getByte(i)) {
                i13++;
            }
        }
        long jIntCount2 = j + intCount(buffer) + 2 + (i13 * 2);
        buffer.writeInt(i13);
        buffer.writeInt(iIntValue);
        for (int i15 = i4; i15 < i3; i15++) {
            byte b = list.get(i15).getByte(i);
            if (i15 == i4 || b != list.get(i15 - 1).getByte(i)) {
                buffer.writeInt(b & 255);
            }
        }
        Buffer buffer4 = new Buffer();
        int i16 = i4;
        while (i16 < i3) {
            byte b2 = list.get(i16).getByte(i);
            int i17 = i16 + 1;
            int i18 = i17;
            while (true) {
                if (i18 >= i3) {
                    i6 = i3;
                    break;
                } else {
                    if (b2 != list.get(i18).getByte(i)) {
                        i6 = i18;
                        break;
                    }
                    i18++;
                }
            }
            if (i17 == i6 && i + 1 == list.get(i16).size()) {
                buffer.writeInt(list2.get(i16).intValue());
                i7 = i6;
                buffer2 = buffer4;
            } else {
                buffer.writeInt((int) ((intCount(buffer4) + jIntCount2) * (-1)));
                i7 = i6;
                buffer2 = buffer4;
                buildTrieRecursive(jIntCount2, buffer4, i + 1, list, i16, i6, list2);
            }
            buffer4 = buffer2;
            i16 = i7;
        }
        Buffer buffer5 = buffer4;
        buffer.write(buffer5, buffer5.size());
    }

    private static int intCount(Buffer buffer) {
        return (int) (buffer.size() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b7, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static dc.squareup.okio.Options of(dc.squareup.okio.ByteString... r11) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: dc.squareup.okio.Options.of(dc.squareup.okio.ByteString[]):dc.squareup.okio.Options");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.byteStrings.length;
    }

    @Override // java.util.AbstractList, java.util.List
    public ByteString get(int i) {
        return this.byteStrings[i];
    }
}
