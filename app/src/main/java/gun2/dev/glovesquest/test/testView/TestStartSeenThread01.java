package gun2.dev.glovesquest.test.testView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.io.PrintWriter;
import java.io.StringWriter;

import gun2.dev.glovesquest.R;
import gun2.dev.glovesquest.main.object.monster.exec.MonsterCreator;
import gun2.dev.glovesquest.main.object.stage.exec.BgImgManager;
import gun2.dev.glovesquest.main.object.stage.exec.StageCreator;
import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.LogManager;
import gun2.dev.glovesquest.utils.MonsterDrawManager;
import io.realm.Realm;

public class TestStartSeenThread01 extends Thread {

    LogManager Log = new LogManager(getClass().getName().trim());

    public TestStartSeenThread01(SurfaceHolder Holder, Context context, StageCreator stageCreator) {
        mHolder = Holder;
        mContext = context;

        mSample = BitmapFactory.decodeResource(context.getResources(), R.drawable.test_object);
        mBackground = new Paint();
        mBackground.setColor(Color.WHITE);

        mMonsterDrawManager = new MonsterDrawManager(0, context.getResources());

        mMonsterCreator = stageCreator.getmMonsterCreator();
        mBgImgManager = stageCreator.getmBgImgManager();

    }

    private BgImgManager mBgImgManager;
    private MonsterCreator mMonsterCreator;
    private SurfaceHolder mHolder;
    private Context mContext;

    /**
     * sample
     */
    public int mWidth = DeviceEnvironment.sDeviceWidth
            , mHeight = DeviceEnvironment.sDeviceHeight
            , mCx = DeviceEnvironment.sDeviceWidthCenter
            , mCy = DeviceEnvironment.sDeviceHeightCenter;     // 화면의 전체 폭과 중심점
    public int mDx = 5, mDy = 5;  //이동 sample
    public Paint mBackground;

    private  boolean canRun = true;              // 스레드 실행용 플래그

    private Bitmap mSample;

    //몬스터 그리기 테스트
    private MonsterDrawManager mMonsterDrawManager;


    // ---------------------------------
    //      Run
    // ---------------------------------
    public void run() {

        //DT
        long now = 0, dt;
        long last = System.currentTimeMillis();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1);

                Canvas canvas = null;
                try {
                    canvas = mHolder.lockCanvas();
                    synchronized (mHolder) {

                        //DT
                        now = System.currentTimeMillis();
                        dt = (now - last);
                        while (dt < DeviceEnvironment.FRAME_PER_MILLIS){
                            Thread.sleep(1);
                            now = System.currentTimeMillis();
                            dt = (now - last);
//                        Log.msg("dt");
                        }

                        try {

                            mBgImgManager.onDrawBgImgBack(canvas);
                            mMonsterCreator.onDrawMonster02(canvas);
//                        mMonsterCreator.onDrawMonster(canvas);
                            DeviceEnvironment.runFrame();

                        }catch (Exception e){
                            Log.msg("run err 1");
                            StringWriter sw = new StringWriter();
                            e.printStackTrace(new PrintWriter(sw));
                            String exceptionAsString = sw.toString();
                            Log.msg(exceptionAsString);
                            // ;;;;;?? 그리기 실패 시
                            Thread.currentThread().interrupt();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.msg("run err 2");
                } finally {
                    if (canvas != null) mHolder.unlockCanvasAndPost(canvas);
                }

                //DT
                last = now;

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        } // while


    } // run


    //------------
    //draw method
    //------------
    public void onDraw(Canvas canvas){
//        Log.msg("onDraw");
        canvas.drawRect(0, 0, mWidth, mHeight, mBackground);
        canvas.drawBitmap(mSample, mCx, mCy, null);

        mCx += mDx;          // 가로로 이동
        mCy += mDy;          // 세로로 이동

        if (mCx <= 0 || mCx >= mWidth) {
            mDx = -mDx;     // 벽이면 이동 방향을 바꿈
            mSample = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.test_object2);

        }
        if (mCy <= 0 || mCy >= mHeight) {
            mDy = -mDy;    // 천정이나 바닥이면 이동 방향을 바꿈
            mSample = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.test_object);

        }
    }


    public void onDrawMonster(Canvas canvas){
//        Log.msg("onDrawMonster");
        mMonsterDrawManager.drawMonster01(canvas);
    }

    //-------------------------------------
    //  stop thread
    //-------------------------------------
    public void StopThread() {
        this.interrupt();

        /*canRun = false;
        synchronized (this) {
            this.notify();                   // 스레드에 통지
        }*/
    }

    public void setDampleDraw(Bitmap sampleDraw){
        mSample = sampleDraw;
    }

    public void setMonsterDraw(Realm realm, int monsterId, int monsterNum){

        mMonsterDrawManager.setDrawMonster(realm, monsterId, monsterNum);

    }


}
