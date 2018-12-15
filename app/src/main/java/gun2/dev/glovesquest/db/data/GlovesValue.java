package gun2.dev.glovesquest.db.data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GlovesValue extends RealmObject {
    @PrimaryKey
    private int id;
    private RealmList<Long> valueList;
    private int upgradeLimit;
    private int enchantResetTwig;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Long> getValueList() {
        return valueList;
    }

    public void setValueList(RealmList<Long> valueList) {
        this.valueList = valueList;
    }

    public int getUpgradeLimit() {
        return upgradeLimit;
    }

    public void setUpgradeLimit(int upgradeLimit) {
        this.upgradeLimit = upgradeLimit;
    }

    public int getEnchantResetTwig() {
        return enchantResetTwig;
    }

    public void setEnchantResetTwig(int enchantResetTwig) {
        this.enchantResetTwig = enchantResetTwig;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GlovesValue{" +
                "id=" + id +
                ", valueList=" + valueList +
                ", upgradeLimit=" + upgradeLimit +
                ", enchantResetTwig=" + enchantResetTwig +
                ", time=" + time +
                '}';
    }
}
