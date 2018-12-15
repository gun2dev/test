package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CrtSkill extends RealmObject {
    @PrimaryKey
    private int id;
    private Text name;
    private Text desc;
    private ImgAnim thumImg;
    private RealmList<ImgAnim> castImgList;
    private RealmList<ImgAnim> fireImgList;
    private int coolTime;
    private int frontDelay;
    private int backDelay;
    private int immortalTime;
    private int useStamina;
    private int requiredReborn;
    private int skillType;
    private int skillPower;
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

    public Text getDesc() {
        return desc;
    }

    public void setDesc(Text desc) {
        this.desc = desc;
    }

    public ImgAnim getThumImg() {
        return thumImg;
    }

    public void setThumImg(ImgAnim thumImg) {
        this.thumImg = thumImg;
    }

    public RealmList<ImgAnim> getCastImgList() {
        return castImgList;
    }

    public void setCastImgList(RealmList<ImgAnim> castImgList) {
        this.castImgList = castImgList;
    }

    public RealmList<ImgAnim> getFireImgList() {
        return fireImgList;
    }

    public void setFireImgList(RealmList<ImgAnim> fireImgList) {
        this.fireImgList = fireImgList;
    }

    public int getCoolTime() {
        return coolTime;
    }

    public void setCoolTime(int coolTime) {
        this.coolTime = coolTime;
    }

    public int getFrontDelay() {
        return frontDelay;
    }

    public void setFrontDelay(int frontDelay) {
        this.frontDelay = frontDelay;
    }

    public int getBackDelay() {
        return backDelay;
    }

    public void setBackDelay(int backDelay) {
        this.backDelay = backDelay;
    }

    public int getImmortalTime() {
        return immortalTime;
    }

    public void setImmortalTime(int immortalTime) {
        this.immortalTime = immortalTime;
    }

    public int getUseStamina() {
        return useStamina;
    }

    public void setUseStamina(int useStamina) {
        this.useStamina = useStamina;
    }

    public int getRequiredReborn() {
        return requiredReborn;
    }

    public void setRequiredReborn(int requiredReborn) {
        this.requiredReborn = requiredReborn;
    }

    public int getSkillType() {
        return skillType;
    }

    public void setSkillType(int skillType) {
        this.skillType = skillType;
    }

    public int getSkillPower() {
        return skillPower;
    }

    public void setSkillPower(int skillPower) {
        this.skillPower = skillPower;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CrtSkill{" +
                "id=" + id +
                ", name=" + name +
                ", desc=" + desc +
                ", thumImg=" + thumImg +
                ", castImgList=" + castImgList +
                ", fireImgList=" + fireImgList +
                ", coolTime=" + coolTime +
                ", frontDelay=" + frontDelay +
                ", backDelay=" + backDelay +
                ", immortalTime=" + immortalTime +
                ", useStamina=" + useStamina +
                ", requiredReborn=" + requiredReborn +
                ", skillType=" + skillType +
                ", skillPower=" + skillPower +
                ", time=" + time +
                '}';
    }
}
