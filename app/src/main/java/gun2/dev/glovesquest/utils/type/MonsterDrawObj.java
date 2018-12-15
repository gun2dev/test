package gun2.dev.glovesquest.utils.type;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.MotionAnimManager;

public class MonsterDrawObj {
    public MonsterDrawObj(Resources resources) {
        this.resources = resources;
    }

    private Resources resources;
    //몬스터 정보
    private int currentState = 0;   //현재 상태 -3죽음, -2스킬중, -1기절, 0기본

    private PointXY currentLocation = new PointXY();
    private ArrayList<Bitmap> monsterBitmaps = new ArrayList<>();

    //몬스터 이미지
    private ArrayList<Bitmap> defaultMonsterBitmapList = new ArrayList<>(); //기본
    private ArrayList<Bitmap> deadMonsterBitmapList = new ArrayList<>();    //죽을때
    private ArrayList<Bitmap> superCastingMonsterBitmapList = new ArrayList<>();    //취소 안되는 스킬 시전시
    private ArrayList<Bitmap> struckMonsterBitmapList = new ArrayList<>();  //피격시

    //몬스터 이미지 개수
    private int defaultCanvasPage = 0;  //기본
    private int defaultLastCanvasPage = 0;

    private int deadCanvasPage = 0; //죽을때
    private int deadtLastCanvasPage = 0;

    private int superCaseingCanvasPage = 0; //취소 안되는 스킬 시전
    private int superCaseingLastCanvasPage = 0;

    private int struckCanvasPage = 0;   //피격
    private int struckLastCanvasPage = 0;


    private int canvasPage = 0;
    private int canvasLastPage = 0;
    private int crtX, crtY;
    private int frameDelay = 10;

    private long frame = 0;
    private long count = 0;
    private long motionCount = 0;

    //모션 정보
    private MotionAnimManager motionAnimManager = new MotionAnimManager();
    private MotionList motionList = new MotionList();

    public long getFrame() {
        return frame;
    }

    public void setFrame(long frame) {
        this.frame = frame;
    }

    public MotionAnimManager getMotionAnimManager() {
        return motionAnimManager;
    }

    public void setMotionAnimManager(MotionAnimManager motionAnimManager) {
        this.motionAnimManager = motionAnimManager;
    }

    public void nextCanvasPage() {
        canvasPage = (canvasPage + 1) % canvasLastPage;
    }

    public PointXY getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(PointXY currentLocation) {
        this.currentLocation = currentLocation;
    }

    public ArrayList<Bitmap> getMonsterBitmaps() {
        return monsterBitmaps;
    }

    public void setMonsterBitmaps(ArrayList<Bitmap> monsterBitmaps) {
        this.monsterBitmaps = monsterBitmaps;
    }

    public int getCanvasPage() {
        return canvasPage;
    }

    public void setCanvasPage(int canvasPage) {
        this.canvasPage = canvasPage;
    }

    public int getCanvasLastPage() {
        return canvasLastPage;
    }

    public void setCanvasLastPage(int canvasLastPage) {
        this.canvasLastPage = canvasLastPage;
    }

    //몬스터 자리 세팅
    public void initLocation() {

    }

    //몬스터 프레임 이미지 삽입
    public void addRscToBitmap(int rsc) {
        Bitmap bitmap = BitmapFactory.decodeResource(resources, rsc);
        monsterBitmaps.add(bitmap);
        if (canvasLastPage == 0) {
            //캐릭터 위치
            crtX = DeviceEnvironment.sDeviceWidthCenter - bitmap.getWidth() / 2;
            crtY = DeviceEnvironment.sDeviceHeightCenter - bitmap.getHeight() / 2;
            //변동하는 위치
           currentLocation.setPoint(crtX, crtY);
        }
        //삽입 후 삽입 개수 계산을 위해 값 증가
        canvasLastPage++;
    }

    public Bitmap getMonsterCurrentBitmap() {

        Bitmap result = monsterBitmaps.get(canvasPage);


        //프레임 딜레이 일치 시 이미지 변경
        if (frame % frameDelay == 0) canvasPage = (canvasPage + 1) % canvasLastPage;

        //모션 시작

        if (motionAnimManager.getStartDelay() < count) moveForMotion();

        //프레임 값 증가
        frame ++;
        //draw 카운트 (모션 초기 딜레이용)
        count ++;
        return result;

    }


    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public int getCrtX() {
        return crtX;
    }

    public void setCrtX(int crtX) {
        this.crtX = crtX;
    }

    public int getCrtY() {
        return crtY;
    }

    public void setCrtY(int crtY) {
        this.crtY = crtY;
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    public int getCurrnetX() {
        return currentLocation.getX();
    }

    public int getCurrnetY() {
        return currentLocation.getY();
    }

    public void moveForMotion() {
        PointXY pointXY = new PointXY();
        for (MotionWorkFlow motionWorkFlow : motionAnimManager.getMotionWorkList()) {
            if (frame % motionWorkFlow.getFrameDelay() == 0){
                for (String str : motionWorkFlow.getMotionList()) {
                    pointXY = motionList.motionApply(currentLocation.getX(), currentLocation.getY(), str, motionCount, 20);
                    currentLocation.setPoint(pointXY.getX(), pointXY.getY());
                }
                motionCount++;
            }
        }

    }

}
