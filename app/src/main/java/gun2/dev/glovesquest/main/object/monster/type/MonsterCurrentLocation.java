package gun2.dev.glovesquest.main.object.monster.type;

import android.support.annotation.NonNull;

public class MonsterCurrentLocation implements Comparable<MonsterCurrentLocation> {
    public MonsterCurrentLocation(CreatedMonster createdMonster, int y){
        this.createdMonster = createdMonster;
        this.y = y;
    }
    private int y;
    private CreatedMonster createdMonster;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CreatedMonster getCreatedMonster() {
        return createdMonster;
    }

    public void setCreatedMonster(CreatedMonster createdMonster) {
        this.createdMonster = createdMonster;
    }

    @Override
    public int compareTo(@NonNull MonsterCurrentLocation o) {
        if (this.y < o.getY()){
            return -1;
        } else if(this.y > o. getY()){
            return 1;
        }
        return 0;
    }
}
