package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import io.realm.RealmList;
import io.realm.RealmObject;

public class PerMonsterSkill extends RealmObject {

    private MonsterSkill monsterSkill;
    private RealmList<ImgAnim> skillCastImgList;
    private RealmList<ImgAnim> skillFireImgList;
    private RealmList<Long> skillRange;
    private int alertTime;
    private long Time = System.currentTimeMillis();

    public MonsterSkill getMonsterSkill() {
        return monsterSkill;
    }

    public void setMonsterSkill(MonsterSkill monsterSkill) {
        this.monsterSkill = monsterSkill;
    }

    public RealmList<ImgAnim> getSkillCastImgList() {
        return skillCastImgList;
    }

    public void setSkillCastImgList(RealmList<ImgAnim> skillCastImgList) {
        this.skillCastImgList = skillCastImgList;
    }

    public RealmList<ImgAnim> getSkillFireImgList() {
        return skillFireImgList;
    }

    public void setSkillFireImgList(RealmList<ImgAnim> skillFireImgList) {
        this.skillFireImgList = skillFireImgList;
    }

    public RealmList<Long> getSkillRange() {
        return skillRange;
    }

    public void setSkillRange(RealmList<Long> skillRange) {
        this.skillRange = skillRange;
    }

    public int getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(int alertTime) {
        this.alertTime = alertTime;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }
}
