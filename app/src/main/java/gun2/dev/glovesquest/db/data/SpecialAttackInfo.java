package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SpecialAttackInfo extends RealmObject {
    @PrimaryKey
    private int id;
    private Text name;
    private int initValue;
    private int limit;
    private int eggPrice;
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

    public int getInitValue() {
        return initValue;
    }

    public void setInitValue(int initValue) {
        this.initValue = initValue;
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
        return "SpecialAttackInfo{" +
                "id=" + id +
                ", name=" + name +
                ", initValue=" + initValue +
                ", limit=" + limit +
                ", eggPrice=" + eggPrice +
                ", increase=" + increase +
                ", time=" + time +
                '}';
    }
}
