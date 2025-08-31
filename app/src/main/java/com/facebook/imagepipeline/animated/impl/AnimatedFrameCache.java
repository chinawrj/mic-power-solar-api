package com.facebook.imagepipeline.animated.impl;

import android.net.Uri;
import androidx.core.view.PointerIconCompat;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedFrameCache {
    private final CountingMemoryCache<CacheKey, CloseableImage> mBackingCache;
    private final CacheKey mImageCacheKey;
    private final LinkedHashSet<CacheKey> mFreeItemsPool = new LinkedHashSet<>();
    private final CountingMemoryCache.EntryStateObserver<CacheKey> mEntryStateObserver = new CountingMemoryCache.EntryStateObserver<CacheKey>() { // from class: com.facebook.imagepipeline.animated.impl.AnimatedFrameCache.1
        @Override // com.facebook.imagepipeline.cache.CountingMemoryCache.EntryStateObserver
        public void onExclusivityChanged(CacheKey key, boolean isExclusive) {
            AnimatedFrameCache.this.onReusabilityChange(key, isExclusive);
        }
    };

    static class FrameKey implements CacheKey {
        private final int mFrameIndex;
        private final CacheKey mImageCacheKey;

        @Override // com.facebook.cache.common.CacheKey
        @Nullable
        public String getUriString() {
            return null;
        }

        @Override // com.facebook.cache.common.CacheKey
        public boolean isResourceIdForDebugging() {
            return false;
        }

        public FrameKey(CacheKey imageCacheKey, int frameIndex) {
            this.mImageCacheKey = imageCacheKey;
            this.mFrameIndex = frameIndex;
        }

        @Override // com.facebook.cache.common.CacheKey
        public String toString() {
            return Objects.toStringHelper(this).add("imageCacheKey", this.mImageCacheKey).add("frameIndex", this.mFrameIndex).toString();
        }

        @Override // com.facebook.cache.common.CacheKey
        public boolean equals(@Nullable Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FrameKey)) {
                return false;
            }
            FrameKey frameKey = (FrameKey) o;
            return this.mFrameIndex == frameKey.mFrameIndex && this.mImageCacheKey.equals(frameKey.mImageCacheKey);
        }

        @Override // com.facebook.cache.common.CacheKey
        public int hashCode() {
            return (this.mImageCacheKey.hashCode() * PointerIconCompat.TYPE_ALL_SCROLL) + this.mFrameIndex;
        }

        @Override // com.facebook.cache.common.CacheKey
        public boolean containsUri(Uri uri) {
            return this.mImageCacheKey.containsUri(uri);
        }
    }

    public AnimatedFrameCache(CacheKey imageCacheKey, final CountingMemoryCache<CacheKey, CloseableImage> backingCache) {
        this.mImageCacheKey = imageCacheKey;
        this.mBackingCache = backingCache;
    }

    public synchronized void onReusabilityChange(CacheKey key, boolean isReusable) {
        if (isReusable) {
            this.mFreeItemsPool.add(key);
        } else {
            this.mFreeItemsPool.remove(key);
        }
    }

    @Nullable
    public CloseableReference<CloseableImage> cache(int frameIndex, CloseableReference<CloseableImage> imageRef) {
        return this.mBackingCache.cache(keyFor(frameIndex), imageRef, this.mEntryStateObserver);
    }

    @Nullable
    public CloseableReference<CloseableImage> get(int frameIndex) {
        return this.mBackingCache.get(keyFor(frameIndex));
    }

    public boolean contains(int frameIndex) {
        return this.mBackingCache.contains((CountingMemoryCache<CacheKey, CloseableImage>) keyFor(frameIndex));
    }

    @Nullable
    public CloseableReference<CloseableImage> getForReuse() {
        CloseableReference<CloseableImage> closeableReferenceReuse;
        do {
            CacheKey cacheKeyPopFirstFreeItemKey = popFirstFreeItemKey();
            if (cacheKeyPopFirstFreeItemKey == null) {
                return null;
            }
            closeableReferenceReuse = this.mBackingCache.reuse(cacheKeyPopFirstFreeItemKey);
        } while (closeableReferenceReuse == null);
        return closeableReferenceReuse;
    }

    @Nullable
    private synchronized CacheKey popFirstFreeItemKey() {
        CacheKey next;
        Iterator<CacheKey> it = this.mFreeItemsPool.iterator();
        if (it.hasNext()) {
            next = it.next();
            it.remove();
        } else {
            next = null;
        }
        return next;
    }

    private FrameKey keyFor(int frameIndex) {
        return new FrameKey(this.mImageCacheKey, frameIndex);
    }
}
