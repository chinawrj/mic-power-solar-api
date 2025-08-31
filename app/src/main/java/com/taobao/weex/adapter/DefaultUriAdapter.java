package com.taobao.weex.adapter;

import android.net.Uri;
import android.text.TextUtils;
import com.taobao.weex.WXSDKInstance;
import java.util.List;

/* loaded from: classes.dex */
public class DefaultUriAdapter implements URIAdapter {
    private Uri.Builder buildRelativeURI(Uri.Builder builder, Uri uri, Uri uri2) {
        if (uri2.getAuthority() != null) {
            return builder.scheme(uri.getScheme());
        }
        builder.encodedAuthority(uri.getEncodedAuthority()).scheme(uri.getScheme()).path(null);
        if (uri2.getPath().startsWith("/")) {
            builder.appendEncodedPath(uri2.getEncodedPath().substring(1));
        } else {
            List<String> pathSegments = uri.getPathSegments();
            int size = pathSegments.size() - (!uri.getPath().endsWith("/") ? 1 : 0);
            for (int i = 0; i < size; i++) {
                builder.appendEncodedPath(pathSegments.get(i));
            }
            builder.appendEncodedPath(uri2.getEncodedPath());
        }
        return builder;
    }

    @Override // io.dcloud.feature.uniapp.adapter.AbsURIAdapter
    public Uri rewrite(WXSDKInstance wXSDKInstance, String str, Uri uri) {
        return rewrite(wXSDKInstance.getBundleUrl(), str, uri);
    }

    @Override // io.dcloud.feature.uniapp.adapter.AbsURIAdapter
    public Uri rewrite(String str, String str2, Uri uri) {
        if (TextUtils.isEmpty(str)) {
            return uri;
        }
        Uri uri2 = Uri.parse(str);
        return uri.isRelative() ? uri.getEncodedPath().length() == 0 ? ("image".equals(str2) && TextUtils.isEmpty(uri.toString())) ? uri : uri2 : buildRelativeURI(uri.buildUpon(), uri2, uri).build() : uri;
    }
}
