package gun2.dev.glovesquest.utils;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import java.util.ArrayList;

import gun2.dev.glovesquest.utils.type.PointXY;

public class GameDataSetting {
    public GameDataSetting() {
        /**
         * Monster data set
         */
        {
            Paint paint = new Paint();
            paint.setColorFilter(
                    new ColorMatrixColorFilter(
                            new ColorMatrix(new float[]{
                                    0.5f, 0, 0, 0, 0,
                                    0, 1, 0, 0, 0,
                                    0, 0, 1, 0, 0,
                                    0, 0, 0, 1, 0,
                                    0, 0, 0, 0, 1}
                            )
                    )
            );
            sMonsterStruckColorFilterPaintList.add(paint);
        }
        {
            Paint paint = new Paint();
            paint.setColorFilter(
                    new ColorMatrixColorFilter(
                            new ColorMatrix(new float[]{
                                    0, 0, 0, 0, 0,
                                    0, 1, 0, 0, 0,
                                    0, 0, 1, 0, 0,
                                    0, 0, 0, 1, 0,
                                    0, 0, 0, 0, 1}
                            )
                    )
            );
            sMonsterStruckColorFilterPaintList.add(paint);
        }
    }

    /**
     * Monster data
     */
    //struck set
    static public ArrayList<Paint> sMonsterStruckColorFilterPaintList =
            new ArrayList<Paint>(); //Paint set with ColorFilter when monster is struck
    static public int sMonsterStruckFilterDelay = 3;






    //몬스터 모션값 설정용 만약 몬스터 정보 생성시 각기 스레드로 동작할 경우 사용하지 말것
    static public ArrayList<PointXY> sGetMonsterMotionList(String motion) {
        ArrayList<PointXY> motionResultList = new ArrayList<>();
        switch (motion) {
            case "upDownMotion01":
                motionResultList.add(new PointXY().setAndGetPoint(0, -1));
                motionResultList.add(new PointXY().setAndGetPoint(0, -1));
                motionResultList.add(new PointXY().setAndGetPoint(0, -1));
                motionResultList.add(new PointXY().setAndGetPoint(0, -1));
                motionResultList.add(new PointXY().setAndGetPoint(0, -1));

                motionResultList.add(new PointXY().setAndGetPoint(0, -1));
                motionResultList.add(new PointXY().setAndGetPoint(0, -1));
                motionResultList.add(new PointXY().setAndGetPoint(0, -1));
                motionResultList.add(new PointXY().setAndGetPoint(0, -1));
                motionResultList.add(new PointXY().setAndGetPoint(0, -1));

                motionResultList.add(new PointXY().setAndGetPoint(0, 1));
                motionResultList.add(new PointXY().setAndGetPoint(0, 1));
                motionResultList.add(new PointXY().setAndGetPoint(0, 1));
                motionResultList.add(new PointXY().setAndGetPoint(0, 1));
                motionResultList.add(new PointXY().setAndGetPoint(0, 1));
                break;

            case "sideMotion01":
                motionResultList.add(new PointXY().setAndGetPoint(1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(1, 0));

                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));

                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(-1, 0));

                motionResultList.add(new PointXY().setAndGetPoint(1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(1, 0));
                motionResultList.add(new PointXY().setAndGetPoint(1, 0));

                break;

            case "baseStruckMotion01":
                motionResultList.add(new PointXY().setAndGetPoint(3, 0));
                motionResultList.add(new PointXY().setAndGetPoint(-3, 0));
                motionResultList.add(new PointXY().setAndGetPoint(3, 2));
                motionResultList.add(new PointXY().setAndGetPoint(3, -3));
                motionResultList.add(new PointXY().setAndGetPoint(0, 2));

            default:
                motionResultList.add(new PointXY().setAndGetPoint(0, 0));
                break;
        }

        return motionResultList;

    }
}
