package gun2.dev.glovesquest.main.object.stage.exec;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import gun2.dev.glovesquest.db.type.BgImg;
import gun2.dev.glovesquest.db.type.BgImgData;
import gun2.dev.glovesquest.db.type.BgImgSky;
import gun2.dev.glovesquest.main.object.stage.BgImgInfo;
import gun2.dev.glovesquest.main.object.stage.type.BgImgDrawInfo;
import gun2.dev.glovesquest.main.object.stage.type.BgImgSkyDrawInfo;
import gun2.dev.glovesquest.main.object.stage.type.TmpInt;
import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.LogManager;

public class BgImgManager {

    private LogManager Log = new LogManager(getClass().getName().trim());

    public BgImgManager(Resources resources) {
        mResources = resources;
    }

    private Resources mResources;

    private BgImgInfo mBgImgInfo;

    private TmpInt tmpInt;

    private int mCloseBgImgPointX, mDisBgImgPointX, mTopBgImgPointX, mSkyBgImgPointX;   //베이스 좌표
    private int mCloseBgImgPointY = 0, mDisBgImgPointY = 0, mTopBgImgPointY = 0, mSkyBgImgPointY = 0;
    private int mSkyBgImgCurrentPointX, mSkyBgImgCurrentPointY; //현재 하늘 이미지 (이미지 이동 시 좌표)
    private int mSkyBgImgLastPointX = 0, mSkyBgImgLastPointY = 0;   //하늘 이미지가 이 좌표에 도달하면 베이스 좌표로 초기화된다.
    private int mDirectionX = 1, mDirectionY = 0;


    private int mCloseAnimCurrentPage = 0, mDisAnimCurrentPage = 0, mTopAnimCurrentPage = 0;    //페이지
    private int mCloseAnimLastPage, mDisAnimLastPage, mTopAnimLastPage;
    private int frame = 0;

    public void setBgImgFromDb(BgImg bgImg) {
        mBgImgInfo = new BgImgInfo();
        tmpInt = new TmpInt();

        BgImgDrawInfo disBgImgDrawInfo = getAutoSetBgImgDrawInfo(bgImg.getDis(), tmpInt);
        mDisAnimLastPage = tmpInt.getI();

        BgImgDrawInfo closeBgImgDrawInfo = getAutoSetBgImgDrawInfo(bgImg.getClose(), tmpInt);
        mCloseAnimLastPage = tmpInt.getI();

        BgImgDrawInfo topBgImgDrawInfo = getAutoSetBgImgDrawInfo(bgImg.getTop(), tmpInt);
        mTopAnimLastPage = tmpInt.getI();

        BgImgSkyDrawInfo bgImgSkyDrawInfo = getAutoSetBgImgSkyDrawInfo(bgImg.getSky());
        Log.msg("mDisAnimLastPage : " + mDisAnimLastPage + " mCloseAnimLastPage : " + mCloseAnimLastPage + "");

        mBgImgInfo.setDisBgImgDrawInfo(disBgImgDrawInfo);
        mBgImgInfo.setCloseBgImgDrawInfo(closeBgImgDrawInfo);
        mBgImgInfo.setTopBgImgDrawInfo(topBgImgDrawInfo);
        mBgImgInfo.setBgImgSkyDrawInfo(bgImgSkyDrawInfo);

        mCloseBgImgPointX = getAutoBgImgPointX(closeBgImgDrawInfo.getMainImg());
        mDisBgImgPointX = getAutoBgImgPointX(disBgImgDrawInfo.getMainImg());
        mTopBgImgPointX = getAutoBgImgPointX(topBgImgDrawInfo.getMainImg());

        mDirectionX = bgImgSkyDrawInfo.getDirectionX();
        mDirectionY = bgImgSkyDrawInfo.getDirectionY();
        mSkyBgImgPointX = getAutoSkyBgImgPointX(bgImgSkyDrawInfo.getImg());
        mSkyBgImgCurrentPointX = mSkyBgImgPointX;

    }

    //Db값 클래스로 옮기기
    public BgImgDrawInfo getAutoSetBgImgDrawInfo(BgImgData bgImgData, TmpInt obj) {
        obj.setI(0);
        BgImgDrawInfo bgImgDrawInfo = new BgImgDrawInfo();

        if (bgImgData != null) {
            if (bgImgData.getMainImg().length() > 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                        mResources.getIdentifier(bgImgData.getMainImg(), "drawable", "gun2.dev.glovesquest"));
                bitmap = Bitmap.createScaledBitmap(bitmap, DeviceEnvironment.sDeviceHeight, DeviceEnvironment.sDeviceHeight, false);

                //db load and save
                bgImgDrawInfo.setMainImg(bitmap);
            }

            if (bgImgData.getAnimImg() != null) {
                for (String img : bgImgData.getAnimImg().getImgList()) {
                    Bitmap bm = BitmapFactory.decodeResource(mResources,
                            mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
                    bgImgDrawInfo.addAnimImg(
                            Bitmap.createScaledBitmap(bm, DeviceEnvironment.sDeviceHeight, DeviceEnvironment.sDeviceHeight, false));
                }
                obj.setI(bgImgData.getAnimImg().getImgList().size());
                bgImgDrawInfo.setFrameDelay(bgImgData.getAnimImg().getFrameDelay());
            }
        }

        return bgImgDrawInfo;
    }


    public BgImgSkyDrawInfo getAutoSetBgImgSkyDrawInfo(BgImgSky bgImgSky) {

        BgImgSkyDrawInfo bgImgSkyDrawInfo = new BgImgSkyDrawInfo();

        Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                mResources.getIdentifier(bgImgSky.getImg(), "drawable", "gun2.dev.glovesquest"));
        bitmap = Bitmap.createScaledBitmap(bitmap, DeviceEnvironment.sDeviceHeight * 2, DeviceEnvironment.sDeviceHeight, false);
        bgImgSkyDrawInfo.setImg(bitmap);
        bgImgSkyDrawInfo.setDirectionX(bgImgSky.getDirectionX());
        bgImgSkyDrawInfo.setDirectionY(bgImgSky.getDirectionY());
        bgImgSkyDrawInfo.setFrameDelay(bgImgSky.getFrameDelay());

        return bgImgSkyDrawInfo;
    }


    public int getAutoBgImgPointX(Bitmap bitmap) {
        if (bitmap != null) return (DeviceEnvironment.sDeviceWidth - bitmap.getWidth()) / 2;
        else return 0;

    }

    public int getAutoSkyBgImgPointX(Bitmap bitmap) {
        if (bitmap != null) {
            //오른쪽 방향
            if (mDirectionX > 0) {
                int result = (DeviceEnvironment.sDeviceWidth - bitmap.getWidth());  //하늘 이미지 시작 X값
                //mDirectionX의 나머지값을 더한다 (onDraw에서 값을 맞추기 위함)
                int lastPoint = (DeviceEnvironment.sDeviceWidth - (bitmap.getWidth() / 2));
                mSkyBgImgLastPointX = lastPoint + (result - lastPoint) % mDirectionX;
                return result;
            }
            //왼쪽 방향
            else {
                //mDirectionX의 나머지값을 더한다 (onDraw에서 값을 맞추기 위함)
                mSkyBgImgLastPointX = (((bitmap.getWidth() / 2)) % mDirectionX) - (bitmap.getWidth() / 2);

                return 0;
            }
        } else return 0;
    }


    public void onDrawBgImgBack(Canvas canvas) {
        //하늘
        canvas.drawBitmap(mBgImgInfo.getBgImgSkyDrawInfo().getImg(), mSkyBgImgCurrentPointX, mSkyBgImgCurrentPointY, null);
        mSkyBgImgCurrentPointX += mDirectionX;
        mSkyBgImgCurrentPointY += mDirectionY;
        if (mSkyBgImgCurrentPointX == mSkyBgImgLastPointX) {
            mSkyBgImgCurrentPointX = mSkyBgImgPointX;
            mSkyBgImgCurrentPointY = mSkyBgImgPointY;
        }
        //원경
        canvas.drawBitmap(mBgImgInfo.getDisBgImgDrawInfo().getMainImg(), mDisBgImgPointX, mDisBgImgPointY, null);
        if (mBgImgInfo.getDisBgImgDrawInfo().getAnimImgList().size() > 0) {
            canvas.drawBitmap(
                    mBgImgInfo.getDisBgImgDrawInfo().getAnimImgList().get(mDisAnimCurrentPage),
                    mDisBgImgPointX,
                    mDisBgImgPointY,
                    null
            );
            mDisAnimCurrentPage = (mDisAnimCurrentPage + 1) % mDisAnimLastPage;
        }


        //근경
        canvas.drawBitmap(mBgImgInfo.getCloseBgImgDrawInfo().getMainImg(), mCloseBgImgPointX, mCloseBgImgPointY, null);
        if (mBgImgInfo.getCloseBgImgDrawInfo().getAnimImgList().size() > 0) {
            canvas.drawBitmap(
                    mBgImgInfo.getCloseBgImgDrawInfo().getAnimImgList().get(mCloseAnimCurrentPage),
                    mCloseBgImgPointX,
                    mCloseBgImgPointY,
                    null
            );
            mCloseAnimCurrentPage = (mCloseAnimCurrentPage + 1) % mCloseAnimLastPage;
        }

    }

    public void onDrawBgImgFront(Canvas canvas) {

    }

    public void recycle() {
        try {
            //원경제거
            Log.msg("원경 제거");
            mBgImgInfo.getDisBgImgDrawInfo().getMainImg().recycle();
        } catch (Exception e) {
            Log.msg("원경제거 err");
        }
        try {
            //원경 애니메이션 제거
            Log.msg("원경 애니메이션 제거");
            for (Bitmap bitmap : mBgImgInfo.getDisBgImgDrawInfo().getAnimImgList()) {
                bitmap.recycle();
            }
        } catch (Exception e) {
            Log.msg("원경 애니메이션 제거 err");
        }
        try {
            //근경제거
            Log.msg("근경 제거");
            mBgImgInfo.getCloseBgImgDrawInfo().getMainImg().recycle();
        } catch (Exception e) {
            Log.msg("근경제거 err");
        }
        try {
            //근경 애니메이션 제거
            Log.msg("근경 애니메이션 제거");
            for (Bitmap bitmap : mBgImgInfo.getCloseBgImgDrawInfo().getAnimImgList()) {
                bitmap.recycle();
            }
        } catch (Exception e) {
            Log.msg("근경 애니메이션 제거 err");
        }
        try {
            //초근경제거
            Log.msg("초근경제거 제거");
            mBgImgInfo.getTopBgImgDrawInfo().getMainImg().recycle();
        } catch (Exception e) {
            Log.msg("초근경제거 err");
        }
        try {
            //초근경 애니메이션 제거
            Log.msg("초근경 애니메이션 제거");
            for (Bitmap bitmap : mBgImgInfo.getTopBgImgDrawInfo().getAnimImgList()) {
                bitmap.recycle();
            }
        } catch (Exception e) {
            Log.msg("초근경 애니메이션 제거 err");
        }
        try {
            //초근경제거
            Log.msg("하늘 제거");
            mBgImgInfo.getBgImgSkyDrawInfo().getImg().recycle();
        } catch (Exception e) {
            Log.msg("하늘 제거 err");
        }
        //하늘 제거

    }
}
