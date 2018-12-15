package gun2.dev.glovesquest.main.object.stage.type;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class BgImgDrawInfo {
    public BgImgDrawInfo() {
    }
    private Bitmap mainImg;
    private ArrayList<Bitmap> animImgList = new ArrayList<Bitmap>();
    private int frameDelay;
    private int currentPage = 0;
    private int lastPage;

    public Bitmap getMainImg() {
        return mainImg;
    }

    public void setMainImg(Bitmap mainImg) {
        this.mainImg = mainImg;
    }

    public ArrayList<Bitmap> getAnimImgList() {
        return animImgList;
    }

    public void setAnimImgList(ArrayList<Bitmap> animImgList) {
        this.animImgList = animImgList;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    public void addAnimImg(Bitmap bitmap){
        animImgList.add(bitmap);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }


}
