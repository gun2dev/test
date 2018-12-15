package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmObject;

public class MonsterHuntQuestCondition extends RealmObject {
    private Text title;
    private Text desc;
    private int goal;
    private boolean repeat;
    private long time = System.currentTimeMillis();

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

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MonsterHuntQuestCondition{" +
                "title=" + title +
                ", desc=" + desc +
                ", goal=" + goal +
                ", repeat=" + repeat +
                ", time=" + time +
                '}';
    }
}
