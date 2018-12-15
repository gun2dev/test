package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Robot extends RealmObject {

    @PrimaryKey
    private int id;
    private int maxCount;
    private Text name;
    private RealmList<ImgAnim> frontImgList;
    private ImgAnim thumImgList;
    private long price;
    private RbLevelUpPrice levelUpPrice;
    private int levelLimit;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public RealmList<ImgAnim> getFrontImgList() {
        return frontImgList;
    }

    public void setFrontImgList(RealmList<ImgAnim> frontImgList) {
        this.frontImgList = frontImgList;
    }

    public ImgAnim getThumImgList() {
        return thumImgList;
    }

    public void setThumImgList(ImgAnim thumImgList) {
        this.thumImgList = thumImgList;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public RbLevelUpPrice getLevelUpPrice() {
        return levelUpPrice;
    }

    public void setLevelUpPrice(RbLevelUpPrice levelUpPrice) {
        this.levelUpPrice = levelUpPrice;
    }

    public int getLevelLimit() {
        return levelLimit;
    }

    public void setLevelLimit(int levelLimit) {
        this.levelLimit = levelLimit;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "id=" + id +
                ", maxCount=" + maxCount +
                ", name=" + name +
                ", frontImgList=" + frontImgList +
                ", thumImgList=" + thumImgList +
                ", price=" + price +
                ", levelUpPrice=" + levelUpPrice +
                ", levelLimit=" + levelLimit +
                ", time=" + time +
                '}';
    }
}
