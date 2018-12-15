package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import io.realm.RealmList;
import io.realm.RealmObject;

public class PerBossMonsterSkill extends RealmObject {

    private MonsterSkill monsterSkill;
    private RealmList<ImgAnim> skillCastImgList;
    private RealmList<ImgAnim> skillFireImgList;
    private int skillRange;
    private int AlertTime;
    private long time = System.currentTimeMillis();

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

    public int getSkillRange() {
        return skillRange;
    }

    public void setSkillRange(int skillRange) {
        this.skillRange = skillRange;
    }

    public int getAlertTime() {
        return AlertTime;
    }

    public void setAlertTime(int alertTime) {
        AlertTime = alertTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PerBossMonsterSkill{" +
                "monsterSkill=" + monsterSkill +
                ", skillCastImgList=" + skillCastImgList +
                ", skillFireImgList=" + skillFireImgList +
                ", skillRange=" + skillRange +
                ", AlertTime=" + AlertTime +
                ", time=" + time +
                '}';
    }
}
