package com.bun.miitmdid.supplier.sumsung;

import android.content.Context;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

/* loaded from: classes.dex */
public class a implements InnerIdSupplier, com.bun.miitmdid.c.e.a {
    public SupplierListener a;
    private SumsungCore b;

    public a(Context context, SupplierListener supplierListener) {
        this.a = supplierListener;
        this.b = new SumsungCore(context, this);
    }

    @Override // com.bun.supplier.InnerIdSupplier
    public native void a(SupplierListener supplierListener);

    @Override // com.bun.miitmdid.c.e.a
    public native void a(boolean z);

    @Override // com.bun.supplier.InnerIdSupplier
    public native boolean a();

    @Override // com.bun.miitmdid.c.e.a
    public native void b();

    @Override // com.bun.supplier.IdSupplier
    public native String getAAID();

    @Override // com.bun.supplier.IdSupplier
    public native String getOAID();

    @Override // com.bun.supplier.InnerIdSupplier
    public native String getUDID();

    @Override // com.bun.supplier.IdSupplier
    public native String getVAID();

    @Override // com.bun.supplier.IdSupplier
    public native boolean isSupported();

    @Override // com.bun.supplier.InnerIdSupplier
    public native void shutDown();
}
