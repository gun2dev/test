package gun2.dev.glovesquest.db.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DmgIncreaseInfo extends RealmObject {
    @PrimaryKey
    private int id = 1001;
    private int reborn;
    private int ctrLevel;
    private int rbLevel;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReborn() {
        return reborn;
    }

    public void setReborn(int reborn) {
        this.reborn = reborn;
    }

    public int getCtrLevel() {
        return ctrLevel;
    }

    public void setCtrLevel(int ctrLevel) {
        this.ctrLevel = ctrLevel;
    }

    public int getRbLevel() {
        return rbLevel;
    }

    public void setRbLevel(int rbLevel) {
        this.rbLevel = rbLevel;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "DmgIncreaseInfo{" +
                "id=" + id +
                ", reborn=" + reborn +
                ", ctrLevel=" + ctrLevel +
                ", rbLevel=" + rbLevel +
                ", time=" + time +
                '}';
    }
}
