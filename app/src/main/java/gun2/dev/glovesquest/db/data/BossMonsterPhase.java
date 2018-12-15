package gun2.dev.glovesquest.db.data;

import io.realm.RealmList;
import io.realm.RealmObject;

public class BossMonsterPhase extends RealmObject {
    private RealmList<BossMonsterPart> bossMonsterPartList;
    private int phaseCondition;
    private String phaseBgImg;
    private String phaseBgm;
    private String phaseEffect;
    private int immortalTime;
    private long time = System.currentTimeMillis();

    public RealmList<BossMonsterPart> getBossMonsterPartList() {
        return bossMonsterPartList;
    }

    public void setBossMonsterPartList(RealmList<BossMonsterPart> bossMonsterPartList) {
        this.bossMonsterPartList = bossMonsterPartList;
    }

    public int getPhaseCondition() {
        return phaseCondition;
    }

    public void setPhaseCondition(int phaseCondition) {
        this.phaseCondition = phaseCondition;
    }

    public String getPhaseBgImg() {
        return phaseBgImg;
    }

    public void setPhaseBgImg(String phaseBgImg) {
        this.phaseBgImg = phaseBgImg;
    }

    public String getPhaseBgm() {
        return phaseBgm;
    }

    public void setPhaseBgm(String phaseBgm) {
        this.phaseBgm = phaseBgm;
    }

    public String getPhaseEffect() {
        return phaseEffect;
    }

    public void setPhaseEffect(String phaseEffect) {
        this.phaseEffect = phaseEffect;
    }

    public int getImmortalTime() {
        return immortalTime;
    }

    public void setImmortalTime(int immortalTime) {
        this.immortalTime = immortalTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BossMonsterPhase{" +
                "bossMonsterPartList=" + bossMonsterPartList +
                ", phaseCondition=" + phaseCondition +
                ", phaseBgImg='" + phaseBgImg + '\'' +
                ", phaseBgm='" + phaseBgm + '\'' +
                ", phaseEffect='" + phaseEffect + '\'' +
                ", immortalTime=" + immortalTime +
                ", time=" + time +
                '}';
    }
}
