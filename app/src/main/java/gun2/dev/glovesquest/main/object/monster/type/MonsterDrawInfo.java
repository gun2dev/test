package gun2.dev.glovesquest.main.object.monster.type;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class MonsterDrawInfo {
    public MonsterDrawInfo() {
    }

    private ArrayList<Bitmap> monsterImgList;
    private int canvasLastPage = 0;
    private int width = 0;
    private int height = 0;
    private int repeat = -1;
    private int crtX = 0;
    private int crtY = 0;
    private int frameDelay = 1;
    private int fadeSpeed = 3;
    private int stiffenTime = 7;
    private MonsterMotionGroup motionGroup;

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

    public int getFadeSpeed() {
        return fadeSpeed;
    }

    public void setFadeSpeed(int fadeSpeed) {
        this.fadeSpeed = fadeSpeed;
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
}
