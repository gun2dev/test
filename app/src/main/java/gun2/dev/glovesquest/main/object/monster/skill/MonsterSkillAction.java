package gun2.dev.glovesquest.main.object.monster.skill;

import android.graphics.Canvas;

import java.util.ArrayList;

import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillAnimInfoData;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillDrawInfoData;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillInfo;
import gun2.dev.glovesquest.main.object.monster.type.CreatedMonster;
import gun2.dev.glovesquest.utils.type.PointXY;

public class MonsterSkillAction {
    private int alertTime;
    private ArrayList<Integer> skillRange;
    private ArrayList<MonsterSkillDrawInfoData> monsterFireImgList;
    private ArrayList<MonsterSkillDrawInfoData> monsterCastImgList;
    private MonsterSkillInfo monsterSkillInfo;
    private MonsterSkillAnimInfoData skillFireImg;
    private MonsterSkillAnimInfoData skillCastImg;
    private int timeFlow = 0;
    private CreatedMonster createdMonster;


    public int getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(int alertTime) {
        this.alertTime = alertTime;
    }

    public ArrayList<Integer> getSkillRange() {
        return skillRange;
    }

    public void setSkillRange(ArrayList<Integer> skillRange) {
        this.skillRange = skillRange;
    }

    public ArrayList<MonsterSkillDrawInfoData> getMonsterFireImgList() {
        return monsterFireImgList;
    }

    public void setMonsterFireImgList(ArrayList<MonsterSkillDrawInfoData> monsterFireImgList) {
        this.monsterFireImgList = monsterFireImgList;
    }

    public ArrayList<MonsterSkillDrawInfoData> getMonsterCastImgList() {
        return monsterCastImgList;
    }

    public void setMonsterCastImgList(ArrayList<MonsterSkillDrawInfoData> monsterCastImgList) {
        this.monsterCastImgList = monsterCastImgList;
    }

    public MonsterSkillInfo getMonsterSkillInfo() {
        return monsterSkillInfo;
    }

    public void setMonsterSkillInfo(MonsterSkillInfo monsterSkillInfo) {
        this.monsterSkillInfo = monsterSkillInfo;
    }

    public MonsterSkillAnimInfoData getSkillFireImg() {
        return skillFireImg;
    }

    public void setSkillFireImg(MonsterSkillAnimInfoData skillFireImg) {
        this.skillFireImg = skillFireImg;
    }

    public int getTimeFlow() {
        return timeFlow;
    }

    public void setTimeFlow(int timeFlow) {
        this.timeFlow = timeFlow;
    }

    public CreatedMonster getCreatedMonster() {
        return createdMonster;
    }

    public void setCreatedMonster(CreatedMonster createdMonster) {
        this.createdMonster = createdMonster;
    }

    public MonsterSkillAnimInfoData getSkillCastImg() {
        return skillCastImg;
    }

    public void setSkillCastImg(MonsterSkillAnimInfoData skillCastImg) {
        this.skillCastImg = skillCastImg;
    }

    public void drawAction(Canvas canvas) {

        int frame = createdMonster.getFrame();

        //스킬 캐스팅 중이면
        if (timeFlow <= monsterSkillInfo.getSkillCastDel()) {
            //스킬 이펙트 (back)
            {
                PointXY pointXY = skillCastImg.getBackPoint();
                canvas.drawBitmap(skillCastImg.getBackSkillAnim().getCurrentMonsterSkillImgBitmap(frame),
                        pointXY.getX() + createdMonster.getCurrentX(),
                        pointXY.getY() + createdMonster.getCurrentY(),
                        null);

            }
            //몬스터 스킬 모션
            for (MonsterSkillDrawInfoData monsterSkillDrawInfoData : monsterCastImgList) {
                PointXY pointXY = monsterSkillDrawInfoData.getMotionGroup().getCurrentPointInGroup();
                canvas.drawBitmap(monsterSkillDrawInfoData.getCurrentMonsterSkillImgBitmap(frame),
                        pointXY.getX() + createdMonster.getCurrentX() +
                                monsterSkillDrawInfoData.getMonsterSkillDrawInfo().getSizeDifferWidth(),
                        pointXY.getY() + createdMonster.getCurrentY() +
                                monsterSkillDrawInfoData.getMonsterSkillDrawInfo().getSizeDifferHeight(),
                        null);
            }

        } else {
            //스킬 캐스팅이 끝나면 스킬 발사 모션 후 상태 변경
            for (MonsterSkillDrawInfoData monsterSkillDrawInfoData : monsterCastImgList) {
                PointXY pointXY = monsterSkillDrawInfoData.getMotionGroup().getCurrentPointInGroup();
                canvas.drawBitmap(monsterSkillDrawInfoData.getCurrentMonsterSkillImgBitmap(frame),
                        pointXY.getX() + createdMonster.getCurrentX() +
                                monsterSkillDrawInfoData.getMonsterSkillDrawInfo().getSizeDifferWidth(),
                        pointXY.getY() + createdMonster.getCurrentY() +
                                monsterSkillDrawInfoData.getMonsterSkillDrawInfo().getSizeDifferHeight(),
                        null);
            }
            createdMonster.setCurrentState(0);
        }

        timeFlow++;
    }
}
