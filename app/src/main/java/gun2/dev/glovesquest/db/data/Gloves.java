package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Gloves extends RealmObject {

    @PrimaryKey
    private int id;
    private Text name;
    private RealmList<ImgAnim> imgWearList;
    private ImgAnim imgThum;
    private RealmList<ImgAnim> imgAttackList;
    private RealmList<ImgAnim> attackEffectList;
    private RealmList<String> attackSoundList;
    private long goldPrice;
    private long twigPrice;
    private long eggPrice;
    private boolean share = false;
    private GlovesValue value;
    private GlovesGrade grade;
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

    public RealmList<ImgAnim> getImgWearList() {
        return imgWearList;
    }

    public void setImgWearList(RealmList<ImgAnim> imgWearList) {
        this.imgWearList = imgWearList;
    }

    public ImgAnim getImgThum() {
        return imgThum;
    }

    public void setImgThum(ImgAnim imgThum) {
        this.imgThum = imgThum;
    }

    public RealmList<ImgAnim> getImgAttackList() {
        return imgAttackList;
    }

    public void setImgAttackList(RealmList<ImgAnim> imgAttackList) {
        this.imgAttackList = imgAttackList;
    }

    public RealmList<ImgAnim> getAttackEffectList() {
        return attackEffectList;
    }

    public void setAttackEffectList(RealmList<ImgAnim> attackEffectList) {
        this.attackEffectList = attackEffectList;
    }

    public RealmList<String> getAttackSoundList() {
        return attackSoundList;
    }

    public void setAttackSoundList(RealmList<String> attackSoundList) {
        this.attackSoundList = attackSoundList;
    }

    public long getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(long goldPrice) {
        this.goldPrice = goldPrice;
    }

    public long getTwigPrice() {
        return twigPrice;
    }

    public void setTwigPrice(long twigPrice) {
        this.twigPrice = twigPrice;
    }

    public long getEggPrice() {
        return eggPrice;
    }

    public void setEggPrice(long eggPrice) {
        this.eggPrice = eggPrice;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public GlovesValue getValue() {
        return value;
    }

    public void setValue(GlovesValue value) {
        this.value = value;
    }

    public GlovesGrade getGrade() {
        return grade;
    }

    public void setGrade(GlovesGrade grade) {
        this.grade = grade;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Gloves{" +
                "id=" + id +
                ", name=" + name +
                ", imgWearList=" + imgWearList +
                ", imgThum=" + imgThum +
                ", imgAttackList=" + imgAttackList +
                ", attackEffectList=" + attackEffectList +
                ", attackSoundList=" + attackSoundList +
                ", goldPrice=" + goldPrice +
                ", twigPrice=" + twigPrice +
                ", eggPrice=" + eggPrice +
                ", share=" + share +
                ", value=" + value +
                ", grade=" + grade +
                ", time=" + time +
                '}';
    }
}
