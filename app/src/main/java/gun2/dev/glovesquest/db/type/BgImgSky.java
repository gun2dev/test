package gun2.dev.glovesquest.db.type;

import io.realm.RealmObject;

public class BgImgSky extends RealmObject {
    private String img;
    private int directionX = 1;
    private int directionY = 0;
    private int frameDelay = 1;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    @Override
    public String toString() {
        return "BgImgSky{" +
                "img='" + img + '\'' +
                ", directionX=" + directionX +
                ", directionY=" + directionY +
                ", frameDelay=" + frameDelay +
                '}';
    }
}
