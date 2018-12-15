package gun2.dev.glovesquest.db.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Language extends RealmObject {

    @PrimaryKey
    private int id = 1001;
    private String code = "en";
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", time=" + time +
                '}';
    }
}
