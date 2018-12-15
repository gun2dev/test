package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Monster extends RealmObject {

    @PrimaryKey
    private int id;
    private Text name;
    private int monsterSize;
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
    private RealmList<ImgAnim> genImgList;
    private MonsterSkillAlgorithm monsterSkillAlgorithm;
//    private ImgAnim skillCastImgList;
//    private ImgAnim skillFireImgList;
    private RealmList<ItemDrop> itemDropList;
    private long exp;
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

    public int getMonsterSize() {
        return monsterSize;
    }

    public void setMonsterSize(int monsterSize) {
        this.monsterSize = monsterSize;
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

    public RealmList<ImgAnim> getGenImgList() {
        return genImgList;
    }

    public void setGenImgList(RealmList<ImgAnim> genImgList) {
        this.genImgList = genImgList;
    }

    public MonsterSkillAlgorithm getMonsterSkillAlgorithm() {
        return monsterSkillAlgorithm;
    }

    public void setMonsterSkillAlgorithm(MonsterSkillAlgorithm monsterSkillAlgorithm) {
        this.monsterSkillAlgorithm = monsterSkillAlgorithm;
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
}
