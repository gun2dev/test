package gun2.dev.glovesquest.db.data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CrtLevelUpExp extends RealmObject {
    @PrimaryKey
    private int id;
    private RealmList<Long> expList;
    private long initCurrentExp;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Long> getExpList() {
        return expList;
    }

    public void setExpList(RealmList<Long> expList) {
        this.expList = expList;
    }

    public long getInitCurrentExp() {
        return initCurrentExp;
    }

    public void setInitCurrentExp(long initCurrentExp) {
        this.initCurrentExp = initCurrentExp;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CrtLevelUpExp{" +
                "id=" + id +
                ", expList=" + expList +
                ", initCurrentExp=" + initCurrentExp +
                ", time=" + time +
                '}';
    }
}
