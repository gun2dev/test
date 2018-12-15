package gun2.dev.glovesquest.main.object.monster.skill.type;

import android.graphics.Paint;

import gun2.dev.glovesquest.utils.type.PointXY;

public class MonsterSkillAnimInfo {

    private MonsterSkillDrawInfo backSkillAnim;
    private MonsterSkillDrawInfo frontSkillAnim;
    private PointXY backPoint;
    private PointXY frontPoint;
    private Paint effect;

    public MonsterSkillDrawInfo getBackSkillAnim() {
        return backSkillAnim;
    }

    public void setBackSkillAnim(MonsterSkillDrawInfo backSkillAnim) {
        this.backSkillAnim = backSkillAnim;
    }

    public MonsterSkillDrawInfo getFrontSkillAnim() {
        return frontSkillAnim;
    }

    public void setFrontSkillAnim(MonsterSkillDrawInfo frontSkillAnim) {
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
}
