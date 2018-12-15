package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;

public class QuestReward extends RealmObject {
    private Text title;
    private RealmList<Gloves> glovesList;
    private long gold;
    private long twig;
    private long time = System.currentTimeMillis();

    public Text getTitle() {
        return title;
    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public RealmList<Gloves> getGlovesList() {
        return glovesList;
    }

    public void setGlovesList(RealmList<Gloves> glovesList) {
        this.glovesList = glovesList;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getTwig() {
        return twig;
    }

    public void setTwig(long twig) {
        this.twig = twig;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "QuestReward{" +
                "title=" + title +
                ", glovesList=" + glovesList +
                ", gold=" + gold +
                ", twig=" + twig +
                ", time=" + time +
                '}';
    }
}
