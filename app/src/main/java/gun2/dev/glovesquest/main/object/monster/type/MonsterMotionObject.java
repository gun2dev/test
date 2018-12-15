package gun2.dev.glovesquest.main.object.monster.type;

import java.util.ArrayList;

import gun2.dev.glovesquest.utils.type.PointXY;

public class MonsterMotionObject {
    public MonsterMotionObject() {
    }

    private ArrayList<String> motionList = new ArrayList<String>();
    private int frontDelay = 1;
    private int repeat = -1;
    private int backDelay = 1;
    private int frameDelay = 25;
    private int rotate;
    private int currentMotionNum = 0;
    private int lastMotionNum = 1;
    private int frame = 0;
    private boolean motion = true;
    private ArrayList<PointXY> motionNavigation = new ArrayList<PointXY>();
    private CreatedMonster createdMonster;
    private MonsterMotionGroup monsterMotionGroup;

    public ArrayList<String> getMotionList() {
        return motionList;
    }

    public void setMotionList(ArrayList<String> motionList) {
        this.motionList = motionList;
    }

    public void addMotion(String motion) {
        motionList.add(motion);
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

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getCurrentMotionNum() {
        return currentMotionNum;
    }

    public void setCurrentMotionNum(int currentMotionNum) {
        this.currentMotionNum = currentMotionNum;
    }

    public int getLastMotionNum() {
        return lastMotionNum;
    }

    public void setLastMotionNum(int lastMotionNum) {
        this.lastMotionNum = lastMotionNum;
    }

    public ArrayList<PointXY> getMotionNavigation() {
        return motionNavigation;
    }

    public void setMotionNavigation(ArrayList<PointXY> motionNavigation) {
        this.motionNavigation = motionNavigation;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public boolean isMotion() {
        return motion;
    }

    public void setMotion(boolean motion) {
        this.motion = motion;
    }

    public CreatedMonster getCreatedMonster() {
        return createdMonster;
    }

    public void setCreatedMonster(CreatedMonster createdMonster) {
        this.createdMonster = createdMonster;
    }

    public MonsterMotionGroup getMonsterMotionGroup() {
        return monsterMotionGroup;
    }

    public void setMonsterMotionGroup(MonsterMotionGroup monsterMotionGroup) {
        this.monsterMotionGroup = monsterMotionGroup;
    }

    public void initCurrentMotionNum() {
        currentMotionNum = 0;
        rotate = 0;
    }

    public PointXY getCurrentPoint() {
//        frame++;

        PointXY result = new PointXY().setAndGetPoint(
                motionNavigation.get(currentMotionNum).getX(),
                motionNavigation.get(currentMotionNum).getY()
        );
        //설정 프레임 딜레이가 지나면 모션을 다음으로 변경
        if (createdMonster.getFrame() % frameDelay == 0) {
//        if (true) {
            if (createdMonster.getFrame() > frontDelay) {
                currentMotionNum = (currentMotionNum + 1);
                if (currentMotionNum >= lastMotionNum){
                    currentMotionNum = 0;
                    monsterMotionGroup.plusCurrentMotionGroupNum();
                }
                /*
                switch (repeat) {
                    case -1:
                        //무한반복
                        currentMotionNum = (currentMotionNum + 1) % lastMotionNum;
                        break;
                    case -2:
                        //반복없음 (마지막 모션으로 대기)
                        if (currentMotionNum + 1 == lastMotionNum) {
                            rotate++;
                            monsterMotionGroup.plusCurrentMotionGroupNum();
                            break;
                        }
                        currentMotionNum = (currentMotionNum + 1);
                        break;
                    case -3:
                        //반복없음( 첫번째 모션으로 대기)
                        if (rotate < 1) currentMotionNum = (currentMotionNum + 1);
                        else {
                            monsterMotionGroup.plusCurrentMotionGroupNum();
                        }
                        break;
                    default:
                        if (rotate == repeat) {
                            monsterMotionGroup.plusCurrentMotionGroupNum();
                            break;
                        }
                        currentMotionNum = (currentMotionNum + 1);
                        break;
                }*/
            }
        }
        return result;
    }


}
