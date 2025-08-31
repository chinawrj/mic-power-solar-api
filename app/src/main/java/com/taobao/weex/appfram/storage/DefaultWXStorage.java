package com.taobao.weex.appfram.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteStatement;
import com.taobao.weex.appfram.storage.IWXStorageAdapter;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.utils.WXLogUtils;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class DefaultWXStorage implements IWXStorageAdapter {
    private WXSQLiteOpenHelper mDatabaseSupplier;
    private ExecutorService mExecutorService;

    public DefaultWXStorage(Context context) {
        this.mDatabaseSupplier = new WXSQLiteOpenHelper(context);
    }

    private void execute(Runnable runnable) {
        if (this.mExecutorService == null) {
            this.mExecutorService = Executors.newSingleThreadExecutor();
        }
        if (runnable == null || this.mExecutorService.isShutdown() || this.mExecutorService.isTerminated()) {
            return;
        }
        this.mExecutorService.execute(WXThread.secure(runnable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> performGetAllKeys() {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase();
        if (database == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = database.query("default_wx_storage", new String[]{IApp.ConfigProperty.CONFIG_KEY}, null, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                arrayList.add(cursorQuery.getString(cursorQuery.getColumnIndex(IApp.ConfigProperty.CONFIG_KEY)));
            } catch (Exception e) {
                WXLogUtils.e("weex_storage", "DefaultWXStorage occurred an exception when execute getAllKeys:" + e.getMessage());
                return arrayList;
            } finally {
                cursorQuery.close();
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String performGetItem(String str) {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase();
        if (database == null) {
            return null;
        }
        Cursor cursorQuery = database.query("default_wx_storage", new String[]{"value"}, "key=?", new String[]{str}, null, null, null);
        try {
            if (!cursorQuery.moveToNext()) {
                return null;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("timestamp", WXSQLiteOpenHelper.sDateFormatter.format(new Date()));
            int iUpdate = this.mDatabaseSupplier.getDatabase().update("default_wx_storage", contentValues, "key= ?", new String[]{str});
            StringBuilder sb = new StringBuilder("update timestamp ");
            sb.append(iUpdate == 1 ? WXImage.SUCCEED : AbsoluteConst.EVENTS_FAILED);
            sb.append(" for operation [getItem(key = ");
            sb.append(str);
            sb.append(")]");
            WXLogUtils.d("weex_storage", sb.toString());
            return cursorQuery.getString(cursorQuery.getColumnIndex("value"));
        } catch (Exception e) {
            WXLogUtils.e("weex_storage", "DefaultWXStorage occurred an exception when execute getItem:" + e.getMessage());
            return null;
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long performGetLength() {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase();
        if (database == null) {
            return 0L;
        }
        SQLiteStatement sQLiteStatementCompileStatement = null;
        try {
            try {
                sQLiteStatementCompileStatement = database.compileStatement("SELECT count(key) FROM default_wx_storage");
                long jSimpleQueryForLong = sQLiteStatementCompileStatement.simpleQueryForLong();
                sQLiteStatementCompileStatement.close();
                return jSimpleQueryForLong;
            } catch (Exception e) {
                WXLogUtils.e("weex_storage", "DefaultWXStorage occurred an exception when execute getLength:" + e.getMessage());
                if (sQLiteStatementCompileStatement != null) {
                    sQLiteStatementCompileStatement.close();
                }
                return 0L;
            }
        } catch (Throwable th) {
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean performRemoveItem(String str) {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase();
        if (database == null) {
            return false;
        }
        try {
            return database.delete("default_wx_storage", "key=?", new String[]{str}) == 1;
        } catch (Exception e) {
            WXLogUtils.e("weex_storage", "DefaultWXStorage occurred an exception when execute removeItem:" + e.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean performSetItem(String str, String str2, boolean z, boolean z2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase();
        if (database == null) {
            return false;
        }
        WXLogUtils.d("weex_storage", "set k-v to storage(key:" + str + ",value:" + str2 + ",isPersistent:" + z + ",allowRetry:" + z2 + Operators.BRACKET_END_STR);
        String str3 = WXSQLiteOpenHelper.sDateFormatter.format(new Date());
        SQLiteStatement sQLiteStatementCompileStatement = null;
        try {
            try {
                sQLiteStatementCompileStatement = database.compileStatement("INSERT OR REPLACE INTO default_wx_storage VALUES (?,?,?,?);");
                sQLiteStatementCompileStatement.clearBindings();
                sQLiteStatementCompileStatement.bindString(1, str);
                sQLiteStatementCompileStatement.bindString(2, str2);
                sQLiteStatementCompileStatement.bindString(3, str3);
                sQLiteStatementCompileStatement.bindLong(4, z ? 1L : 0L);
                sQLiteStatementCompileStatement.execute();
                sQLiteStatementCompileStatement.close();
                return true;
            } catch (Exception e) {
                WXLogUtils.e("weex_storage", "DefaultWXStorage occurred an exception when execute setItem :" + e.getMessage());
                if (!(e instanceof SQLiteFullException) || !z2 || !trimToSize()) {
                    if (sQLiteStatementCompileStatement == null) {
                        return false;
                    }
                    sQLiteStatementCompileStatement.close();
                    return false;
                }
                WXLogUtils.d("weex_storage", "retry set k-v to storage(key:" + str + ",value:" + str2 + Operators.BRACKET_END_STR);
                boolean zPerformSetItem = performSetItem(str, str2, z, false);
                if (sQLiteStatementCompileStatement != null) {
                    sQLiteStatementCompileStatement.close();
                }
                return zPerformSetItem;
            }
        } catch (Throwable th) {
            if (sQLiteStatementCompileStatement != null) {
                sQLiteStatementCompileStatement.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0076 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean trimToSize() {
        /*
            r13 = this;
            java.lang.String r0 = "weex_storage"
            com.taobao.weex.appfram.storage.WXSQLiteOpenHelper r1 = r13.mDatabaseSupplier
            android.database.sqlite.SQLiteDatabase r2 = r1.getDatabase()
            r1 = 0
            if (r2 != 0) goto Lc
            return r1
        Lc:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r11 = "key"
            java.lang.String r12 = "persistent"
            java.lang.String[] r4 = new java.lang.String[]{r11, r12}
            r8 = 0
            java.lang.String r9 = "timestamp ASC"
            java.lang.String r3 = "default_wx_storage"
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)
            r3 = 1
            int r4 = r2.getCount()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            int r4 = r4 / 10
            r5 = r1
        L2d:
            boolean r6 = r2.moveToNext()     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> L55
            if (r6 == 0) goto L4f
            int r6 = r2.getColumnIndex(r11)     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> L55
            java.lang.String r6 = r2.getString(r6)     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> L55
            int r7 = r2.getColumnIndex(r12)     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> L55
            int r7 = r2.getInt(r7)     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> L55
            if (r7 != r3) goto L46
            goto L2d
        L46:
            if (r6 == 0) goto L2d
            int r5 = r5 + 1
            r10.add(r6)     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> L55
            if (r5 != r4) goto L2d
        L4f:
            r2.close()
            goto L74
        L53:
            r4 = move-exception
            goto L59
        L55:
            r0 = move-exception
            goto La2
        L57:
            r4 = move-exception
            r5 = r1
        L59:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L55
            r6.<init>()     // Catch: java.lang.Throwable -> L55
            java.lang.String r7 = "DefaultWXStorage occurred an exception when execute trimToSize:"
            r6.append(r7)     // Catch: java.lang.Throwable -> L55
            java.lang.String r4 = r4.getMessage()     // Catch: java.lang.Throwable -> L55
            r6.append(r4)     // Catch: java.lang.Throwable -> L55
            java.lang.String r4 = r6.toString()     // Catch: java.lang.Throwable -> L55
            com.taobao.weex.utils.WXLogUtils.e(r0, r4)     // Catch: java.lang.Throwable -> L55
            r2.close()
        L74:
            if (r5 > 0) goto L77
            return r1
        L77:
            java.util.Iterator r1 = r10.iterator()
        L7b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L8b
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            r13.performRemoveItem(r2)
            goto L7b
        L8b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "remove "
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r2 = " items by lru"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.taobao.weex.utils.WXLogUtils.e(r0, r1)
            return r3
        La2:
            r2.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.appfram.storage.DefaultWXStorage.trimToSize():boolean");
    }

    @Override // com.taobao.weex.appfram.storage.IWXStorageAdapter
    public void close() {
        final ExecutorService executorService = this.mExecutorService;
        execute(new Runnable() { // from class: com.taobao.weex.appfram.storage.DefaultWXStorage.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DefaultWXStorage.this.mDatabaseSupplier.closeDatabase();
                    ExecutorService executorService2 = executorService;
                    if (executorService2 != null) {
                        executorService2.shutdown();
                    }
                } catch (Exception e) {
                    WXLogUtils.e("weex_storage", e.getMessage());
                }
            }
        });
        this.mExecutorService = null;
    }

    @Override // com.taobao.weex.appfram.storage.IWXStorageAdapter
    public void getAllKeys(final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        execute(new Runnable() { // from class: com.taobao.weex.appfram.storage.DefaultWXStorage.5
            @Override // java.lang.Runnable
            public void run() {
                Map<String, Object> allkeysResult = StorageResultHandler.getAllkeysResult(DefaultWXStorage.this.performGetAllKeys());
                IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener2 = onResultReceivedListener;
                if (onResultReceivedListener2 == null) {
                    return;
                }
                onResultReceivedListener2.onReceived(allkeysResult);
            }
        });
    }

    @Override // com.taobao.weex.appfram.storage.IWXStorageAdapter
    public void getItem(final String str, final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        execute(new Runnable() { // from class: com.taobao.weex.appfram.storage.DefaultWXStorage.2
            @Override // java.lang.Runnable
            public void run() {
                Map<String, Object> itemResult = StorageResultHandler.getItemResult(DefaultWXStorage.this.performGetItem(str));
                IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener2 = onResultReceivedListener;
                if (onResultReceivedListener2 == null) {
                    return;
                }
                onResultReceivedListener2.onReceived(itemResult);
            }
        });
    }

    @Override // com.taobao.weex.appfram.storage.IWXStorageAdapter
    public void length(final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        execute(new Runnable() { // from class: com.taobao.weex.appfram.storage.DefaultWXStorage.4
            @Override // java.lang.Runnable
            public void run() {
                Map<String, Object> lengthResult = StorageResultHandler.getLengthResult(DefaultWXStorage.this.performGetLength());
                IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener2 = onResultReceivedListener;
                if (onResultReceivedListener2 == null) {
                    return;
                }
                onResultReceivedListener2.onReceived(lengthResult);
            }
        });
    }

    @Override // com.taobao.weex.appfram.storage.IWXStorageAdapter
    public void removeItem(final String str, final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        execute(new Runnable() { // from class: com.taobao.weex.appfram.storage.DefaultWXStorage.3
            @Override // java.lang.Runnable
            public void run() {
                Map<String, Object> mapRemoveItemResult = StorageResultHandler.removeItemResult(DefaultWXStorage.this.performRemoveItem(str));
                IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener2 = onResultReceivedListener;
                if (onResultReceivedListener2 == null) {
                    return;
                }
                onResultReceivedListener2.onReceived(mapRemoveItemResult);
            }
        });
    }

    @Override // com.taobao.weex.appfram.storage.IWXStorageAdapter
    public void setItem(final String str, final String str2, final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        execute(new Runnable() { // from class: com.taobao.weex.appfram.storage.DefaultWXStorage.1
            @Override // java.lang.Runnable
            public void run() {
                Map<String, Object> itemResult = StorageResultHandler.setItemResult(DefaultWXStorage.this.performSetItem(str, str2, false, true));
                IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener2 = onResultReceivedListener;
                if (onResultReceivedListener2 == null) {
                    return;
                }
                onResultReceivedListener2.onReceived(itemResult);
            }
        });
    }

    @Override // com.taobao.weex.appfram.storage.IWXStorageAdapter
    public void setItemPersistent(final String str, final String str2, final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        execute(new Runnable() { // from class: com.taobao.weex.appfram.storage.DefaultWXStorage.6
            @Override // java.lang.Runnable
            public void run() {
                Map<String, Object> itemResult = StorageResultHandler.setItemResult(DefaultWXStorage.this.performSetItem(str, str2, true, true));
                IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener2 = onResultReceivedListener;
                if (onResultReceivedListener2 == null) {
                    return;
                }
                onResultReceivedListener2.onReceived(itemResult);
            }
        });
    }
}
