package gun2.dev.glovesquest.main.object.monster.skill.type;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class MonsterSkillDrawInfo {
    private ArrayList<Bitmap> skillImgList;
    private int canvasLastPage = 0;
    private int width = 0;
    private int height = 0;
    private int repeat = -1;
    private int crtX = 0;
    private int crtY = 0;
    private int frameDelay = 1;
    private int sizeDifferWidth = 0;
    private int sizeDifferHeight = 0;

    public ArrayList<Bitmap> getSkillImgList() {
        return skillImgList;
    }

    public void setSkillImgList(ArrayList<Bitmap> skillImgList) {
        this.skillImgList = skillImgList;
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

    public int getSizeDifferWidth() {
        return sizeDifferWidth;
    }

    public void setSizeDifferWidth(int sizeDifferWidth) {
        this.sizeDifferWidth = sizeDifferWidth;
    }

    public int getSizeDifferHeight() {
        return sizeDifferHeight;
    }

    public void setSizeDifferHeight(int sizeDifferHeight) {
        this.sizeDifferHeight = sizeDifferHeight;
    }
}
