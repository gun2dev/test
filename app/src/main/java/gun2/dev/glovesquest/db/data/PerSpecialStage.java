package gun2.dev.glovesquest.db.data;

import io.realm.RealmObject;

public class PerSpecialStage extends RealmObject {
    private SpecialStage specialStage;
    private Boolean complete = false;
    private Boolean enterable = false;
    private int count;
    private long record;
    private long time = System.currentTimeMillis();

    public SpecialStage getSpecialStage() {
        return specialStage;
    }

    public void setSpecialStage(SpecialStage specialStage) {
        this.specialStage = specialStage;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Boolean getEnterable() {
        return enterable;
    }

    public void setEnterable(Boolean enterable) {
        this.enterable = enterable;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getRecord() {
        return record;
    }

    public void setRecord(long record) {
        this.record = record;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PerSpecialStage{" +
                "specialStage=" + specialStage +
                ", complete=" + complete +
                ", enterable=" + enterable +
                ", count=" + count +
                ", record=" + record +
                ", time=" + time +
                '}';
    }
}
