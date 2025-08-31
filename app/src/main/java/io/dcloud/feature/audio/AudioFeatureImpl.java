package io.dcloud.feature.audio;

import android.media.AudioManager;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.adapter.util.MessageHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class AudioFeatureImpl implements IFeature, MessageHandler.IMessages {
    static final String TAG = "AudioFeatureImpl";
    HashMap<String, ArrayList> mAppsAudioObj = null;

    private Object findAppObj(String str, String str2) {
        ArrayList appObjList = getAppObjList(str);
        if (!appObjList.isEmpty()) {
            Iterator it = appObjList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if ((next instanceof AbsAudio) && ((AbsAudio) next).mUuid.equals(str2)) {
                    return next;
                }
            }
        }
        return null;
    }

    private ArrayList getAppObjList(String str) {
        ArrayList arrayList = this.mAppsAudioObj.get(str);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(2);
        this.mAppsAudioObj.put(str, arrayList2);
        return arrayList2;
    }

    private void putAppObjList(String str, Object obj) {
        getAppObjList(str).add(obj);
    }

    private void removeAppObjFromList(String str, Object obj) {
        ArrayList appObjList = getAppObjList(str);
        if (appObjList != null) {
            appObjList.remove(obj);
        }
    }

    private void setCanPlay(String str, String str2, boolean z) throws IllegalStateException, JSONException, IOException, SecurityException, IllegalArgumentException {
        Iterator it = getAppObjList(str).iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof AudioPlayer) {
                AudioPlayer audioPlayer = (AudioPlayer) next;
                if (!audioPlayer.mUuid.equals(str2) && !z) {
                    audioPlayer.pause();
                }
                audioPlayer.setCanMix(z);
            }
        }
    }

    private void setSpeakerphoneOn(AudioManager audioManager, boolean z) {
        if (z) {
            audioManager.setSpeakerphoneOn(true);
            audioManager.setMode(1);
        } else {
            audioManager.setSpeakerphoneOn(false);
            audioManager.setRouting(0, 1, -1);
            audioManager.setMode(3);
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0044  */
    @Override // io.dcloud.common.DHInterface.IFeature
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(io.dcloud.common.DHInterface.IWebview r9, java.lang.String r10, java.lang.String[] r11) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.audio.AudioFeatureImpl.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.mAppsAudioObj = new HashMap<>(2);
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x0164  */
    @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(java.lang.Object r18) throws java.lang.IllegalStateException, org.json.JSONException, java.io.IOException, java.lang.SecurityException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instructions count: 666
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.audio.AudioFeatureImpl.execute(java.lang.Object):void");
    }
}
