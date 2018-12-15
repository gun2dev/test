package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.BgImg;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Stage extends RealmObject {
    @PrimaryKey
    private int stage;
    private Text name;
    private BgImg bgImg;
    private String bgm;
    private RealmList<MonsterGen> monsterGenList;
    private long time = System.currentTimeMillis();

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

    public BgImg getBgImg() {
        return bgImg;
    }

    public void setBgImg(BgImg bgImg) {
        this.bgImg = bgImg;
    }

    public String getBgm() {
        return bgm;
    }

    public void setBgm(String bgm) {
        this.bgm = bgm;
    }

    public RealmList<MonsterGen> getMonsterGenList() {
        return monsterGenList;
    }

    public void setMonsterGenList(RealmList<MonsterGen> monsterGenList) {
        this.monsterGenList = monsterGenList;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "stage=" + stage +
                ", name=" + name +
                ", bgImg=" + bgImg +
                ", bgm='" + bgm + '\'' +
                ", monsterGenList=" + monsterGenList +
                ", time=" + time +
                '}';
    }
}
