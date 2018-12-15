package gun2.dev.glovesquest.main.object.monster.type;

import android.graphics.Bitmap;

import java.util.ArrayList;

import gun2.dev.glovesquest.utils.type.PointXY;

public class MonsterDrawInfoData {
    private CreatedMonster createdMonster;

    private ArrayList<Bitmap> monsterImgList;
    private int canvasLastPage = 0;
    private int width = 0;
    private int height = 0;
    private int repeat = -1;
    private int crtX = 0;
    private int crtY = 0;
    private int frameDelay = 1;
    private int stiffenTime = 7;
    private MonsterMotionGroup motionGroup;

    private int canvasPage = 0;
    private int rotate = 0;
    private int stiffenCount = 0;

    public int getCanvasPage() {
        return canvasPage;
    }

    public void setCanvasPage(int canvasPage) {
        this.canvasPage = canvasPage;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getStiffenCount() {
        return stiffenCount;
    }

    public void setStiffenCount(int stiffenCount) {
        this.stiffenCount = stiffenCount;
    }


    public ArrayList<Bitmap> getMonsterImgList() {
        return monsterImgList;
    }

    public void setMonsterImgList(ArrayList<Bitmap> monsterImgList) {
        this.monsterImgList = monsterImgList;
    }

    public int getCanvasLastPage() {
        return canvasLastPage;
    }

    public void setCanvasLastPage(int canvasLastPage) {
        this.canvasLastPage = canvasLastPage;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getCrtX() {
        return crtX;
    }

    public void setCrtX(int crtX) {
        this.crtX = crtX;
    }

    public int getCrtY() {
        return crtY;
    }

    public void setCrtY(int crtY) {
        this.crtY = crtY;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    public int getStiffenTime() {
        return stiffenTime;
    }

    public void setStiffenTime(int stiffenTime) {
        this.stiffenTime = stiffenTime;
    }

    public MonsterMotionGroup getMotionGroup() {
        return motionGroup;
    }

    public void setMotionGroup(MonsterMotionGroup motionGroup) {
        this.motionGroup = motionGroup;
    }

    public CreatedMonster getCreatedMonster() {
        return createdMonster;
    }

    public void setCreatedMonster(CreatedMonster createdMonster) {
        this.createdMonster = createdMonster;
    }

    public Bitmap getCurrentMonsterImgBitmap(int frame) {
        Bitmap bitmap = monsterImgList.get(canvasPage);
//        frame++;
        //설정 프레임 딜레이가 지나면 이미지 다음것으로 변경
        if (frame % frameDelay == 0) {
            switch (repeat) {
                case -1:
                    //무한반복
                    canvasPage = (canvasPage + 1) % canvasLastPage;
                    break;
                case -2:
                    //반복없음 (마지막 이미지로 대기)
                    if (canvasPage + 1 == canvasLastPage) break;
                    canvasPage = (canvasPage + 1) % canvasLastPage;
                    break;
                case -3:
                    //반복없음( 첫번째 이미지로 대기)
                    if (rotate < 1) {
                        canvasPage = canvasPage + 1;
                        if (canvasPage == canvasLastPage) {
                            rotate++;
                            canvasPage = 0;
                        }
                    }
                    break;
                default:
                    if (rotate != repeat) {
                        canvasPage = canvasPage + 1;
                        if (canvasPage == canvasLastPage) {
                            rotate++;
                            canvasPage = 0;
                        }
                    }
                    break;
            }
        }
        return bitmap;
    }


    public PointXY getMotionPoint() {
        return motionGroup.getCurrentPointInGroup();
    }


    public void initData() {
        canvasPage = 0;
        rotate = 0;
        stiffenCount = 0;
    }


    public Bitmap getCurrentMonsterDeadImgBitmap(int frame, MonsterDrawInfoDataType monsterDrawInfoDataType) {
        int canvasPage = monsterDrawInfoDataType.getCurrentPage();
        Bitmap bitmap = monsterImgList.get(canvasPage);
//        frame++;
        //설정 프레임 딜레이가 지나면 이미지 다음것으로 변경
        if (frame % frameDelay == 0) {
            switch (repeat) {
                case -1:
                    //무한반복
                    monsterDrawInfoDataType.setCurrentPage((canvasPage + 1) % canvasLastPage);
                    break;
                case -2:
                    //반복없음 (마지막 이미지로 대기)
                    if (canvasPage + 1 == canvasLastPage) break;
                    monsterDrawInfoDataType.setCurrentPage((canvasPage + 1) % canvasLastPage);
                    break;
                case -3:
                    //반복없음( 첫번째 이미지로 대기)
                    if (rotate < 1) {
                        monsterDrawInfoDataType.setCurrentPage(canvasPage + 1);
                        if (monsterDrawInfoDataType.getCurrentPage() == canvasLastPage) {
                            rotate++;
                            monsterDrawInfoDataType.setCurrentPage(0);
                        }
                    }
                    break;
                default:
                    if (rotate != repeat) {
                        monsterDrawInfoDataType.setCurrentPage(canvasPage + 1);
                        if (monsterDrawInfoDataType.getCurrentPage() == canvasLastPage) {
                            rotate++;
                            monsterDrawInfoDataType.setCurrentPage(0);
                        }
                    }
                    break;
            }
        }
        return bitmap;
    }
}
