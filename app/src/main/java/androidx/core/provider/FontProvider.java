package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator() { // from class: androidx.core.provider.FontProvider$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FontProvider.lambda$static$0((byte[]) obj, (byte[]) obj2);
        }
    };

    private FontProvider() {
    }

    static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, FontRequest fontRequest, CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return FontsContractCompat.FontFamilyResult.create(1, null);
        }
        return FontsContractCompat.FontFamilyResult.create(0, query(context, fontRequest, provider.authority, cancellationSignal));
    }

    static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
        List<byte[]> listConvertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listConvertToByteArrayList, sByteArrayComparator);
        List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
        for (int i = 0; i < certificates.size(); i++) {
            ArrayList arrayList = new ArrayList(certificates.get(i));
            Collections.sort(arrayList, sByteArrayComparator);
            if (equalsByteArrayList(listConvertToByteArrayList, arrayList)) {
                return providerInfoResolveContentProvider;
            }
        }
        return null;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static androidx.core.provider.FontsContractCompat.FontInfo[] query(android.content.Context r16, androidx.core.provider.FontRequest r17, java.lang.String r18, android.os.CancellationSignal r19) {
        /*
            r0 = r18
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.net.Uri$Builder r2 = new android.net.Uri$Builder
            r2.<init>()
            java.lang.String r3 = "content"
            android.net.Uri$Builder r2 = r2.scheme(r3)
            android.net.Uri$Builder r2 = r2.authority(r0)
            android.net.Uri r2 = r2.build()
            android.net.Uri$Builder r4 = new android.net.Uri$Builder
            r4.<init>()
            android.net.Uri$Builder r3 = r4.scheme(r3)
            android.net.Uri$Builder r0 = r3.authority(r0)
            java.lang.String r3 = "file"
            android.net.Uri$Builder r0 = r0.appendPath(r3)
            android.net.Uri r0 = r0.build()
            r3 = r16
            androidx.core.provider.FontProvider$ContentQueryWrapper r3 = androidx.core.provider.FontProvider.ContentQueryWrapper.CC.make(r3, r2)
            r11 = 0
            java.lang.String r4 = "_id"
            java.lang.String r5 = "file_id"
            java.lang.String r6 = "font_ttc_index"
            java.lang.String r7 = "font_variation_settings"
            java.lang.String r8 = "font_weight"
            java.lang.String r9 = "font_italic"
            java.lang.String r10 = "result_code"
            java.lang.String[] r6 = new java.lang.String[]{r4, r5, r6, r7, r8, r9, r10}     // Catch: java.lang.Throwable -> Led
            java.lang.String r7 = "query = ?"
            java.lang.String r4 = r17.getQuery()     // Catch: java.lang.Throwable -> Led
            java.lang.String[] r8 = new java.lang.String[]{r4}     // Catch: java.lang.Throwable -> Led
            r9 = 0
            r4 = r3
            r5 = r2
            r10 = r19
            android.database.Cursor r11 = r4.query(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> Led
            if (r11 == 0) goto Ldb
            int r5 = r11.getCount()     // Catch: java.lang.Throwable -> Led
            if (r5 <= 0) goto Ldb
            java.lang.String r1 = "result_code"
            int r1 = r11.getColumnIndex(r1)     // Catch: java.lang.Throwable -> Led
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Led
            r5.<init>()     // Catch: java.lang.Throwable -> Led
            java.lang.String r6 = "_id"
            int r6 = r11.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Led
            java.lang.String r7 = "file_id"
            int r7 = r11.getColumnIndex(r7)     // Catch: java.lang.Throwable -> Led
            java.lang.String r8 = "font_ttc_index"
            int r8 = r11.getColumnIndex(r8)     // Catch: java.lang.Throwable -> Led
            java.lang.String r9 = "font_weight"
            int r9 = r11.getColumnIndex(r9)     // Catch: java.lang.Throwable -> Led
            java.lang.String r10 = "font_italic"
            int r10 = r11.getColumnIndex(r10)     // Catch: java.lang.Throwable -> Led
        L8e:
            boolean r12 = r11.moveToNext()     // Catch: java.lang.Throwable -> Led
            if (r12 == 0) goto Lda
            r12 = -1
            if (r1 == r12) goto L9c
            int r13 = r11.getInt(r1)     // Catch: java.lang.Throwable -> Led
            goto L9d
        L9c:
            r13 = 0
        L9d:
            if (r8 == r12) goto La4
            int r14 = r11.getInt(r8)     // Catch: java.lang.Throwable -> Led
            goto La5
        La4:
            r14 = 0
        La5:
            if (r7 != r12) goto Lb2
            r17 = r5
            long r4 = r11.getLong(r6)     // Catch: java.lang.Throwable -> Led
            android.net.Uri r4 = android.content.ContentUris.withAppendedId(r2, r4)     // Catch: java.lang.Throwable -> Led
            goto Lbc
        Lb2:
            r17 = r5
            long r4 = r11.getLong(r7)     // Catch: java.lang.Throwable -> Led
            android.net.Uri r4 = android.content.ContentUris.withAppendedId(r0, r4)     // Catch: java.lang.Throwable -> Led
        Lbc:
            if (r9 == r12) goto Lc3
            int r5 = r11.getInt(r9)     // Catch: java.lang.Throwable -> Led
            goto Lc5
        Lc3:
            r5 = 400(0x190, float:5.6E-43)
        Lc5:
            if (r10 == r12) goto Lcf
            int r12 = r11.getInt(r10)     // Catch: java.lang.Throwable -> Led
            r15 = 1
            if (r12 != r15) goto Lcf
            goto Ld0
        Lcf:
            r15 = 0
        Ld0:
            androidx.core.provider.FontsContractCompat$FontInfo r4 = androidx.core.provider.FontsContractCompat.FontInfo.create(r4, r14, r5, r15, r13)     // Catch: java.lang.Throwable -> Led
            r5 = r17
            r5.add(r4)     // Catch: java.lang.Throwable -> Led
            goto L8e
        Lda:
            r1 = r5
        Ldb:
            if (r11 == 0) goto Le0
            r11.close()
        Le0:
            r3.close()
            r0 = 0
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = new androidx.core.provider.FontsContractCompat.FontInfo[r0]
            java.lang.Object[] r0 = r1.toArray(r0)
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = (androidx.core.provider.FontsContractCompat.FontInfo[]) r0
            return r0
        Led:
            r0 = move-exception
            if (r11 == 0) goto Lf3
            r11.close()
        Lf3:
            r3.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.query(android.content.Context, androidx.core.provider.FontRequest, java.lang.String, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontInfo[]");
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    static /* synthetic */ int lambda$static$0(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            byte b2 = bArr2[i];
            if (b != b2) {
                return b - b2;
            }
        }
        return 0;
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    private interface ContentQueryWrapper {
        void close();

        Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);

        /* renamed from: androidx.core.provider.FontProvider$ContentQueryWrapper$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static ContentQueryWrapper make(Context context, Uri uri) {
                if (Build.VERSION.SDK_INT < 24) {
                    return new ContentQueryWrapperApi16Impl(context, uri);
                }
                return new ContentQueryWrapperApi24Impl(context, uri);
            }
        }
    }

    private static class ContentQueryWrapperApi16Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi16Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.release();
            }
        }
    }

    private static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi24Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.release();
            }
        }
    }
}
