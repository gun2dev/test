package gun2.dev.glovesquest.main.object.monster.type;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.type.PointXY;

public class MonsterDeadAction {
    public MonsterDeadAction(ArrayList<MonsterDrawInfoData> monsterDeadImgList, int deadPointX, int deadPointY){
        this.monsterDeadImgList = monsterDeadImgList;
        this.deadPointX = deadPointX;
        this.deadPointY = deadPointY;

        for (MonsterDrawInfoData monsterDrawInfoData : monsterDeadImgList){
            monsterDrawInfoDataTypeList.add(new MonsterDrawInfoDataType());
        }
    }
    private ArrayList<MonsterDrawInfoData> monsterDeadImgList;
    private boolean fading = false;
    private int fadeCount = 10;
    private int fadeSpeed = 1;
    private int deadPointX = 0;
    private int deadPointY = 0;
    private int frame = 0;
    private boolean end = false;
    private ArrayList<MonsterDrawInfoDataType> monsterDrawInfoDataTypeList = new ArrayList<MonsterDrawInfoDataType>();


    public ArrayList<MonsterDrawInfoData> getMonsterDeadImgList() {
        return monsterDeadImgList;
    }

    public void setMonsterDeadImgList(ArrayList<MonsterDrawInfoData> monsterDeadImgList) {
        this.monsterDeadImgList = monsterDeadImgList;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
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

    public int getFadeSpeed() {
        return fadeSpeed;
    }

    public void setFadeSpeed(int fadeSpeed) {
        this.fadeSpeed = fadeSpeed;
    }

    public int getDeadPointX() {
        return deadPointX;
    }

    public void setDeadPointX(int deadPointX) {
        this.deadPointX = deadPointX;
    }

    public int getDeadPointY() {
        return deadPointY;
    }

    public void setDeadPointY(int deadPointY) {
        this.deadPointY = deadPointY;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    //default action
    public void defaultDeadDrawAction(Canvas canvas) {
        Paint paint = getFadeOutPaintGen();
        for (int i = 0; i < monsterDeadImgList.size(); i ++){
            PointXY pointXY = monsterDeadImgList.get(i).getMotionPoint();

            canvas.drawBitmap(monsterDeadImgList.get(i).getCurrentMonsterDeadImgBitmap(
                    frame, monsterDrawInfoDataTypeList.get(i)),
                    deadPointX  + pointXY.getX(),
                    deadPointY  + pointXY.getY()
                    , paint);
        }
        frame++;
    }

    //페이드 아웃 설정
    public Paint getFadeOutPaintGen() {
        if (frame % 3 == 0) fadeCount -= fadeSpeed;
        if (fading == false) {
            //초기 시작
            fading = true;
            fadeCount = 9;
        }
        if (fadeCount <= 0) {
            //종료
            //몬스터가 젠 상태면 일반 상태로 변경
            fadeCount = 0;
            fading = false;
            end = true;
        }
        return DeviceEnvironment.sAlphaPaintSettingList.get(fadeCount);
    }
}
