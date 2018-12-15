package gun2.dev.glovesquest.db.type;

import io.realm.RealmObject;

public class MonsterSkillAnim extends RealmObject {

    private ImgAnim backSkillAnim;
    private ImgAnim frontSkillAnim;
    private String effect;
    private LogicalPoint backPoint;
    private LogicalPoint frontPoint;
    private int backSkillSize;
    private int frontSkillSize;

    public ImgAnim getBackSkillAnim() {
        return backSkillAnim;
    }

    public void setBackSkillAnim(ImgAnim backSkillAnim) {
        this.backSkillAnim = backSkillAnim;
    }

    public ImgAnim getFrontSkillAnim() {
        return frontSkillAnim;
    }

    public void setFrontSkillAnim(ImgAnim frontSkillAnim) {
        this.frontSkillAnim = frontSkillAnim;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public LogicalPoint getBackPoint() {
        return backPoint;
    }

    public void setBackPoint(LogicalPoint backPoint) {
        this.backPoint = backPoint;
    }

    public LogicalPoint getFrontPoint() {
        return frontPoint;
    }

    public void setFrontPoint(LogicalPoint frontPoint) {
        this.frontPoint = frontPoint;
    }

    public int getBackSkillSize() {
        return backSkillSize;
    }

    public void setBackSkillSize(int backSkillSize) {
        this.backSkillSize = backSkillSize;
    }

    public int getFrontSkillSize() {
        return frontSkillSize;
    }

    public void setFrontSkillSize(int frontSkillSize) {
        this.frontSkillSize = frontSkillSize;
    }
}
