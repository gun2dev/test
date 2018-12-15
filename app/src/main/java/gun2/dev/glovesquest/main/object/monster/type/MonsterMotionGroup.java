package gun2.dev.glovesquest.main.object.monster.type;

import java.util.ArrayList;

import gun2.dev.glovesquest.utils.type.PointXY;

public class MonsterMotionGroup {
    public MonsterMotionGroup() {
    }

    private ArrayList<MonsterMotionObject> monsterMotionObjectList = new ArrayList<MonsterMotionObject>();
    private int startDelay = 1;
    private int lastDelay = 1;
    private int repeat = -1;
    private int repeatDelay = 1;
    private int frameDelay = 25;
    private int rotate;
    private int currentMotionGroupNum = 0;
    private int lastMotionGroupNum = 1;

    public ArrayList<MonsterMotionObject> getMonsterMotionObjectList() {
        return monsterMotionObjectList;
    }

    public void setMonsterMotionObjectList(ArrayList<MonsterMotionObject> monsterMotionObjectList) {
        this.monsterMotionObjectList = monsterMotionObjectList;
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

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getCurrentMotionGroupNum() {
        return currentMotionGroupNum;
    }

    public void plusCurrentMotionGroupNum() {
        currentMotionGroupNum = ++currentMotionGroupNum%lastMotionGroupNum;
    }

    public int getLastMotionGroupNum() {
        return lastMotionGroupNum;
    }

    public void setLastMotionGroupNum(int lastMotionGroupNum) {
        this.lastMotionGroupNum = lastMotionGroupNum;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    public void setCurrentMotionGroupNum(int currentMotionGroupNum) {
        this.currentMotionGroupNum = currentMotionGroupNum;
    }

    public void addMonsterMotionObject(MonsterMotionObject monsterMotionObject) {
        monsterMotionObjectList.add(monsterMotionObject);
    }

    public PointXY getCurrentPointInGroup() {

        PointXY result = monsterMotionObjectList.get(currentMotionGroupNum).getCurrentPoint();

//        if (DeviceEnvironment.sFrame % frameDelay == 0) {
        /*
        if (true) {
            switch (repeat) {
                case -1:
                    //무한반복
//                    currentMotionGroupNum = (currentMotionGroupNum + 1) % lastMotionGroupNum;
                    //그룹 작업이 한바퀴 돌면 모션작업 초기화
                    if (currentMotionGroupNum == lastMotionGroupNum) {
                        currentMotionGroupNum = 0;
                        monsterMotionObjectList.get(currentMotionGroupNum).initCurrentMotionNum();
                    }
                    break;
                case -2:
                    //반복없음 (마지막 모션 그룹으로 대기)
                    if (currentMotionGroupNum + 1 == lastMotionGroupNum) {
                        rotate++;
                        break;
                    }
//                    currentMotionGroupNum = (currentMotionGroupNum + 1);
                    break;
                case -3:
                    //반복없음( 첫번째 모션 그룹으로 대기)
                    if (rotate < 1)
                        currentMotionGroupNum = (currentMotionGroupNum + 1) % lastMotionGroupNum;
                    break;
                default:
                    if (rotate == repeat) break;
                    currentMotionGroupNum = (currentMotionGroupNum + 1);
                    break;
            }

        }*/
        return result;
    }
}
