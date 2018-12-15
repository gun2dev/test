package gun2.dev.glovesquest.main.object.monster.skill.type;

import android.graphics.Bitmap;

import gun2.dev.glovesquest.main.object.monster.type.MonsterMotionGroup;

public class MonsterSkillDrawInfoData {
    private MonsterSkillDrawInfo monsterSkillDrawInfo;
    private int canvasCurrentPage = 0;
    private int rotate = 0;
    private int repeat = -1;
    private MonsterMotionGroup motionGroup;

    public int getCanvasCurrentPage() {
        return canvasCurrentPage;
    }

    public void setCanvasCurrentPage(int canvasCurrentPage) {
        this.canvasCurrentPage = canvasCurrentPage;
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

    public MonsterSkillDrawInfo getMonsterSkillDrawInfo() {
        return monsterSkillDrawInfo;
    }

    public void setMonsterSkillDrawInfo(MonsterSkillDrawInfo monsterSkillDrawInfo) {
        this.monsterSkillDrawInfo = monsterSkillDrawInfo;
    }

    public MonsterMotionGroup getMotionGroup() {
        return motionGroup;
    }

    public void setMotionGroup(MonsterMotionGroup motionGroup) {
        this.motionGroup = motionGroup;
    }

    public Bitmap getCurrentMonsterSkillImgBitmap(int frame) {
        Bitmap bitmap = monsterSkillDrawInfo.getSkillImgList().get(canvasCurrentPage);
//        frame++;
        //설정 프레임 딜레이가 지나면 이미지 다음것으로 변경
        if (frame % monsterSkillDrawInfo.getFrameDelay() == 0) {
            switch (repeat) {
                case -1:
                    //무한반복
                    canvasCurrentPage = (canvasCurrentPage + 1) % monsterSkillDrawInfo.getCanvasLastPage();
                    break;
                case -2:
                    //반복없음 (마지막 이미지로 대기)
                    if (canvasCurrentPage + 1 == monsterSkillDrawInfo.getCanvasLastPage()) break;
                    canvasCurrentPage = (canvasCurrentPage + 1) % monsterSkillDrawInfo.getCanvasLastPage();
                    break;
                case -3:
                    //반복없음( 첫번째 이미지로 대기)
                    if (rotate < 1) {
                        canvasCurrentPage = canvasCurrentPage + 1;
                        if (canvasCurrentPage == monsterSkillDrawInfo.getCanvasLastPage()) {
                            rotate++;
                            canvasCurrentPage = 0;
                        }
                    }
                    break;
                default:
                    if (rotate != repeat) {
                        canvasCurrentPage = canvasCurrentPage + 1;
                        if (canvasCurrentPage == monsterSkillDrawInfo.getCanvasLastPage()) {
                            rotate++;
                            canvasCurrentPage = 0;
                        }
                    }
                    break;
            }
        }
        return bitmap;
    }
}
