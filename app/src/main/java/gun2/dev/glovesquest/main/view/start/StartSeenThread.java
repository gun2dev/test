package gun2.dev.glovesquest.main.view.start;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import gun2.dev.glovesquest.R;
import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.LogManager;

public class StartSeenThread extends Thread {
    
    LogManager Log = new LogManager(getClass().getName().trim());
    
    public StartSeenThread(SurfaceHolder Holder, Context context) {
        mHolder = Holder;
        mContext = context;

        mSample = BitmapFactory.decodeResource(context.getResources(), R.drawable.test_object);
        mBackground = new Paint();
        mBackground.setColor(Color.WHITE);
    }

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


    // ---------------------------------
    //      Run
    // ---------------------------------
    public void run() {
        while (canRun) {
            Canvas canvas = null;
            try {
                canvas = mHolder.lockCanvas();
                synchronized (mHolder) {
                    try {
                        Log.msg("run!");
                        onDraw(canvas);
                    }catch (Exception e){}
                }
            } finally {
                if (canvas != null) mHolder.unlockCanvasAndPost(canvas);
            }
        } // while
    } // run


    //------------
    //draw method
    //------------
    public void onDraw(Canvas canvas){
        Log.msg("onDraw");
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

    //-------------------------------------
    //  stop thread
    //-------------------------------------
    public void StopThread() {
        canRun = false;
        synchronized (this) {
            this.notify();                   // 스레드에 통지
        }
    }

    public void setDampleDraw(Bitmap sampleDraw){
        mSample = sampleDraw;
    }
}
