package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Egg extends RealmObject {

    @PrimaryKey
    private int id = 1001;
    private ImgAnim frontImg;
    private ImgAnim thumImg;
    private long initValue;
    private short pay;
    private long limit;
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

    public short getPay() {
        return pay;
    }

    public void setPay(short pay) {
        this.pay = pay;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Egg{" +
                "id=" + id +
                ", initValue=" + initValue +
                ", pay=" + pay +
                ", limit=" + limit +
                ", time=" + time +
                '}';
    }
}
