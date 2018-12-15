package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MonsterHuntQuest extends RealmObject {
    @PrimaryKey
    private int id;
    private Text title;
    private Text desc;
    private RealmList<MonsterHuntQuestCondition> conditionList;
    private QuestReward reward;
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

    public RealmList<MonsterHuntQuestCondition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(RealmList<MonsterHuntQuestCondition> conditionList) {
        this.conditionList = conditionList;
    }

    public QuestReward getReward() {
        return reward;
    }

    public void setReward(QuestReward reward) {
        this.reward = reward;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MonsterHuntQuest{" +
                "id=" + id +
                ", title=" + title +
                ", desc=" + desc +
                ", conditionList=" + conditionList +
                ", reward=" + reward +
                ", time=" + time +
                '}';
    }
}
