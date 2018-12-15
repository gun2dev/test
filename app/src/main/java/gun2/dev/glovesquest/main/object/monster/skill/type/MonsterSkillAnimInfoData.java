package gun2.dev.glovesquest.main.object.monster.skill.type;

import android.graphics.Paint;

import gun2.dev.glovesquest.utils.type.PointXY;

public class MonsterSkillAnimInfoData {
    private MonsterSkillDrawInfoData backSkillAnim;
    private MonsterSkillDrawInfoData frontSkillAnim;
    private PointXY backPoint;
    private PointXY frontPoint;
    private Paint effect;
    private int fadeCount = 0;
    private int fadeSpeed = 3;

    public MonsterSkillDrawInfoData getBackSkillAnim() {
        return backSkillAnim;
    }

    public void setBackSkillAnim(MonsterSkillDrawInfoData backSkillAnim) {
        this.backSkillAnim = backSkillAnim;
    }

    public MonsterSkillDrawInfoData getFrontSkillAnim() {
        return frontSkillAnim;
    }

    public void setFrontSkillAnim(MonsterSkillDrawInfoData frontSkillAnim) {
        this.frontSkillAnim = frontSkillAnim;
    }

    public PointXY getBackPoint() {
        return backPoint;
    }

    public void setBackPoint(PointXY backPoint) {
        this.backPoint = backPoint;
    }

    public PointXY getFrontPoint() {
        return frontPoint;
    }

    public void setFrontPoint(PointXY frontPoint) {
        this.frontPoint = frontPoint;
    }

    public Paint getEffect() {
        return effect;
    }

    public void setEffect(Paint effect) {
        this.effect = effect;
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
}
