package io.dcloud.sdk.core.entry;

@Deprecated
/* loaded from: classes3.dex */
public class SplashAOLConfig {
    private int a;
    private int b;

    public static final class Builder {
        private int a;
        private int b;

        public SplashAOLConfig build() {
            return new SplashAOLConfig(this);
        }

        public Builder height(int i) {
            this.b = i;
            return this;
        }

        public Builder width(int i) {
            this.a = i;
            return this;
        }
    }

    public int getHeight() {
        return this.b;
    }

    public int getWidth() {
        return this.a;
    }

    private SplashAOLConfig(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
    }
}
