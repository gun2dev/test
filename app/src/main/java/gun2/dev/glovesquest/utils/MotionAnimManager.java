package gun2.dev.glovesquest.utils;

import java.util.ArrayList;

import gun2.dev.glovesquest.utils.type.MotionWorkFlow;

public class MotionAnimManager {
    private ArrayList<MotionWorkFlow> motionWorkList = new ArrayList<MotionWorkFlow>();
    private int startDelay;
    private int lastDelay;
    private int repeat;
    private int repeatDelay;

    private long frame = 0;

    public ArrayList<MotionWorkFlow> getMotionWorkList() {
        return motionWorkList;
    }

    public void setMotionWorkList(ArrayList<MotionWorkFlow> motionWorkList) {
        this.motionWorkList = motionWorkList;
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

    public void addMotionWork(MotionWorkFlow motionWorkFlow) {
        motionWorkList.add(motionWorkFlow);
    }

    public long getFrame() {
        return frame;
    }

    public void setFrame(long frame) {
        this.frame = frame;
    }
}
