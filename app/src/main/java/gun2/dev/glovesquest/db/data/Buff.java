package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Buff extends RealmObject {
    @PrimaryKey
    private int id;
    private Text name;
    private Text desc;
    private int value;
    private int duration;
    private boolean sellAble;
    private int price;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isSellAble() {
        return sellAble;
    }

    public void setSellAble(boolean sellAble) {
        this.sellAble = sellAble;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Buff{" +
                "id=" + id +
                ", name=" + name +
                ", desc=" + desc +
                ", value=" + value +
                ", duration=" + duration +
                ", sellAble=" + sellAble +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
