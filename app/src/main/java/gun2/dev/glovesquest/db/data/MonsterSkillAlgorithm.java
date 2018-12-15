package gun2.dev.glovesquest.db.data;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MonsterSkillAlgorithm extends RealmObject {
    private RealmList<PerMonsterSkill> defaultSkillList;
    private RealmList<PerMonsterSkill> monsterSkillList;
    private RealmList<PerMonsterSkill> startSkillList;
    private int skillProbability = 10000;

    public RealmList<PerMonsterSkill> getDefaultSkillList() {
        return defaultSkillList;
    }

    public void setDefaultSkillList(RealmList<PerMonsterSkill> defaultSkillList) {
        this.defaultSkillList = defaultSkillList;
    }

    public RealmList<PerMonsterSkill> getMonsterSkillList() {
        return monsterSkillList;
    }

    public void setMonsterSkillList(RealmList<PerMonsterSkill> monsterSkillList) {
        this.monsterSkillList = monsterSkillList;
    }

    public RealmList<PerMonsterSkill> getStartSkillList() {
        return startSkillList;
    }

    public void setStartSkillList(RealmList<PerMonsterSkill> startSkillList) {
        this.startSkillList = startSkillList;
    }

    public int getSkillProbability() {
        return skillProbability;
    }

    public void setSkillProbability(int skillProbability) {
        this.skillProbability = skillProbability;
    }
}
