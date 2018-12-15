package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.LogicalPoint;
import io.realm.RealmList;
import io.realm.RealmObject;

public class MonsterGen extends RealmObject {
    private Monster monster;
    private int genProbability = 100;
    private int monsterGenTime = 1;
    private RealmList<LogicalPoint> genPointList;
    private int monsterLimit = 5;

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public int getGenProbability() {
        return genProbability;
    }

    public void setGenProbability(int genProbability) {
        this.genProbability = genProbability;
    }

    public int getMonsterGenTime() {
        return monsterGenTime;
    }

    public void setMonsterGenTime(int monsterGenTime) {
        this.monsterGenTime = monsterGenTime;
    }

    public RealmList<LogicalPoint> getGenPointList() {
        return genPointList;
    }

    public void setGenPointList(RealmList<LogicalPoint> genPointList) {
        this.genPointList = genPointList;
    }

    public int getMonsterLimit() {
        return monsterLimit;
    }

    public void setMonsterLimit(int monsterLimit) {
        this.monsterLimit = monsterLimit;
    }

    @Override
    public String toString() {
        return "MonsterGen{" +
                "monster=" + monster +
                ", genProbability=" + genProbability +
                ", monsterGenTime=" + monsterGenTime +
                ", genPointList=" + genPointList +
                ", monsterLimit=" + monsterLimit +
                '}';
    }
}
