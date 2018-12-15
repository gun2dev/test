package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.MonsterSkillAnim;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MonsterSkill extends RealmObject {

    @PrimaryKey
    private int id;
    private Text name;
    private ImgAnim thumImg;
    private MonsterSkillAnim skillCastImg;
    private MonsterSkillAnim skillFireImg;
    private RealmList<ImgAnim> skillEfImg;
    private String skillCastSound;
    private String skillFireSound;
    private int SkillEfPoint;
    private int duration;
    private int skillStateCode;
    private int skillCoolTime;
    private int skillCastDel = 3;
    private long Time = System.currentTimeMillis();

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

    public MonsterSkillAnim getSkillCastImg() {
        return skillCastImg;
    }

    public void setSkillCastImg(MonsterSkillAnim skillCastImg) {
        this.skillCastImg = skillCastImg;
    }

    public MonsterSkillAnim getSkillFireImg() {
        return skillFireImg;
    }

    public void setSkillFireImg(MonsterSkillAnim skillFireImg) {
        this.skillFireImg = skillFireImg;
    }

    public RealmList<ImgAnim> getSkillEfImg() {
        return skillEfImg;
    }

    public void setSkillEfImg(RealmList<ImgAnim> skillEfImg) {
        this.skillEfImg = skillEfImg;
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
        return SkillEfPoint;
    }

    public void setSkillEfPoint(int skillEfPoint) {
        SkillEfPoint = skillEfPoint;
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
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    public int getSkillCastDel() {
        return skillCastDel;
    }

    public void setSkillCastDel(int skillCastDel) {
        this.skillCastDel = skillCastDel;
    }
}
