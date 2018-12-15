package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BossStage extends RealmObject {
    @PrimaryKey
    private int bossStage;
    private Text name;
    private String bgImg;
    private String bgm;
    private BossMonster bossMonster;
    private long time = System.currentTimeMillis();

    public int getBossStage() {
        return bossStage;
    }

    public void setBossStage(int bossStage) {
        this.bossStage = bossStage;
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

    public BossMonster getBossMonster() {
        return bossMonster;
    }

    public void setBossMonster(BossMonster bossMonster) {
        this.bossMonster = bossMonster;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BossStage{" +
                "bossStage=" + bossStage +
                ", name=" + name +
                ", bgImg='" + bgImg + '\'' +
                ", bgm='" + bgm + '\'' +
                ", bossMonster=" + bossMonster +
                ", time=" + time +
                '}';
    }
}
