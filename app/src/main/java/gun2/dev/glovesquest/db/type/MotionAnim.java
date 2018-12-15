package gun2.dev.glovesquest.db.type;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MotionAnim extends RealmObject {
    private RealmList<String> motionList;
    private int frontDelay;
    private int repeat;
    private int backDelay;
    private int frameDelay;

    public RealmList<String> getMotionList() {
        return motionList;
    }

    public void setMotionList(RealmList<String> motionList) {
        this.motionList = motionList;
    }

    public int getFrontDelay() {
        return frontDelay;
    }

    public void setFrontDelay(int frontDelay) {
        this.frontDelay = frontDelay;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getBackDelay() {
        return backDelay;
    }

    public void setBackDelay(int backDelay) {
        this.backDelay = backDelay;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    @Override
    public String toString() {
        return "MotionAnim{" +
                "motionList=" + motionList +
                ", frontDelay=" + frontDelay +
                ", repeat=" + repeat +
                ", backDelay=" + backDelay +
                ", frameDelay=" + frameDelay +
                '}';
    }
}
