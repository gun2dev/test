package gun2.dev.glovesquest.db.data;

import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.Text;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


//커스텀 이미지를 저장
public class Costume extends RealmObject
    implements RealmModel {

    @PrimaryKey
    private int id;
    private Text name;
    private RealmList<ImgAnim> frontImgList;
    private RealmList<ImgAnim> backImgList;
    private ImgAnim thumImg;
    private long time = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public RealmList<ImgAnim> getFrontImgList() {
        return frontImgList;
    }

    public void setFrontImgList(RealmList<ImgAnim> frontImgList) {
        this.frontImgList = frontImgList;
    }

    public RealmList<ImgAnim> getBackImgList() {
        return backImgList;
    }

    public void setBackImgList(RealmList<ImgAnim> backImgList) {
        this.backImgList = backImgList;
    }

    public ImgAnim getThumImg() {
        return thumImg;
    }

    public void setThumImg(ImgAnim thumImg) {
        this.thumImg = thumImg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Costume{" +
                "id=" + id +
                ", name=" + name +
                ", frontImgList=" + frontImgList +
                ", backImgList=" + backImgList +
                ", thumImg=" + thumImg +
                ", time=" + time +
                '}';
    }
}
