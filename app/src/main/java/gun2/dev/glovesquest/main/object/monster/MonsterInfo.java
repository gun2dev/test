package gun2.dev.glovesquest.main.object.monster;

import java.util.ArrayList;

import gun2.dev.glovesquest.main.object.monster.type.CreatedMonster;
import gun2.dev.glovesquest.main.object.monster.type.MonsterDraw;
import gun2.dev.glovesquest.main.object.monster.type.MonsterPart;

public class MonsterInfo {
    public MonsterInfo() {

    }
    public interface Callback{
        void ChangeCurrentState(int i);
    }

    private ArrayList<CreatedMonster> createdMonsterList = new ArrayList<CreatedMonster>();



    private MonsterPart monsterPart;
    private MonsterDraw monsterDraw;
    private int currentState = 2;
    private int basePointX;
    private int basePointY;
    private int genProbability;
    private int monsterGenTime;

    public MonsterPart getMonsterPart() {
        return monsterPart;
    }

    public void setMonsterPart(MonsterPart monsterPart) {
        this.monsterPart = monsterPart;
    }

    public MonsterDraw getMonsterDraw() {
        return monsterDraw;
    }

    public void setMonsterDraw(MonsterDraw monsterDraw) {
        this.monsterDraw = monsterDraw;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getBasePointX() {
        return basePointX;
    }

    public void setBasePointX(int basePointX) {
        this.basePointX = basePointX;
    }

    public int getBasePointY() {
        return basePointY;
    }

    public void setBasePointY(int basePointY) {
        this.basePointY = basePointY;
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

    public void addCreatedMonster(CreatedMonster createdMonster){
        createdMonsterList.add(createdMonster);
    }

    //-------------
    //callback method
    //-------------
    //하위 객체가 몬스터 상태 변화 요청 시 사용 (ex: 몬스터 파트의 스킬 모션, 이미지 끝날때 변경)
    void ChangeCurrentState(int i){
        currentState = i;
    }
}
