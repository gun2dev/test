package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SpecialStage extends RealmObject {

    @PrimaryKey
    private int stage;
    private Text name;
    private String bgImg;
    private String bgm;
    private RealmList<Monster> monsterList;
    private int monsterGen;
    private long Time = System.currentTimeMillis();

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getBgm() {
        return bgm;
    }

    public void setBgm(String bgm) {
        this.bgm = bgm;
    }

    public RealmList<Monster> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(RealmList<Monster> monsterList) {
        this.monsterList = monsterList;
    }

    public int getMonsterGen() {
        return monsterGen;
    }

    public void setMonsterGen(int monsterGen) {
        this.monsterGen = monsterGen;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "SpecialStage{" +
                "stage=" + stage +
                ", name=" + name +
                ", bgImg='" + bgImg + '\'' +
                ", bgm='" + bgm + '\'' +
                ", monsterList=" + monsterList +
                ", monsterGen=" + monsterGen +
                ", Time=" + Time +
                '}';
    }
}
