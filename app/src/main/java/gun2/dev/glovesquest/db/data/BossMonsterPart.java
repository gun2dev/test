package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BossMonsterPart extends RealmObject {
    @PrimaryKey
    private int id;
    private Text name;
    private int locationX = 0;
    private int locationY = 0;
    private int jointX;
    private int jointY;
    private int widthScale;
    private int heightScale;
    private long rewardGold;
    private int atPoint;
    private int defense;
    private long health;
    private long recovery;
    private int reflexes;
    private RealmList<ImgAnim> imgList;
    private RealmList<ImgAnim> struckImgList;
    private RealmList<ImgAnim> deadImgList;
    private RealmList<PerBossMonsterSkill> perBossMonsterSkillList;
    private RealmList<ItemDrop> itemDropList;
    private long exp;
    private long time;

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

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public int getJointX() {
        return jointX;
    }

    public void setJointX(int jointX) {
        this.jointX = jointX;
    }

    public int getJointY() {
        return jointY;
    }

    public void setJointY(int jointY) {
        this.jointY = jointY;
    }

    public int getWidthScale() {
        return widthScale;
    }

    public void setWidthScale(int widthScale) {
        this.widthScale = widthScale;
    }

    public int getHeightScale() {
        return heightScale;
    }

    public void setHeightScale(int heightScale) {
        this.heightScale = heightScale;
    }

    public long getRewardGold() {
        return rewardGold;
    }

    public void setRewardGold(long rewardGold) {
        this.rewardGold = rewardGold;
    }

    public int getAtPoint() {
        return atPoint;
    }

    public void setAtPoint(int atPoint) {
        this.atPoint = atPoint;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public long getRecovery() {
        return recovery;
    }

    public void setRecovery(long recovery) {
        this.recovery = recovery;
    }

    public int getReflexes() {
        return reflexes;
    }

    public void setReflexes(int reflexes) {
        this.reflexes = reflexes;
    }

    public RealmList<ImgAnim> getImgList() {
        return imgList;
    }

    public void setImgList(RealmList<ImgAnim> imgList) {
        this.imgList = imgList;
    }

    public RealmList<ImgAnim> getStruckImgList() {
        return struckImgList;
    }

    public void setStruckImgList(RealmList<ImgAnim> struckImgList) {
        this.struckImgList = struckImgList;
    }

    public RealmList<ImgAnim> getDeadImgList() {
        return deadImgList;
    }

    public void setDeadImgList(RealmList<ImgAnim> deadImgList) {
        this.deadImgList = deadImgList;
    }

    public RealmList<PerBossMonsterSkill> getPerBossMonsterSkillList() {
        return perBossMonsterSkillList;
    }

    public void setPerBossMonsterSkillList(RealmList<PerBossMonsterSkill> perBossMonsterSkillList) {
        this.perBossMonsterSkillList = perBossMonsterSkillList;
    }

    public RealmList<ItemDrop> getItemDropList() {
        return itemDropList;
    }

    public void setItemDropList(RealmList<ItemDrop> itemDropList) {
        this.itemDropList = itemDropList;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BossMonsterPart{" +
                "id=" + id +
                ", name=" + name +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                ", jointX=" + jointX +
                ", jointY=" + jointY +
                ", widthScale=" + widthScale +
                ", heightScale=" + heightScale +
                ", rewardGold=" + rewardGold +
                ", atPoint=" + atPoint +
                ", defense=" + defense +
                ", health=" + health +
                ", recovery=" + recovery +
                ", reflexes=" + reflexes +
                ", imgList=" + imgList +
                ", struckImgList=" + struckImgList +
                ", deadImgList=" + deadImgList +
                ", perBossMonsterSkillList=" + perBossMonsterSkillList +
                ", itemDropList=" + itemDropList +
                ", exp=" + exp +
                ", time=" + time +
                '}';
    }
}
