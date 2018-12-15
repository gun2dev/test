package gun2.dev.glovesquest.db.type;

import io.realm.RealmObject;

public class Text extends RealmObject {
    private String en;  //English
    private String ko;  //korean
    private String ja;  //japanese

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getKo() {
        return ko;
    }

    public void setKo(String ko) {
        this.ko = ko;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    @Override
    public String toString() {
        return "Text{" +
                "en='" + en + '\'' +
                ", ko='" + ko + '\'' +
                ", ja='" + ja + '\'' +
                '}';
    }
}
