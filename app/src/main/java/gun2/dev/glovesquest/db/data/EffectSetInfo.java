package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class EffectSetInfo extends RealmObject {

    @PrimaryKey
    private int id = 1001;
    private Text title;
    private Text desc;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Text getTitle() {
        return title;
    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getDesc() {
        return desc;
    }

    public void setDesc(Text desc) {
        this.desc = desc;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "EffectSetInfo{" +
                "id=" + id +
                ", title=" + title +
                ", desc=" + desc +
                ", time=" + time +
                '}';
    }
}
