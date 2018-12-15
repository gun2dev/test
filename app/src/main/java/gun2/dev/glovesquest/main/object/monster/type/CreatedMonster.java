package gun2.dev.glovesquest.main.object.monster.type;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import gun2.dev.glovesquest.db.type.Text;
import gun2.dev.glovesquest.main.object.monster.skill.MonsterSkillAction;
import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.GameDataSetting;
import gun2.dev.glovesquest.utils.type.PointXY;

public class CreatedMonster {

    private Text monsterName;
    private int currentState = 20;
    private int genPointX;
    private int genPointY;
    private int currentX;
    private int currentY;
    private int frame = 0;

    //gen
    private int fadeSpeed = 3;
    private boolean fading = false;
    private int fadeCount;

    //struck
    private int stiffenTime = 10;
    private int stiffenCount = 0;

    private ArrayList<MonsterDrawInfoData> defaultImgList;
    private ArrayList<MonsterDrawInfoData> deadImgList;
    private ArrayList<MonsterDrawInfoData> superCastingImgList;
    private ArrayList<MonsterDrawInfoData> struckImgList;
    private ArrayList<MonsterDrawInfoData> genImgList;
    private MonsterSkillAction monsterSkillAction;

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getGenPointX() {
        return genPointX;
    }

    public void setGenPointX(int genPointX) {
        this.genPointX = genPointX;
    }

    public int getGenPointY() {
        return genPointY;
    }

    public void setGenPointY(int genPointY) {
        this.genPointY = genPointY;
    }

    public ArrayList<MonsterDrawInfoData> getDefaultImgList() {
        return defaultImgList;
    }

    public void setDefaultImgList(ArrayList<MonsterDrawInfoData> defaultImgList) {
        this.defaultImgList = defaultImgList;
    }

    public ArrayList<MonsterDrawInfoData> getDeadImgList() {
        return deadImgList;
    }

    public void setDeadImgList(ArrayList<MonsterDrawInfoData> deadImgList) {
        this.deadImgList = deadImgList;
    }

    public ArrayList<MonsterDrawInfoData> getSuperCastingImgList() {
        return superCastingImgList;
    }

    public void setSuperCastingImgList(ArrayList<MonsterDrawInfoData> superCastingImgList) {
        this.superCastingImgList = superCastingImgList;
    }

    public ArrayList<MonsterDrawInfoData> getStruckImgList() {
        return struckImgList;
    }

    public void setStruckImgList(ArrayList<MonsterDrawInfoData> struckImgList) {
        this.struckImgList = struckImgList;
    }

    public ArrayList<MonsterDrawInfoData> getGenImgList() {
        return genImgList;
    }

    public void setGenImgList(ArrayList<MonsterDrawInfoData> genImgList) {
        this.genImgList = genImgList;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getFadeSpeed() {
        return fadeSpeed;
    }

    public void setFadeSpeed(int fadeSpeed) {
        this.fadeSpeed = fadeSpeed;
    }

    public boolean isFading() {
        return fading;
    }

    public void setFading(boolean fading) {
        this.fading = fading;
    }

    public int getFadeCount() {
        return fadeCount;
    }

    public void setFadeCount(int fadeCount) {
        this.fadeCount = fadeCount;
    }

    public MonsterSkillAction getMonsterSkillAction() {
        return monsterSkillAction;
    }

    public void setMonsterSkillAction(MonsterSkillAction monsterSkillAction) {
        this.monsterSkillAction = monsterSkillAction;
    }

    public Text getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(Text monsterName) {
        Text text = new Text();
        text.setEn(monsterName.getEn());
        text.setJa(monsterName.getJa());
        text.setKo(monsterName.getKo());
        this.monsterName = text;
    }

    public int getStiffenTime() {
        return stiffenTime;
    }

    public void setStiffenTime(int stiffenTime) {
        this.stiffenTime = stiffenTime;
    }

    public int getStiffenCount() {
        return stiffenCount;
    }

    public void setStiffenCount(int stiffenCount) {
        this.stiffenCount = stiffenCount;
    }

    //default action
    public void defaultDrawAction(Canvas canvas) {
        for (MonsterDrawInfoData monsterDrawInfoData : defaultImgList) {
            PointXY pointXY = monsterDrawInfoData.getMotionPoint();

            canvas.drawBitmap(monsterDrawInfoData.getCurrentMonsterImgBitmap(frame),
                    currentX + pointXY.getX(),
                    currentY + pointXY.getY()
                    , null);
        }

        frame++;
    }

    //gen action
    public void defaultGenDrawAction(Canvas canvas) {
        Paint paint = getFadeInPaintGen();
        for (MonsterDrawInfoData monsterDrawInfoData : defaultImgList) {
            PointXY pointXY = monsterDrawInfoData.getMotionPoint();

            canvas.drawBitmap(monsterDrawInfoData.getMonsterImgList().get(0),
                    currentX + pointXY.getX(),
                    currentY + pointXY.getY()
                    , paint);
        }

        frame++;
    }

    //페이드 인 설정
    public Paint getFadeInPaintGen() {
        if (frame % 3 == 0) fadeCount += fadeSpeed;
        if (fading == false) {
            //초기 시작
            fading = true;
            fadeCount = 0;
        }
        if (fadeCount >= 9) {
            //종료
            //몬스터가 젠 상태면 일반 상태로 변경
            if (currentState == 20) currentState = 0;
            fading = false;
        }
        return DeviceEnvironment.sAlphaPaintSettingList.get(fadeCount);
    }


    //struck action
    public void struckDrawAction(Canvas canvas) {
        for (MonsterDrawInfoData monsterDrawInfoData : struckImgList) {
            PointXY pointXY = monsterDrawInfoData.getMotionPoint();

            canvas.drawBitmap(monsterDrawInfoData.getCurrentMonsterImgBitmap(frame),
                    currentX + pointXY.getX(),
                    currentY + pointXY.getY()
                    , getStruckPaintFilter());
        }
        frame++;
    }

    public Paint getStruckPaintFilter() {
        if (frame % GameDataSetting.sMonsterStruckFilterDelay == 0) stiffenCount++;
        if (stiffenCount == stiffenTime) {
            //작동 중지
            currentState = 0;
            stiffenCount = 0;
        }
        return GameDataSetting.sMonsterStruckColorFilterPaintList.get(stiffenCount % 2);
    }

    public void skillDrawAction(Canvas canvas) {

        monsterSkillAction.drawAction(canvas);
        frame++;
    }
}
