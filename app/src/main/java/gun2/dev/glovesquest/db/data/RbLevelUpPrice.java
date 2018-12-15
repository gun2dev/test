package gun2.dev.glovesquest.db.data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RbLevelUpPrice extends RealmObject {
    @PrimaryKey
    private int id;
    private RealmList<Long> priceList;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Long> getPriceList() {
        return priceList;
    }

    public void setPriceList(RealmList<Long> priceList) {
        this.priceList = priceList;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RbLevelUpPrice{" +
                "id=" + id +
                ", priceList=" + priceList +
                ", time=" + time +
                '}';
    }
}
