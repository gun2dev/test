package gun2.dev.glovesquest.main.object.monster.type;

import java.util.ArrayList;

public class MonsterGenManager {
    private MonsterShareData monsterShareData;
    private int genProbability = 10000;
    private int monsterGenTime;
    private ArrayList<CreatedMonster> createdMonsterList = new ArrayList<CreatedMonster>();
    private int monsterLimit;

    public MonsterShareData getMonsterShareData() {
        return monsterShareData;
    }

    public void setMonsterShareData(MonsterShareData monsterShareData) {
        this.monsterShareData = monsterShareData;
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

    public ArrayList<CreatedMonster> getCreatedMonsterList() {
        return createdMonsterList;
    }

    public void setCreatedMonsterList(ArrayList<CreatedMonster> createdMonsterList) {
        this.createdMonsterList = createdMonsterList;
    }

    public int getMonsterLimit() {
        return monsterLimit;
    }

    public void setMonsterLimit(int monsterLimit) {
        this.monsterLimit = monsterLimit;
    }
}
