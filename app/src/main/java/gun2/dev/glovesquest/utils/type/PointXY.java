package gun2.dev.glovesquest.utils.type;

public class PointXY {
    private int x, y;

    public PointXY() {
    }

    public PointXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PointXY setAndGetPoint(int x, int y){
        this.x = x;
        this.y = y;

        return this;
    }


    public int[] getPoint(){

        int result[] = {x,y};

        return result;
    }

    @Override
    public String toString() {
        return "PointXY{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
