package gun2.dev.glovesquest.main.object.monster.skill.type;

public class MonsterReSizeDataType {
    public MonsterReSizeDataType(int monsterSize, int monsterWidthSize, int monsterHeightSize, int baseCrtX, int baseCrtY){
        this.monsterSize = monsterSize;
        this.monsterWidthSize = monsterWidthSize;
        this.monsterHeightSize = monsterHeightSize;
        this.baseCrtX = baseCrtX;
        this.baseCrtY = baseCrtY;
    }
    private int monsterSize;
    private int monsterWidthSize;
    private int monsterHeightSize;
    private int baseCrtX;
    private int baseCrtY;

    public int getMonsterSize() {
        return monsterSize;
    }

    public void setMonsterSize(int monsterSize) {
        this.monsterSize = monsterSize;
    }

    public int getMonsterWidthSize() {
        return monsterWidthSize;
    }

    public void setMonsterWidthSize(int monsterWidthSize) {
        this.monsterWidthSize = monsterWidthSize;
    }

    public int getMonsterHeightSize() {
        return monsterHeightSize;
    }

    public void setMonsterHeightSize(int monsterHeightSize) {
        this.monsterHeightSize = monsterHeightSize;
    }

    public MonsterReSizeDataType() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public int getBaseCrtX() {
        return baseCrtX;
    }

    public void setBaseCrtX(int baseCrtX) {
        this.baseCrtX = baseCrtX;
    }

    public int getBaseCrtY() {
        return baseCrtY;
    }

    public void setBaseCrtY(int baseCrtY) {
        this.baseCrtY = baseCrtY;
    }
}
