package io.dcloud.feature.uniapp.utils;

/* loaded from: classes3.dex */
public interface AbsLogLevel {
    int compare(AbsLogLevel absLogLevel);

    String getName();

    int getPriority();

    int getValue();
}
