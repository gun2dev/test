package gun2.dev.glovesquest.main.object.stage.exec;

import android.content.res.Resources;

import gun2.dev.glovesquest.db.RealmController;
import gun2.dev.glovesquest.db.data.Stage;
import gun2.dev.glovesquest.main.object.monster.exec.MonsterCreator;
import gun2.dev.glovesquest.utils.LogManager;
import io.realm.Realm;

public class StageCreator {
    LogManager Log = new LogManager(getClass().getName().trim());

    public StageCreator(Resources resources) {
        mResources = resources;
    }

    private Resources mResources;

    private boolean mLoadMonster = false;
    private boolean mLoadBgImg = false;

    private MonsterCreator mMonsterCreator;
    private BgImgManager mBgImgManager;

    private Thread mThread;

    private Realm mRealm;

    public void create() {

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mMonsterCreator = new MonsterCreator(mResources);
                mBgImgManager = new BgImgManager(mResources);

                mRealm = Realm.getInstance(RealmController.baseRealmConfig());
                Stage stage = mRealm.where(Stage.class).equalTo("stage", 2).findFirst();

//                mMonsterCreator.createMonster3(stage.getMonsterList());
                mMonsterCreator.setMonsterInfo(stage.getMonsterGenList());

                mLoadMonster = true;
                mBgImgManager.setBgImgFromDb(stage.getBgImg());
                mLoadBgImg = true;
                if (!mRealm.isClosed()) mRealm.close();
            }
        });

        mThread.start();

        Log.msg("LoadMonster...");
        while (mLoadMonster == false) {
//            Log.msg("LoadMonster");
        }
        Log.msg("LoadMonster ok");
        Log.msg("LoadBgImg...");
        while (mLoadBgImg == false) {
//            Log.msg("LoadBgImg");
        }
        Log.msg("LoadBgImg ok");
    }


    public void recycle() {

        //배경화면 제거
        mBgImgManager.recycle();
        //몬스터 bitmap 제거
        mMonsterCreator.recycle();

    }

    public MonsterCreator getmMonsterCreator() {
        return mMonsterCreator;
    }

    public void setmMonsterCreator(MonsterCreator mMonsterCreator) {
        this.mMonsterCreator = mMonsterCreator;
    }

    public BgImgManager getmBgImgManager() {
        return mBgImgManager;
    }

    public void setmBgImgManager(BgImgManager mBgImgManager) {
        this.mBgImgManager = mBgImgManager;
    }
}
