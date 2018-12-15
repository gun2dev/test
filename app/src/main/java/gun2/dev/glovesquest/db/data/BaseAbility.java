package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BaseAbility extends RealmObject {
    @PrimaryKey
    private int id;
    private Text name;
    private Text desc;
    private ImgAnim thumImg;
    private int initLevel;
    private int limit;
    private int eggPrice;
    private int eggPriceIncrease;
    private int increase;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public Text getDesc() {
        return desc;
    }

    public void setDesc(Text desc) {
        this.desc = desc;
    }

    public ImgAnim getThumImg() {
        return thumImg;
    }

    public void setThumImg(ImgAnim thumImg) {
        this.thumImg = thumImg;
    }

    public int getInitLevel() {
        return initLevel;
    }

    public void setInitLevel(int initLevel) {
        this.initLevel = initLevel;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getEggPrice() {
        return eggPrice;
    }

    public void setEggPrice(int eggPrice) {
        this.eggPrice = eggPrice;
    }

    public int getEggPriceIncrease() {
        return eggPriceIncrease;
    }

    public void setEggPriceIncrease(int eggPriceIncrease) {
        this.eggPriceIncrease = eggPriceIncrease;
    }

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BaseAbility{" +
                "id=" + id +
                ", name=" + name +
                ", desc=" + desc +
                ", thumImg=" + thumImg +
                ", initLevel=" + initLevel +
                ", limit=" + limit +
                ", eggPrice=" + eggPrice +
                ", eggPriceIncrease=" + eggPriceIncrease +
                ", increase=" + increase +
                ", time=" + time +
                '}';
    }
}
