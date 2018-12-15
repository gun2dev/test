package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Gold extends RealmObject {
    @PrimaryKey
    private int id = 1001;
    private long initValue;
    private long limit;
    private ImgAnim frontImg;
    private ImgAnim thumImg;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getInitValue() {
        return initValue;
    }

    public void setInitValue(long initValue) {
        this.initValue = initValue;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public ImgAnim getFrontImg() {
        return frontImg;
    }

    public void setFrontImg(ImgAnim frontImg) {
        this.frontImg = frontImg;
    }

    public ImgAnim getThumImg() {
        return thumImg;
    }

    public void setThumImg(ImgAnim thumImg) {
        this.thumImg = thumImg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Gold{" +
                "id=" + id +
                ", initValue=" + initValue +
                ", limit=" + limit +
                ", frontImg=" + frontImg +
                ", thumImg=" + thumImg +
                ", time=" + time +
                '}';
    }
}
