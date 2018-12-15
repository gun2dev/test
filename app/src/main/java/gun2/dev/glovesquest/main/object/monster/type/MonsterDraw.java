package gun2.dev.glovesquest.main.object.monster.type;


import java.util.ArrayList;

public class MonsterDraw {

    public MonsterDraw() {
    }

    private ArrayList<MonsterDrawInfo> defaultImgList = new ArrayList<MonsterDrawInfo>();
    private ArrayList<MonsterDrawInfo> deadImgList = new ArrayList<MonsterDrawInfo>();
    private ArrayList<MonsterDrawInfo> superCastingImgList = new ArrayList<MonsterDrawInfo>();
    private ArrayList<MonsterDrawInfo> struckImgList = new ArrayList<MonsterDrawInfo>();
    private ArrayList<MonsterDrawInfo> getGenImgList = new ArrayList<MonsterDrawInfo>();

    public ArrayList<MonsterDrawInfo> getDefaultImgList() {
        return defaultImgList;
    }

    public void setDefaultImgList(ArrayList<MonsterDrawInfo> defaultImgList) {
        this.defaultImgList = defaultImgList;
    }

    public ArrayList<MonsterDrawInfo> getDeadImgList() {
        return deadImgList;
    }

    public void setDeadImgList(ArrayList<MonsterDrawInfo> deadImgList) {
        this.deadImgList = deadImgList;
    }

    public ArrayList<MonsterDrawInfo> getSuperCastingImgList() {
        return superCastingImgList;
    }

    public void setSuperCastingImgList(ArrayList<MonsterDrawInfo> superCastingImgList) {
        this.superCastingImgList = superCastingImgList;
    }

    public ArrayList<MonsterDrawInfo> getStruckImgList() {
        return struckImgList;
    }

    public void setStruckImgList(ArrayList<MonsterDrawInfo> struckImgList) {
        this.struckImgList = struckImgList;
    }

    public ArrayList<MonsterDrawInfo> getGetGenImgList() {
        return getGenImgList;
    }

    public void setGetGenImgList(ArrayList<MonsterDrawInfo> getGenImgList) {
        this.getGenImgList = getGenImgList;
    }
}
