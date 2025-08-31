package androidtranscoder.format;

import android.media.MediaFormat;
import io.dcloud.common.util.StringUtil;

/* loaded from: classes.dex */
public class MediaFormatPresets {
    private static final int LONGER_LENGTH_960x540 = 960;

    private MediaFormatPresets() {
    }

    @Deprecated
    public static MediaFormat getExportPreset960x540() {
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(MediaFormatExtraConstants.MIMETYPE_VIDEO_AVC, LONGER_LENGTH_960x540, 540);
        mediaFormatCreateVideoFormat.setInteger("bitrate", 5500000);
        mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", 30);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 1);
        return mediaFormatCreateVideoFormat;
    }

    public static MediaFormat getExportPreset960x540(int i, int i2) {
        int iMax = Math.max(i, i2);
        int iMin = Math.min(i, i2);
        int i3 = LONGER_LENGTH_960x540;
        if (iMax <= LONGER_LENGTH_960x540) {
            return null;
        }
        int i4 = iMin * LONGER_LENGTH_960x540;
        if (i4 % iMax == 0) {
            int i5 = i4 / iMax;
            if (i < i2) {
                i5 = LONGER_LENGTH_960x540;
                i3 = i5;
            }
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(MediaFormatExtraConstants.MIMETYPE_VIDEO_AVC, i3, i5);
            mediaFormatCreateVideoFormat.setInteger("bitrate", 5500000);
            mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
            mediaFormatCreateVideoFormat.setInteger("frame-rate", 30);
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 1);
            return mediaFormatCreateVideoFormat;
        }
        throw new OutputFormatUnavailableException(StringUtil.format("Could not fit to integer, original: (%d, %d), scaled: (%d, %f)", Integer.valueOf(iMax), Integer.valueOf(iMin), Integer.valueOf(LONGER_LENGTH_960x540), Double.valueOf((iMin * 960.0d) / iMax)));
    }
}
