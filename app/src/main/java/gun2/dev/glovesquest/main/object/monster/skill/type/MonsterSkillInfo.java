package gun2.dev.glovesquest.main.object.monster.skill.type;

public class MonsterSkillInfo {

    private int skillCastDel;
    private MonsterSkillAnimInfo skillCastImg;
    private MonsterSkillAnimInfo skillFireImg;
    private int duration;
    private int skillStateCode;
    private int skillCoolTime;

    public int getSkillCastDel() {
        return skillCastDel;
    }

    public void setSkillCastDel(int skillCastDel) {
        this.skillCastDel = skillCastDel;
    }

    public MonsterSkillAnimInfo getSkillCastImg() {
        return skillCastImg;
    }

    public void setSkillCastImg(MonsterSkillAnimInfo skillCastImg) {
        this.skillCastImg = skillCastImg;
    }

    public MonsterSkillAnimInfo getSkillFireImg() {
        return skillFireImg;
    }

    public void setSkillFireImg(MonsterSkillAnimInfo skillFireImg) {
        this.skillFireImg = skillFireImg;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSkillStateCode() {
        return skillStateCode;
    }

    public void setSkillStateCode(int skillStateCode) {
        this.skillStateCode = skillStateCode;
    }

    public int getSkillCoolTime() {
        return skillCoolTime;
    }

    public void setSkillCoolTime(int skillCoolTime) {
        this.skillCoolTime = skillCoolTime;
    }
}
