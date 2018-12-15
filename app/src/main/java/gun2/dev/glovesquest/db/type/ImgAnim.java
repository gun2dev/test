package gun2.dev.glovesquest.db.type;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ImgAnim extends RealmObject {
    private RealmList<String> imgList;
    private MotionSet motionSet;
    private int repeat = -1;
    private int frameDelay;

    public RealmList<String> getImgList() {
        return imgList;
    }

    public void setImgList(RealmList<String> imgList) {
        this.imgList = imgList;
    }

    public MotionSet getMotionSet() {
        return motionSet;
    }

    public void setMotionSet(MotionSet motionSet) {
        this.motionSet = motionSet;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }
}
