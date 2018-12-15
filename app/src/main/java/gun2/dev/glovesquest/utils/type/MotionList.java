package gun2.dev.glovesquest.utils.type;

public class MotionList {
    public PointXY motionApply(int x, int y, String motion, long count, int range){
        PointXY pointXY = new PointXY();

        int c = (int)(count%range);
        switch (motion){

            case "upDownMotion01":
                if (c < range/2){
                    y--;
                } else {
                    y++;
                }
                break;

            case "sideMotion01":
                int moveX[] = {
                        1, 1, 1, 1, 1,
                        -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1,
                        1, 1, 1, 1, 1
                };
                x += moveX[c];
                break;

        }



        pointXY.setPoint(x, y);
        return pointXY;

    }
}
