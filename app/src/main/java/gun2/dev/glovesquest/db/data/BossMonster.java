package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BossMonster extends RealmObject {
    @PrimaryKey
    private int id;
    private Text name;
    private long rewardGold;
    private RealmList<ItemDrop> itemDropList;
    private RealmList<BossMonsterPhase> bossMonsterPhaseList;
    private long exp;
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

    public long getRewardGold() {
        return rewardGold;
    }

    public void setRewardGold(long rewardGold) {
        this.rewardGold = rewardGold;
    }

    public RealmList<ItemDrop> getItemDropList() {
        return itemDropList;
    }

    public void setItemDropList(RealmList<ItemDrop> itemDropList) {
        this.itemDropList = itemDropList;
    }

    public RealmList<BossMonsterPhase> getBossMonsterPhaseList() {
        return bossMonsterPhaseList;
    }

    public void setBossMonsterPhaseList(RealmList<BossMonsterPhase> bossMonsterPhaseList) {
        this.bossMonsterPhaseList = bossMonsterPhaseList;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BossMonster{" +
                "id=" + id +
                ", name=" + name +
                ", rewardGold=" + rewardGold +
                ", itemDropList=" + itemDropList +
                ", bossMonsterPhaseList=" + bossMonsterPhaseList +
                ", exp=" + exp +
                ", time=" + time +
                '}';
    }
}
