package gun2.dev.glovesquest.main.view.start;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import gun2.dev.glovesquest.utils.LogManager;

public class StartSeenView extends SurfaceView implements SurfaceHolder.Callback{

    LogManager Log = new LogManager(getClass().getName().trim());

    public interface Callback{
        void StartSeenViewCallback(StartSeenThread obj);
    }

    public StartSeenView(Context context) {
        super(context);
        Log.msg("StartSeenView");
//        ReadSprite(context);
        SurfaceHolder mHolder = getHolder();
        mHolder.addCallback(this);

//        mThread = new MainThread(mHolder, context);
        mThread = new StartSeenThread(mHolder, context);
        if (context instanceof Callback) {

            ((Callback)context).StartSeenViewCallback(mThread);
        }
        setFocusable(true);
    }
    public  static int RUN = 1;
    public  static int PAUSE = 2;
    public  int mMode = RUN;

    private SurfaceHolder mHolder;
    private StartSeenThread mThread;
    private Context mContext;
    /**
     * sample
     */
    public int Width, Height, cx, cy;     // 화면의 전체 폭과 중심점
    public int dx = 1, dy = 1;  //이동 sample
    public Paint mBackground;

    private  boolean canRun = true;              // 스레드 실행용 플래그

    private Bitmap mSample;

    public void ReadSprite(Context context) {
        // 화면 해상도 구하기
        Display display = ((WindowManager)context.getSystemService(context.WINDOW_SERVICE))
                .getDefaultDisplay();
        Width  = display.getWidth();
        Height = display.getHeight();
        cx = Width / 2;                   // 화면의 중심점
        cy = Height / 2;

        Resources res = context.getResources();          // 리소스 읽기

    } // ReadSprite




    // ---------------------------------
    //  implements Zone
    // ---------------------------------

    /**
     * SurfaceHolder interface
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.msg("try surfaceDestroy");
        mThread.StopThread();
        boolean retry = true;
        while (retry) {
            try {
                mThread.join();
                retry = false;
                Log.msg("surfaceDestroyed");
            } catch (InterruptedException e) {
                //
            }
        }
    }

    /**
     *  control zone
     */
    // ---------------------------------
    //        onTouchEvent
    // ---------------------------------
    public boolean onTouchEvent(MotionEvent event) {

        return false;
    } // touch


    // ---------------------------------
    //        onKeyDown
    // ---------------------------------
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mMode == RUN && keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            synchronized (mHolder) {
                Log.msg("KEYCODE_DPAD_UP down");
            }
        }
        return false;
    }



} // end SurfaceView
