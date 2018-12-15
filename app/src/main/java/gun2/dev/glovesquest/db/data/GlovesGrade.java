package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GlovesGrade extends RealmObject {

    @PrimaryKey
    private int id;
    private Text grade;
    private RealmList<Float> probabilityList;
    private int dmgPlus;
    private long time = System.currentTimeMillis();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Text getGrade() {
        return grade;
    }

    public void setGrade(Text grade) {
        this.grade = grade;
    }

    public RealmList<Float> getProbabilityList() {
        return probabilityList;
    }

    public void setProbabilityList(RealmList<Float> probabilityList) {
        this.probabilityList = probabilityList;
    }

    public int getDmgPlus() {
        return dmgPlus;
    }

    public void setDmgPlus(int dmgPlus) {
        this.dmgPlus = dmgPlus;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GlovesGrade{" +
                "id=" + id +
                ", grade=" + grade +
                ", probabilityList=" + probabilityList +
                ", dmgPlus=" + dmgPlus +
                ", time=" + time +
                '}';
    }
}
