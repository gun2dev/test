package gun2.dev.glovesquest.main.object.monster.skill;

import java.util.ArrayList;

import gun2.dev.glovesquest.main.object.monster.skill.type.PerMonsterSkillInfo;

public class MonsterSkillAlgorithmInfo {
    private ArrayList<PerMonsterSkillInfo> defaultSkillList;
    private ArrayList<PerMonsterSkillInfo> monsterSkillList;
    private ArrayList<PerMonsterSkillInfo> startSkillList;
    private int skillProbability = 5000;

    public ArrayList<PerMonsterSkillInfo> getDefaultSkillList() {
        return defaultSkillList;
    }

    public void setDefaultSkillList(ArrayList<PerMonsterSkillInfo> defaultSkillList) {
        this.defaultSkillList = defaultSkillList;
    }

    public ArrayList<PerMonsterSkillInfo> getMonsterSkillList() {
        return monsterSkillList;
    }

    public void setMonsterSkillList(ArrayList<PerMonsterSkillInfo> monsterSkillList) {
        this.monsterSkillList = monsterSkillList;
    }

    public ArrayList<PerMonsterSkillInfo> getStartSkillList() {
        return startSkillList;
    }

    public void setStartSkillList(ArrayList<PerMonsterSkillInfo> startSkillList) {
        this.startSkillList = startSkillList;
    }

    public int getSkillProbability() {
        return skillProbability;
    }

    public void setSkillProbability(int skillProbability) {
        this.skillProbability = skillProbability;
    }
}
