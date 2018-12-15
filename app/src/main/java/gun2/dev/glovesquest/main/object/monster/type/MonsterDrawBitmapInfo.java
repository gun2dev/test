package gun2.dev.glovesquest.main.object.monster.type;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class MonsterDrawBitmapInfo {
    private ArrayList<Bitmap> monsterImgList = new ArrayList<Bitmap>();
    private int canvasPage = 0;
    private int canvasLastPage = 0;
    private int width = 0;
    private int height = 0;
    private int rotate = 0;
    private int repeat = -1;
    private int crtX = 0;
    private int crtY = 0;
    private int frameDelay = 1;
    private boolean fading = false;
    private int fadeCount;
    private int fadeSpeed = 3;
    private int frame = 0;
    private int stiffenTime = 7;
    private int stiffenCount = 0;
    private MonsterMotionGroup monsterMotionGroup;

    public ArrayList<Bitmap> getMonsterImgList() {
        return monsterImgList;
    }

    public void setMonsterImgList(ArrayList<Bitmap> monsterImgList) {
        this.monsterImgList = monsterImgList;
    }

    public int getCanvasPage() {
        return canvasPage;
    }

    public void setCanvasPage(int canvasPage) {
        this.canvasPage = canvasPage;
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

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
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

    public boolean isFading() {
        return fading;
    }

    public void setFading(boolean fading) {
        this.fading = fading;
    }

    public int getFadeCount() {
        return fadeCount;
    }

    public void setFadeCount(int fadeCount) {
        this.fadeCount = fadeCount;
    }

    public int getFadeSpeed() {
        return fadeSpeed;
    }

    public void setFadeSpeed(int fadeSpeed) {
        this.fadeSpeed = fadeSpeed;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getStiffenTime() {
        return stiffenTime;
    }

    public void setStiffenTime(int stiffenTime) {
        this.stiffenTime = stiffenTime;
    }

    public int getStiffenCount() {
        return stiffenCount;
    }

    public void setStiffenCount(int stiffenCount) {
        this.stiffenCount = stiffenCount;
    }

    public MonsterMotionGroup getMonsterMotionGroup() {
        return monsterMotionGroup;
    }

    public void setMonsterMotionGroup(MonsterMotionGroup monsterMotionGroup) {
        this.monsterMotionGroup = monsterMotionGroup;
    }

    public void addMonsterImgBitmap(Bitmap monsterImg){
        monsterImgList.add(monsterImg);
    }
}
