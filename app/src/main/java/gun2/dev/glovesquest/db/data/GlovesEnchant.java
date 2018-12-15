package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GlovesEnchant extends RealmObject {

    @PrimaryKey
    private int id;
    private Text name;
    private int min;
    private int max;
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

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GlovesEnchant{" +
                "id=" + id +
                ", name=" + name +
                ", min=" + min +
                ", max=" + max +
                ", time=" + time +
                '}';
    }
}
