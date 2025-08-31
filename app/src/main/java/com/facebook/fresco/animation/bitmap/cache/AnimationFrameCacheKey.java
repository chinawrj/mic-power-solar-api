package com.facebook.fresco.animation.bitmap.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimationFrameCacheKey implements CacheKey {
    private static final String URI_PREFIX = "anim://";
    private final String mAnimationUriString;
    private final boolean mDeepEquals;

    @Override // com.facebook.cache.common.CacheKey
    public boolean isResourceIdForDebugging() {
        return false;
    }

    public AnimationFrameCacheKey(int imageId) {
        this(imageId, false);
    }

    public AnimationFrameCacheKey(int imageId, boolean deepEquals) {
        this.mAnimationUriString = URI_PREFIX + imageId;
        this.mDeepEquals = deepEquals;
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean containsUri(Uri uri) {
        return uri.toString().startsWith(this.mAnimationUriString);
    }

    @Override // com.facebook.cache.common.CacheKey
    public String getUriString() {
        return this.mAnimationUriString;
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean equals(@Nullable Object o) {
        if (!this.mDeepEquals) {
            return super.equals(o);
        }
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mAnimationUriString.equals(((AnimationFrameCacheKey) o).mAnimationUriString);
    }

    @Override // com.facebook.cache.common.CacheKey
    public int hashCode() {
        if (!this.mDeepEquals) {
            return super.hashCode();
        }
        return this.mAnimationUriString.hashCode();
    }
}
