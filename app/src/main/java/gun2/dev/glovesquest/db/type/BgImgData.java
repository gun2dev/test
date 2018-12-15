package gun2.dev.glovesquest.db.type;

import io.realm.RealmObject;

public class BgImgData extends RealmObject {
    private String mainImg;
    private ImgAnim animImg;

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public ImgAnim getAnimImg() {
        return animImg;
    }

    public void setAnimImg(ImgAnim animImg) {
        this.animImg = animImg;
    }

    @Override
    public String toString() {
        return "BgImgClose{" +
                "mainImg='" + mainImg + '\'' +
                ", animImg=" + animImg +
                '}';
    }
}