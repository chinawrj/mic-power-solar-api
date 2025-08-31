package io.dcloud.nineoldandroids.animation;

/* loaded from: classes3.dex */
public class TimeAnimator extends ValueAnimator {
    private TimeListener mListener;
    private long mPreviousTime = -1;

    public interface TimeListener {
        void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2);
    }

    @Override // io.dcloud.nineoldandroids.animation.ValueAnimator
    void animateValue(float f) {
    }

    @Override // io.dcloud.nineoldandroids.animation.ValueAnimator
    void initAnimation() {
    }

    @Override // io.dcloud.nineoldandroids.animation.ValueAnimator
    boolean animationFrame(long j) {
        if (this.mPlayingState == 0) {
            this.mPlayingState = 1;
            if (this.mSeekTime < 0) {
                this.mStartTime = j;
            } else {
                this.mStartTime = j - this.mSeekTime;
                this.mSeekTime = -1L;
            }
        }
        if (this.mListener == null) {
            return false;
        }
        long j2 = j - this.mStartTime;
        long j3 = this.mPreviousTime;
        long j4 = j3 >= 0 ? j - j3 : 0L;
        this.mPreviousTime = j;
        this.mListener.onTimeUpdate(this, j2, j4);
        return false;
    }

    public void setTimeListener(TimeListener timeListener) {
        this.mListener = timeListener;
    }
}
