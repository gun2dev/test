package gun2.dev.glovesquest.db.data;

import io.realm.RealmObject;

public class ItemDrop extends RealmObject {

    private Gloves gloves;
    private long gold;
    private long twig;
    private Costume costume;
    private long egg;
    private float drop;
    private long time = System.currentTimeMillis();

    public Gloves getGloves() {
        return gloves;
    }

    public void setGloves(Gloves gloves) {
        this.gloves = gloves;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getTwig() {
        return twig;
    }

    public void setTwig(long twig) {
        this.twig = twig;
    }

    public Costume getCostume() {
        return costume;
    }

    public void setCostume(Costume costume) {
        this.costume = costume;
    }

    public long getEgg() {
        return egg;
    }

    public void setEgg(long egg) {
        this.egg = egg;
    }

    public float getDrop() {
        return drop;
    }

    public void setDrop(float drop) {
        this.drop = drop;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ItemDrop{" +
                "gloves=" + gloves +
                ", gold=" + gold +
                ", twig=" + twig +
                ", costume=" + costume +
                ", egg=" + egg +
                ", drop=" + drop +
                ", time=" + time +
                '}';
    }
}
