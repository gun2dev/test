package gun2.dev.glovesquest.main.object.monster.skill.type;

import java.util.ArrayList;

public class PerMonsterSkillInfo {

    private MonsterSkillInfo monsterSkillInfo;
    private ArrayList<MonsterSkillDrawInfo> skillCastImgList;
    private ArrayList<MonsterSkillDrawInfo> skillFireImgList;
    private ArrayList<Integer> skillRange = new ArrayList<Integer>();
    private int alertTime;

    public MonsterSkillInfo getMonsterSkillInfo() {
        return monsterSkillInfo;
    }

    public void setMonsterSkillInfo(MonsterSkillInfo monsterSkillInfo) {
        this.monsterSkillInfo = monsterSkillInfo;
    }

    public ArrayList<MonsterSkillDrawInfo> getSkillCastImgList() {
        return skillCastImgList;
    }

    public void setSkillCastImgList(ArrayList<MonsterSkillDrawInfo> skillCastImgList) {
        this.skillCastImgList = skillCastImgList;
    }

    public ArrayList<MonsterSkillDrawInfo> getSkillFireImgList() {
        return skillFireImgList;
    }

    public void setSkillFireImgList(ArrayList<MonsterSkillDrawInfo> skillFireImgList) {
        this.skillFireImgList = skillFireImgList;
    }

    public ArrayList<Integer> getSkillRange() {
        return skillRange;
    }

    public void setSkillRange(ArrayList<Integer> skillRange) {
        this.skillRange = skillRange;
    }

    public int getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(int alertTime) {
        this.alertTime = alertTime;
    }
}
