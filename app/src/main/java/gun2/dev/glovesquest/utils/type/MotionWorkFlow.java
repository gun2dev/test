package gun2.dev.glovesquest.utils.type;

import java.util.ArrayList;

public class MotionWorkFlow {
    private ArrayList<String> motionList = new ArrayList<String>();
    private int frontDelay;
    private int repeat;
    private int backDelay;
    private int frameDelay;

    private long frame = 0;

    public ArrayList<String> getMotionList() {
        return motionList;
    }

    public void setMotionList(ArrayList<String> motionList) {
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

    public void addMotion(String str){
        motionList.add(str);
    }

    public long getFrame() {
        return frame;
    }

    public void setFrame(long frame) {
        this.frame = frame;
    }
}
