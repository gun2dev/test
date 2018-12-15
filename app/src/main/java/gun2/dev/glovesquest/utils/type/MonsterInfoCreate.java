package gun2.dev.glovesquest.utils.type;

import java.util.ArrayList;

public class MonsterInfoCreate {

    private PointXY baseLocation;
    private ArrayList<MonsterDrawObj> monsterDrawObj;

    public PointXY getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(PointXY baseLocation) {
        this.baseLocation = baseLocation;
    }

    public ArrayList<MonsterDrawObj> getMonsterDrawObj() {
        return monsterDrawObj;
    }

    public void setMonsterDrawObj(ArrayList<MonsterDrawObj> monsterDrawObj) {
        this.monsterDrawObj = monsterDrawObj;
    }

}
