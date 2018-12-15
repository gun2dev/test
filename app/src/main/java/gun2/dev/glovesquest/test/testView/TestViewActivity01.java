package gun2.dev.glovesquest.test.testView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import gun2.dev.glovesquest.R;
import gun2.dev.glovesquest.db.RealmController;
import gun2.dev.glovesquest.db.data.Stage;
import gun2.dev.glovesquest.main.object.monster.exec.MonsterCreator;
import gun2.dev.glovesquest.main.object.stage.exec.BgImgManager;
import gun2.dev.glovesquest.main.object.stage.exec.StageCreator;
import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.LogManager;
import io.realm.Realm;

public class TestViewActivity01 extends AppCompatActivity
        implements View.OnClickListener,
        TestStartSeenView01.Callback{
    private LogManager Log = new LogManager(getClass().getName().trim());

    /**
     * View init
     */
    private TestStartSeenView01 mTestStartSeenView01;

    private int mSequenceNum = 0;
    private int mMonsterNumList[] = { 1001, 1002, 1003};
    private int mMonsterNum = mMonsterNumList[0];
    private TestStartSeenThread01 mTestStartSeenThread01;
    private MonsterCreator mMonsterCreator;
    private Realm mRealm;
    private BgImgManager mBgImgManager;
    private StageCreator mStageCreator;
    private Thread mLodingThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view01);

        Button backBtn = (Button)findViewById(R.id.back_activity_test_view01);
        Button nextBtn = (Button)findViewById(R.id.next_activity_test_view01);
        Button deadBtn = (Button)findViewById(R.id.dead_activity_test_view01);
        Button struckBtn = (Button)findViewById(R.id.struck_activity_test_view01);

        struckBtn.setOnClickListener(this);
        deadBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        new DeviceEnvironment(getApplicationContext()); //디바이스 정보 얻기, 추후 게임 시작 페이지로 옮겨야됩니다.



        mRealm = Realm.getInstance(RealmController.baseRealmConfig());
        Stage stage = mRealm.where(Stage.class).findFirst();

        mStageCreator = new StageCreator(getResources());
        /*
        Log.msg("몬스터 정보 불러오는중");
        mMonsterCreator.createMonster3(stage.getMonsterList());
        Log.msg("배경화면 정보 불러오는중");
        mBgImgManager.setBgImgFromDb(stage.getBgImg());
        */

        mStageCreator.create();
        if (!mRealm.isClosed()) mRealm.close();

        mMonsterCreator = mStageCreator.getmMonsterCreator();
        mBgImgManager = mStageCreator.getmBgImgManager();


        //서페이스뷰생성
        mTestStartSeenView01 = new TestStartSeenView01(this, mStageCreator);   //해당 SurfaceView 클래스는 디바이스 정보를 얻은 후 초기화해야 적절한 디바이스 사이즈를 구합니다.

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_activity_test_view01);
        frameLayout.addView(mTestStartSeenView01);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mRealm.isClosed()) mRealm.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_activity_test_view01:
                Log.msg("back_activity_test_view01");
                MonsterNumDown();

                break;

            case R.id.next_activity_test_view01:
                Log.msg("next_activity_test_view01");
                MonsterNumUp();

                break;
            case R.id.dead_activity_test_view01:
                Log.msg("dead_activity_test_view");
                mMonsterCreator.changeMonsterCurrentState(-60);
                mMonsterCreator.recycleMonster_TestVer();
                break;
            case R.id.struck_activity_test_view01:
                Log.msg("struck_activity_test_view01");
                mMonsterCreator.changeMonsterCurrentState(-20);
                break;
        }


    }

    public void MonsterNumUp(){
        mSequenceNum++;
        mMonsterNum = mMonsterNumList[mSequenceNum % mMonsterNumList.length];
        mTestStartSeenView01.setDrawData(mRealm, mMonsterNum, 0);
        Log.msg(""+mMonsterNum);
    }

    public void MonsterNumDown(){
        mSequenceNum--;
        mMonsterNum = mMonsterNumList[mSequenceNum % mMonsterNumList.length];
        mTestStartSeenView01.setDrawData(mRealm, mMonsterNum, 0);
        Log.msg(""+mMonsterNum);
    }

    @Override
    public void StartSeenViewCallback(TestStartSeenThread01 obj) {
        mTestStartSeenThread01 = obj;
    }

}
