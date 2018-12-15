package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BossMonsterSkill extends RealmObject {

    @PrimaryKey
    private int id;
    private Text name;
    private ImgAnim thumImg;
    private int skillCastDel;
    private RealmList<ImgAnim> skillCastImgList;
    private RealmList<ImgAnim> skillFireImgList;
    private RealmList<ImgAnim> skillEfImgList;
    private String skillCastSound;
    private String skillFireSound;
    private int skillEfPoint;
    private int duration;
    private int skillStateCode;
    private int skillCoolTime;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public ImgAnim getThumImg() {
        return thumImg;
    }

    public void setThumImg(ImgAnim thumImg) {
        this.thumImg = thumImg;
    }

    public int getSkillCastDel() {
        return skillCastDel;
    }

    public void setSkillCastDel(int skillCastDel) {
        this.skillCastDel = skillCastDel;
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

    public RealmList<ImgAnim> getSkillEfImgList() {
        return skillEfImgList;
    }

    public void setSkillEfImgList(RealmList<ImgAnim> skillEfImgList) {
        this.skillEfImgList = skillEfImgList;
    }

    public String getSkillCastSound() {
        return skillCastSound;
    }

    public void setSkillCastSound(String skillCastSound) {
        this.skillCastSound = skillCastSound;
    }

    public String getSkillFireSound() {
        return skillFireSound;
    }

    public void setSkillFireSound(String skillFireSound) {
        this.skillFireSound = skillFireSound;
    }

    public int getSkillEfPoint() {
        return skillEfPoint;
    }

    public void setSkillEfPoint(int skillEfPoint) {
        this.skillEfPoint = skillEfPoint;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BossMonsterSkill{" +
                "id=" + id +
                ", name=" + name +
                ", thumImg=" + thumImg +
                ", skillCastDel=" + skillCastDel +
                ", skillCastImgList=" + skillCastImgList +
                ", skillFireImgList=" + skillFireImgList +
                ", skillEfImgList=" + skillEfImgList +
                ", skillCastSound='" + skillCastSound + '\'' +
                ", skillFireSound='" + skillFireSound + '\'' +
                ", skillEfPoint=" + skillEfPoint +
                ", duration=" + duration +
                ", skillStateCode=" + skillStateCode +
                ", skillCoolTime=" + skillCoolTime +
                ", time=" + time +
                '}';
    }
}
