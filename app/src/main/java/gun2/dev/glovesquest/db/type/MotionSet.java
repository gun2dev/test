package gun2.dev.glovesquest.db.type;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MotionSet extends RealmObject {

    private RealmList<MotionAnim> motionAnimList;
    private int startDelay;
    private int lastDelay;
    private int repeat;
    private int repeatDelay;
    private int frameDelay = 10;

    public RealmList<MotionAnim> getMotionAnimList() {
        return motionAnimList;
    }

    public void setMotionAnimList(RealmList<MotionAnim> motionAnimList) {
        this.motionAnimList = motionAnimList;
    }

    public int getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(int startDelay) {
        this.startDelay = startDelay;
    }

    public int getLastDelay() {
        return lastDelay;
    }

    public void setLastDelay(int lastDelay) {
        this.lastDelay = lastDelay;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getRepeatDelay() {
        return repeatDelay;
    }

    public void setRepeatDelay(int repeatDelay) {
        this.repeatDelay = repeatDelay;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    @Override
    public String toString() {
        return "MotionSet{" +
                "motionAnimList=" + motionAnimList +
                ", startDelay=" + startDelay +
                ", lastDelay=" + lastDelay +
                ", repeat=" + repeat +
                ", repeatDelay=" + repeatDelay +
                '}';
    }
}
