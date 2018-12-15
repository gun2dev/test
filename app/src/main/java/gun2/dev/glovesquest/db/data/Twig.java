package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Twig extends RealmObject {

    @PrimaryKey
    private int id = 1001;
    private ImgAnim frontImg;
    private ImgAnim thumImg;
    private int limit;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Twig{" +
                "id=" + id +
                ", frontImg=" + frontImg +
                ", thumImg=" + thumImg +
                ", limit=" + limit +
                ", time=" + time +
                '}';
    }
}
