package io.dcloud.js.geolocation.system;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import io.dcloud.common.adapter.util.Logger;

/* loaded from: classes3.dex */
public class b implements LocationListener {
    private Context a;
    private LocationManager b;
    private a c;
    private Location e;
    private boolean f;
    private boolean d = false;
    long g = System.currentTimeMillis();

    public b(Context context, a aVar) {
        this.f = false;
        this.c = aVar;
        this.a = context;
        this.b = (LocationManager) context.getSystemService("location");
        this.f = false;
    }

    private void a(boolean z) {
        this.d = z;
        if (z) {
            this.g = System.currentTimeMillis();
        }
    }

    public void b() {
        if (this.f) {
            this.b.removeUpdates(this);
        }
        this.f = false;
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        Logger.d("GpsListener: The location has been updated!");
        a(true);
        this.e = location;
        this.c.a(location, a.s);
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        this.f = false;
        if (this.d) {
            return;
        }
        this.c.a(a.p, "GPS provider disabled.", a.s);
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
        Logger.d("GpsListener: The provider " + str + " is enabled");
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
        Logger.d("GpsListener: The status of the provider " + str + " has changed");
        if (i == 0) {
            Logger.d("GpsListener: " + str + " is OUT OF SERVICE");
            this.c.a(a.p, "GPS out of service.", a.s);
            return;
        }
        if (i == 1) {
            Logger.d("GpsListener: " + str + " is TEMPORARILY_UNAVAILABLE");
            return;
        }
        Logger.d("GpsListener: " + str + " is Available");
    }

    public boolean a() {
        if (System.currentTimeMillis() - this.g >= 10000) {
            this.d = false;
        }
        return this.d;
    }

    public void a(int i) {
        if (this.f) {
            return;
        }
        this.f = true;
        this.b.requestLocationUpdates("gps", i, 0.0f, this);
    }
}
