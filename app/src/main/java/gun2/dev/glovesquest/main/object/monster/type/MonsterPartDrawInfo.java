package gun2.dev.glovesquest.main.object.monster.type;

import android.graphics.Bitmap;
import android.graphics.Paint;

import java.util.ArrayList;

import gun2.dev.glovesquest.main.object.monster.MonsterInfo;
import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.GameDataSetting;
import gun2.dev.glovesquest.utils.type.PointXY;

public class MonsterPartDrawInfo
        implements MonsterInfo.Callback {
    public MonsterPartDrawInfo(Object obj) {
        if (obj instanceof MonsterInfo) monsterInfo = (MonsterInfo) obj;
    }

    private ArrayList<Bitmap> monsterImg = new ArrayList<Bitmap>();
    private int canvasPage = 0;
    private int canvasLastPage = 0;
    private int width = 0;
    private int height = 0;
    private int rotate = 0;
    private int repeat = -1;
    private int crtX = 0;
    private int crtY = 0;
    private int frameDelay = 1;
    private boolean fading = false;
    private int fadeCount;
    private int fadeSpeed = 3;
    private int frame = 0;
    private int stiffenTime = 7;
    private int stiffenCount = 0;
    private MonsterInfo monsterInfo;
    private MonsterMotionGroup motionGroup;

    public ArrayList<Bitmap> getMonsterImg() {
        return monsterImg;
    }

    public void setMonsterImg(ArrayList<Bitmap> monsterImg) {
        this.monsterImg = monsterImg;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
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

    public void addMonsterImgBitmap(Bitmap bitmap) {
        monsterImg.add(bitmap);
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    public boolean isFading() {
        return fading;
    }

    public void setFading(boolean fading) {
        this.fading = fading;
    }

    public int getFadeCount() {
        return fadeCount;
    }

    public void setFadeCount(int fadeCount) {
        this.fadeCount = fadeCount;
    }

    public int getFadeSpeed() {
        return fadeSpeed;
    }

    public void setFadeSpeed(int fadeSpeed) {
        this.fadeSpeed = fadeSpeed;
    }

    public MonsterInfo getMonsterInfo() {
        return monsterInfo;
    }

    public void setMonsterInfo(MonsterInfo monsterInfo) {
        this.monsterInfo = monsterInfo;
    }

    public MonsterMotionGroup getMotionGroup() {
        return motionGroup;
    }

    public void setMotionGroup(MonsterMotionGroup motionGroup) {
        this.motionGroup = motionGroup;
    }


    public Bitmap getCurrentMonsterImgBitmap(int frame) {
        Bitmap bitmap = monsterImg.get(canvasPage);
//        frame++;
        //설정 프레임 딜레이가 지나면 이미지 다음것으로 변경
        if (frame % frameDelay == 0) {
            switch (repeat) {
                case -1:
                    //무한반복
                    canvasPage = (canvasPage + 1) % canvasLastPage;
                    break;
                case -2:
                    //반복없음 (마지막 이미지로 대기)
                    if (canvasPage + 1 == canvasLastPage) break;
                    canvasPage = (canvasPage + 1) % canvasLastPage;
                    break;
                case -3:
                    //반복없음( 첫번째 이미지로 대기)
                    if (rotate < 1) {
                        canvasPage = canvasPage + 1;
                        if (canvasPage == canvasLastPage) {
                            rotate++;
                            canvasPage = 0;
                        }
                    }
                    break;
                default:
                    if (rotate != repeat) {
                        canvasPage = canvasPage + 1;
                        if (canvasPage == canvasLastPage) {
                            rotate++;
                            canvasPage = 0;
                        }
                    }
                    break;
            }
        }
        return bitmap;
    }


    public void startFadeIn(int time) {
        fadeCount = 0;
        fadeSpeed = time;
    }

    //페이드 인 설정
    public Paint getFadeInPaintGen() {
        if (DeviceEnvironment.sFrame % 3 == 0) fadeCount += fadeSpeed;
        if (fading == false) {
            //초기 시작
            fading = true;
            startFadeIn(1);
        }
        if (fadeCount >= 9) {
            //종료
            //몬스터가 젠 상태면 일반 상태로 변경
            if (monsterInfo.getCurrentState() == 2) monsterInfo.setCurrentState(0);
            fading = false;
        }
        return DeviceEnvironment.sAlphaPaintSettingList.get(fadeCount);
    }

    public void startFadeOut(int time) {
        fadeCount = 9;
        fadeSpeed = time;
    }

    //페이드 아웃 설정
    public Paint getFadeOutPaintDead() {
        if (DeviceEnvironment.sFrame % 3 == 0) fadeCount -= fadeSpeed;
        if (fading == false) {
            //초기 시작
            fading = true;
            startFadeOut(1);
        }
        if (fadeCount <= 0) {
            //종료
            //몬스터가 죽은 상태면 젠 상태로 변경 (test)
            if (monsterInfo.getCurrentState() == -6) monsterInfo.setCurrentState(2);
            fading = false;
        }
        return DeviceEnvironment.sAlphaPaintSettingList.get(fadeCount);
    }


    public Paint getStruckPaintFilter() {
        if (DeviceEnvironment.sFrame % GameDataSetting.sMonsterStruckFilterDelay == 0) stiffenCount++;
        if (stiffenCount == stiffenTime){
            //작동 중지
            monsterInfo.setCurrentState(0);
            stiffenCount = 0;
        }
        return GameDataSetting.sMonsterStruckColorFilterPaintList.get(stiffenCount%2);
    }

    //현재 모션 좌표 가져오기
    public PointXY getCurrentPointXY() {
        return motionGroup.getCurrentPointInGroup();
    }

    //몬스터 세팅 초기화 (재 활용 시 사용)
    public void recycleData() {
        canvasPage = 0;
        rotate = 0;
        frame = 0;
        fading = false;
    }

    //----------------
    //callback zone
    //----------------
    @Override
    public void ChangeCurrentState(int i) {
    }
}
