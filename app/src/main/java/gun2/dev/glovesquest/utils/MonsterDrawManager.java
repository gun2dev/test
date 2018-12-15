package gun2.dev.glovesquest.utils;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

import gun2.dev.glovesquest.db.data.Monster;
import gun2.dev.glovesquest.utils.type.MonsterDrawObj;
import gun2.dev.glovesquest.utils.type.MonsterInfoCreate;
import io.realm.Realm;

public class MonsterDrawManager {

    private LogManager Log = new LogManager(getClass().getName().trim());

    private int mMediaWidth, mMediaHeight, mMediaCenterX, mMediaCenterY;

//    private PointXY[] mMonsterLocation;

//    private MonsterInfoCreate[] mMonsterInfoCreates = new MonsterInfoCreate[5];

    private ArrayList<MonsterInfoCreate> mMonsterInfoCreate = new ArrayList<MonsterInfoCreate>();

    private Resources mResource;

    //---------------
    //tmp zone
    //---------------
    private Paint mBackground;

    private long mFrame = 0;

    private Paint mMonsterNamePaint;
    String mMonsterName = "";

    //------------------
    //creator
    //------------------
    public MonsterDrawManager(int locationNum, Resources resources) {

//        mMonsterLocation[locationNum].setPoint();

        mMediaWidth = DeviceEnvironment.sDeviceWidth;
        mMediaHeight = DeviceEnvironment.sDeviceHeight;
        mMediaCenterX = DeviceEnvironment.sDeviceWidthCenter;
        mMediaCenterY = DeviceEnvironment.sDeviceHeightCenter;

        mResource = resources;

        mBackground = new Paint();
        mBackground.setColor(Color.BLACK);
        //몬스터 이름
        mMonsterNamePaint = new Paint();
        mMonsterNamePaint.setColor(Color.WHITE);
        mMonsterNamePaint.setTextSize(100);
        mMonsterNamePaint.setTextAlign(Paint.Align.CENTER);
        mMonsterNamePaint.setTypeface(Typeface.DEFAULT_BOLD);

    }

    //test
    public void setDrawMonster(Realm mRealm, int monsterId, int monsterNum) {

        //몬스터 이름 출력


        MonsterInfoCreate monsterInfoCreate = new MonsterInfoCreate();

        ArrayList<MonsterDrawObj> monsterDrawObjList = new ArrayList<MonsterDrawObj>();

        Monster monster = mRealm.where(Monster.class).equalTo("id", monsterId).findFirst();
        Log.msg("to String :" + monster.toString());
        Log.msg("getImg" + monster.getImgList().toString());
        Log.msg("monster Name : " + monster.getName().getKo());
        mMonsterName = monster.getName().getKo();

/*
        //몬스터 설정
        for (ImgAnim imgAnim : monster.getImgList()) {
            MonsterDrawObj monsterDrawObj = new MonsterDrawObj(mResource);
            Log.msg("00000");
            monsterDrawObj.setFrameDelay(imgAnim.getFrameDelay());

            //모션 설정 (덩어리)
            for (MotionSet motionSet : imgAnim.getMotionSetList()) {
                MotionAnimManager motionAnimManager = new MotionAnimManager();

                //모션 워크 설정 (각개)
                for (MotionAnim motionAnim : motionSet.getMotionAnimList()){
                    MotionWorkFlow motionWorkFlow = new MotionWorkFlow();
                    motionWorkFlow.setBackDelay(motionAnim.getBackDelay());
                    motionWorkFlow.setFrameDelay(motionAnim.getFrameDelay());
                    motionWorkFlow.setFrontDelay(motionAnim.getFrontDelay());
                    motionWorkFlow.setRepeat(motionAnim.getRepeat());

                    //Realm String 형 변환
                    for (String str : motionAnim.getMotionList()) {
                        motionWorkFlow.addMotion(str);
                    }
                    motionAnimManager.setMotionAnimList();
                }
            }

            //몬스터 이미지 설정
            for (String img : imgAnim.getImgList()) {
                Log.msg("monster img" + img);
                int resId = mResource.getIdentifier(img, "drawable", "gun2.dev.glovesquest");
                Log.msg("id : " + resId);
                monsterDrawObj.addRscToBitmap(resId);

            }
            monsterDrawObjList.add(monsterDrawObj);
        }
*/

/*
        //몬스터 설정
        for (int i = 0; i < monster.getImgList().size(); i++) {
            MonsterDrawObj monsterDrawObj = new MonsterDrawObj(mResource);
            Log.msg("00000");

            ImgAnim imgAnim = monster.getImgList().get(i);

            monsterDrawObj.setFrameDelay(imgAnim.getFrameDelay());

            MotionAnimManager motionAnimManager = new MotionAnimManager();
            //모션 설정 (덩어리)
            for (MotionSet motionSet : imgAnim.getMotionSetList()) {
                MotionWorkFlow motionWorkFlow = new MotionWorkFlow();
                motionAnimManager.setLastDelay(motionSet.getLastDelay());
                motionAnimManager.setStartDelay(motionSet.getStartDelay());
                motionAnimManager.setRepeat(motionSet.getRepeat());
                motionAnimManager.setRepeatDelay(motionSet.getRepeatDelay());

                //모션 워크 설정 (각개)
                for (MotionAnim motionAnim : motionSet.getMotionAnimList()){
                    motionWorkFlow.setBackDelay(motionAnim.getBackDelay());
                    motionWorkFlow.setFrameDelay(motionAnim.getFrameDelay());
                    motionWorkFlow.setFrontDelay(motionAnim.getFrontDelay());
                    motionWorkFlow.setRepeat(motionAnim.getRepeat());

                    //Realm String 형 변환
                    for (String str : motionAnim.getMotionList()) {
                        motionWorkFlow.addMotion(str);
                    }
                    motionAnimManager.addMotionWork(motionWorkFlow);
                }


            }

            //몬스터 이미지 설정
            for (String img : imgAnim.getImgList()) {
                Log.msg("monster img" + img);
                int resId = mResource.getIdentifier(img, "drawable", "gun2.dev.glovesquest");
                Log.msg("id : " + resId);
                //이미지 추가
                monsterDrawObj.addRscToBitmap(resId);
                //모션 추가
                monsterDrawObj.setMotionAnimManager(motionAnimManager);
            }

            monsterDrawObjList.add(monsterDrawObj);
        }

//        Log.msg(mMonsterInfoCreate.get(0).toString());


        monsterInfoCreate.setMonsterDrawObj(monsterDrawObjList);

        //tmp
        mMonsterInfoCreate = new ArrayList<MonsterInfoCreate>();
        mMonsterInfoCreate.add(monsterInfoCreate);

//        if (!mRealm.isClosed()) mRealm.close();

    }

    public void drawMonster(Canvas canvas){

        canvas.drawRect(0, 0, mMediaWidth, mMediaHeight, mBackground);
        canvas.drawText(mMonsterName, mMediaCenterX, 100, mMonsterNamePaint);
        //monster 1~5번

        for (int i = 0; i<5; i++){
            //monster setDrawMonster 가 설정되어있으면 그린다.
            if (mMonsterInfoCreate.size() != 0){
                for (MonsterDrawObj monsterDrawObj : mMonsterInfoCreate.get(0).getMonsterDrawObj()){
                    //프레임 딜레이
//                    Log.msg("mMonsterInfoCreate draw : "+ mFrame);
                    canvas.drawBitmap(monsterDrawObj.getMonsterCurrentBitmap(), monsterDrawObj.getCurrnetX(), monsterDrawObj.getCurrnetY(), null);
                }
            }
        }
        mFrame ++;
*/
    }

    //몬스터 상태별 체크 버전
    public void drawMonster01(Canvas canvas){
        /*

        canvas.drawRect(0, 0, mMediaWidth, mMediaHeight, mBackground);
        canvas.drawText(mMonsterName, mMediaCenterX, 100, mMonsterNamePaint);

        //monster 1~5번
        for (int i = 0; i<5; i++){
            //monster setDrawMonster 가 설정되어있으면 그린다.
            if (mMonsterInfoCreate.size() != 0){
                for (MonsterDrawObj monsterDrawObj : mMonsterInfoCreate.get(i).getMonsterDrawObj()){
                    //프레임 딜레이
//                    Log.msg("mMonsterInfoCreate draw : "+ mFrame);
                    canvas.drawBitmap(monsterDrawObj.getMonsterCurrentBitmap(), monsterDrawObj.getCurrnetX(), monsterDrawObj.getCurrnetY(), null);
                }
            }
        }
        mFrame ++;
*/
    }

}
