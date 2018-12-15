package gun2.dev.glovesquest.main.object.monster.type;

import java.util.ArrayList;

import gun2.dev.glovesquest.main.object.monster.skill.MonsterSkillAlgorithmInfo;

public class MonsterShareData {
    private ArrayList<MonsterDrawInfo> monsterDefaultDrawInfoList;
    private ArrayList<MonsterDrawInfo> monsterDeadDrawInfoList;
    private ArrayList<MonsterDrawInfo> monsterStruckDrawInfoList;
    private ArrayList<MonsterDrawInfo> monsterGenDrawInfoList;
    private MonsterSkillAlgorithmInfo monsterSkillAlgorithmInfo;

    public MonsterSkillAlgorithmInfo getMonsterSkillAlgorithmInfo() {
        return monsterSkillAlgorithmInfo;
    }

    public void setMonsterSkillAlgorithmInfo(MonsterSkillAlgorithmInfo monsterSkillAlgorithmInfo) {
        this.monsterSkillAlgorithmInfo = monsterSkillAlgorithmInfo;
    }

    public ArrayList<MonsterDrawInfo> getMonsterDefaultDrawInfoList() {
        return monsterDefaultDrawInfoList;
    }

    public void setMonsterDefaultDrawInfoList(ArrayList<MonsterDrawInfo> monsterDefaultDrawInfoList) {
        this.monsterDefaultDrawInfoList = monsterDefaultDrawInfoList;
    }

    public ArrayList<MonsterDrawInfo> getMonsterDeadDrawInfoList() {
        return monsterDeadDrawInfoList;
    }

    public void setMonsterDeadDrawInfoList(ArrayList<MonsterDrawInfo> monsterDeadDrawInfoList) {
        this.monsterDeadDrawInfoList = monsterDeadDrawInfoList;
    }

    public ArrayList<MonsterDrawInfo> getMonsterStruckDrawInfoList() {
        return monsterStruckDrawInfoList;
    }

    public void setMonsterStruckDrawInfoList(ArrayList<MonsterDrawInfo> monsterStruckDrawInfoList) {
        this.monsterStruckDrawInfoList = monsterStruckDrawInfoList;
    }

    public ArrayList<MonsterDrawInfo> getMonsterGenDrawInfoList() {
        return monsterGenDrawInfoList;
    }

    public void setMonsterGenDrawInfoList(ArrayList<MonsterDrawInfo> monsterGenDrawInfoList) {
        this.monsterGenDrawInfoList = monsterGenDrawInfoList;
    }
}
