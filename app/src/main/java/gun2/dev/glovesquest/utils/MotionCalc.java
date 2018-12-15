package gun2.dev.glovesquest.utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import gun2.dev.glovesquest.utils.type.PointXY;

public class MotionCalc {
    public MotionCalc() {
    }

    static public ArrayList<PointXY> sGetMonsterMotionList(int x, int y, ArrayList<String> motionList) {
        //임시 저장값
        int maxSize = 0;
        ArrayList<PointXY> resultList = new ArrayList<PointXY>();
        ArrayList<ArrayList<PointXY>> tmpResultList = new ArrayList<ArrayList<PointXY>>();
        for (String str : motionList) {
            ArrayList<PointXY> pointXYArrayList = new ArrayList<PointXY>();
            pointXYArrayList = GameDataSetting.sGetMonsterMotionList(str);
            tmpResultList.add(pointXYArrayList);

            if (pointXYArrayList.size() > 0) {
                maxSize = pointXYArrayList.size();
            }
        }
        //결과물 합산
        for (int i = 0; i < maxSize; i++) {
            for (ArrayList<PointXY> pointXYArrayList : tmpResultList) {
                try {
                    x += pointXYArrayList.get(i).getX();
                    y += pointXYArrayList.get(i).getY();
                }catch (Exception e){
                    Log.d("MotionCalc", "skip");
                }
            }
            resultList.add(new PointXY().setAndGetPoint(x, y));
        }

        /////////////////
        return resultList;
    }


    static public ArrayList<PointXY> sGetMonsterMotion(int x, int y, String motion) {
        //임시 저장값
        ArrayList<PointXY> resultList = new ArrayList<PointXY>();

        ArrayList<PointXY> pointXYArrayList = GameDataSetting.sGetMonsterMotionList(motion);

        //결과물 합산
        for (PointXY pointXY : pointXYArrayList) {
            try {
                x += pointXY.getX();
                y += pointXY.getY();
            }catch (Exception e){
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                Log.d("MotionCalc : ", exceptionAsString);
            }
            resultList.add(new PointXY().setAndGetPoint(x, y));
        }

        return resultList;
    }


    //가져온 모든 배열 리스트를 계산
    static public ArrayList<PointXY> sMotionSum(ArrayList<ArrayList<PointXY>> tmpResultList, int maxSize, int x, int y) {
        ArrayList<PointXY> resultList = new ArrayList<PointXY>();

        for (int i = 0; i < maxSize; i++) {
            for (ArrayList<PointXY> pointXYArrayList : tmpResultList) {
                if (pointXYArrayList.get(i) != null){
                    x += pointXYArrayList.get(i).getX();
                    y += pointXYArrayList.get(i).getY();
                }
                resultList.add(new PointXY().setAndGetPoint(x, y));
            }

        }

        return resultList;
    }
}
