package gun2.dev.glovesquest.db.type;

import io.realm.RealmObject;

public class BgImg extends RealmObject {

    private BgImgSky sky;
    private BgImgData dis;
    private BgImgData close;
    private BgImgData top;

    public BgImgSky getSky() {
        return sky;
    }

    public void setSky(BgImgSky sky) {
        this.sky = sky;
    }

    public BgImgData getDis() {
        return dis;
    }

    public void setDis(BgImgData dis) {
        this.dis = dis;
    }

    public BgImgData getClose() {
        return close;
    }

    public void setClose(BgImgData close) {
        this.close = close;
    }

    public BgImgData getTop() {
        return top;
    }

    public void setTop(BgImgData top) {
        this.top = top;
    }
}
