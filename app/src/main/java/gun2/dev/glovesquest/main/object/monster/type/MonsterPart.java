package gun2.dev.glovesquest.main.object.monster.type;


import java.util.ArrayList;

public class MonsterPart {

    public MonsterPart() {
    }

    private ArrayList<MonsterPartDrawInfo> defaultImgList = new ArrayList<MonsterPartDrawInfo>();
    private ArrayList<MonsterPartDrawInfo> deadImgList = new ArrayList<MonsterPartDrawInfo>();
    private ArrayList<MonsterPartDrawInfo> superCastingImgList = new ArrayList<MonsterPartDrawInfo>();
    private ArrayList<MonsterPartDrawInfo> struckImgList = new ArrayList<MonsterPartDrawInfo>();
    private ArrayList<MonsterPartDrawInfo> getGenImgList = new ArrayList<MonsterPartDrawInfo>();
    private int currentX = 0;
    private int currentY = 0;

    public ArrayList<MonsterPartDrawInfo> getDefaultImgList() {
        return defaultImgList;
    }

    public void setDefaultImgList(ArrayList<MonsterPartDrawInfo> defaultImgList) {
        this.defaultImgList = defaultImgList;
    }

    public ArrayList<MonsterPartDrawInfo> getDeadImgList() {
        return deadImgList;
    }

    public void setDeadImgList(ArrayList<MonsterPartDrawInfo> deadImgList) {
        this.deadImgList = deadImgList;
    }

    public ArrayList<MonsterPartDrawInfo> getSuperCastingImgList() {
        return superCastingImgList;
    }

    public void setSuperCastingImgList(ArrayList<MonsterPartDrawInfo> superCastingImgList) {
        this.superCastingImgList = superCastingImgList;
    }

    public ArrayList<MonsterPartDrawInfo> getStruckImgList() {
        return struckImgList;
    }

    public void setStruckImgList(ArrayList<MonsterPartDrawInfo> struckImgList) {
        this.struckImgList = struckImgList;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }


    public ArrayList<MonsterPartDrawInfo> getGetGenImgList() {
        return getGenImgList;
    }

    public void setGetGenImgList(ArrayList<MonsterPartDrawInfo> getGenImgList) {
        this.getGenImgList = getGenImgList;
    }



}
