package gun2.dev.glovesquest.db.type;

import io.realm.RealmObject;

public class LogicalPoint extends RealmObject {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "LogicalPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
