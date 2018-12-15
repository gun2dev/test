package gun2.dev.glovesquest.utils;

import android.content.Context;
import android.graphics.Paint;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;

import gun2.dev.glovesquest.utils.type.PointXY;

public class DeviceEnvironment {
    public DeviceEnvironment(Context context) {
        // 화면 해상도 구하기
        Display display = ((WindowManager) context.getSystemService(context.WINDOW_SERVICE))
                .getDefaultDisplay();

        /**
         * 화면 사이즈 계산
         */
        sDeviceWidth = display.getWidth(); //화면 전체 크기
        sDeviceHeight = display.getHeight();
        sDeviceWidthCenter = sDeviceWidth / 2;  // 화면의 중심점
        sDeviceHeightCenter = sDeviceHeight / 2;
        sLogicalWidth = sDeviceWidth/100;
        sLogicalHeight = sDeviceHeight/100;
        sMonsterWidth = sLogicalWidth*33;
        sMonsterHeight = sLogicalHeight*20;



        int monsterFrontLine = sDeviceHeightCenter + sLogicalHeight*20;
        int monsterBackLine = sDeviceHeightCenter;
        //몬스터 좌표 구하기
        for (int i = 0; i < 5; i++) {
            ArrayList<PointXY> pointXYList = new ArrayList<PointXY>();
            if (i == 0) {
                PointXY pointXY1 = new PointXY();

                pointXY1.setPoint(sDeviceWidthCenter, monsterFrontLine);
                pointXYList.add(pointXY1);
            } else if (i == 1) {
                PointXY pointXY1 = new PointXY();
                PointXY pointXY2 = new PointXY();

                pointXY1.setPoint(sDeviceWidth * 1 / 3, monsterFrontLine);
                pointXY2.setPoint(sDeviceWidth * 2 / 3, monsterFrontLine);

                pointXYList.add(pointXY1);
                pointXYList.add(pointXY2);

            } else if (i == 2) {
                PointXY pointXY1 = new PointXY();
                PointXY pointXY2 = new PointXY();
                PointXY pointXY3 = new PointXY();

                pointXY1.setPoint(sDeviceWidth * 2 / 4, monsterBackLine);
                pointXY2.setPoint(sDeviceWidth * 1 / 4, monsterFrontLine);
                pointXY3.setPoint(sDeviceWidth * 3 / 4, monsterFrontLine);


                pointXYList.add(pointXY1);
                pointXYList.add(pointXY2);
                pointXYList.add(pointXY3);

            } else if (i == 3) {
                PointXY pointXY1 = new PointXY();
                PointXY pointXY2 = new PointXY();
                PointXY pointXY3 = new PointXY();
                PointXY pointXY4 = new PointXY();

                pointXY1.setPoint(sDeviceWidth * 2 / 5, monsterBackLine);
                pointXY2.setPoint(sDeviceWidth * 4 / 5, monsterBackLine);
                pointXY3.setPoint(sDeviceWidth * 1 / 5, monsterFrontLine);
                pointXY4.setPoint(sDeviceWidth * 3 / 5, monsterFrontLine);

                pointXYList.add(pointXY1);
                pointXYList.add(pointXY2);
                pointXYList.add(pointXY3);
                pointXYList.add(pointXY4);

            } else if (i == 4) {
                PointXY pointXY1 = new PointXY();
                PointXY pointXY2 = new PointXY();
                PointXY pointXY3 = new PointXY();
                PointXY pointXY4 = new PointXY();
                PointXY pointXY5 = new PointXY();

                pointXY1.setPoint(sDeviceWidth * 2 / 6, monsterBackLine);
                pointXY2.setPoint(sDeviceWidth * 4 / 6, monsterBackLine);
                pointXY3.setPoint(sDeviceWidth * 1 / 6, monsterFrontLine);
                pointXY4.setPoint(sDeviceWidth * 3 / 6, monsterFrontLine);
                pointXY5.setPoint(sDeviceWidth * 5 / 6, monsterFrontLine);

                pointXYList.add(pointXY1);
                pointXYList.add(pointXY2);
                pointXYList.add(pointXY3);
                pointXYList.add(pointXY4);
                pointXYList.add(pointXY5);

            }
            sMonsterLocationList.add(pointXYList);
        }


        //알파값 미리 설정
        for (int i = 1; i < 11; i++) {
            Paint paint = new Paint();
            paint.setAlpha(255*i/10);
            sAlphaPaintSettingList.add(paint);
        }

    }

    public static int sDeviceWidth;
    public static int sDeviceHeight;

    public static int sDeviceWidthCenter;
    public static int sDeviceHeightCenter;

    //논리적 크기
    public static int sLogicalWidth;
    public static int sLogicalHeight;
    //일반 몬스터 크기 정의
    public static int sMonsterWidth;
    public static int sMonsterHeight;



    final public static int FRAME_PER_MILLIS = 30;

    public static int sFrame = 0;

    //몬스터 좌표
    public static ArrayList<ArrayList<PointXY>> sMonsterLocationList = new ArrayList<ArrayList<PointXY>>();

    //투명율
    public static ArrayList<Paint> sAlphaPaintSettingList = new ArrayList<Paint>();


    static public void runFrame(){
        sFrame++;
    }


}
