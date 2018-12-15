package gun2.dev.glovesquest.main.object.stage;

import gun2.dev.glovesquest.main.object.stage.type.BgImgDrawInfo;
import gun2.dev.glovesquest.main.object.stage.type.BgImgSkyDrawInfo;

public class BgImgInfo {
    public BgImgInfo() {
    }
    private BgImgDrawInfo disBgImgDrawInfo;
    private BgImgDrawInfo closeBgImgDrawInfo;
    private BgImgDrawInfo topBgImgDrawInfo;
    private BgImgSkyDrawInfo bgImgSkyDrawInfo;
    private int frame;

    public BgImgDrawInfo getDisBgImgDrawInfo() {
        return disBgImgDrawInfo;
    }

    public void setDisBgImgDrawInfo(BgImgDrawInfo disBgImgDrawInfo) {
        this.disBgImgDrawInfo = disBgImgDrawInfo;
    }

    public BgImgDrawInfo getCloseBgImgDrawInfo() {
        return closeBgImgDrawInfo;
    }

    public void setCloseBgImgDrawInfo(BgImgDrawInfo closeBgImgDrawInfo) {
        this.closeBgImgDrawInfo = closeBgImgDrawInfo;
    }

    public BgImgDrawInfo getTopBgImgDrawInfo() {
        return topBgImgDrawInfo;
    }

    public void setTopBgImgDrawInfo(BgImgDrawInfo topBgImgDrawInfo) {
        this.topBgImgDrawInfo = topBgImgDrawInfo;
    }

    public BgImgSkyDrawInfo getBgImgSkyDrawInfo() {
        return bgImgSkyDrawInfo;
    }

    public void setBgImgSkyDrawInfo(BgImgSkyDrawInfo bgImgSkyDrawInfo) {
        this.bgImgSkyDrawInfo = bgImgSkyDrawInfo;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }
}
