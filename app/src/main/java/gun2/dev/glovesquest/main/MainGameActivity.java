package gun2.dev.glovesquest.main;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import java.text.DateFormat;
import java.util.Date;

import gun2.dev.glovesquest.R;
import gun2.dev.glovesquest.db.RealmInitiate;
import gun2.dev.glovesquest.main.ui.button.AdventureSeenBtnFragment;
import gun2.dev.glovesquest.main.ui.button.FightSeenBtnFragment;
import gun2.dev.glovesquest.main.ui.button.StartSeenBtnFragment;
import gun2.dev.glovesquest.main.ui.menu.IdSelectionFragment;
import gun2.dev.glovesquest.main.view.start.StartSeenThread;
import gun2.dev.glovesquest.main.view.start.StartSeenView;
import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.LogManager;

public class MainGameActivity extends AppCompatActivity
        implements View.OnClickListener,
        IdSelectionFragment.Callback,
        StartSeenView.Callback {

    /**
     * test (완성 후 지울것)
     */
    private StartSeenThread startSeenThread;

    /**
     * Log
     */
    private LogManager Log = new LogManager(getClass().getName().trim());   //LogManager init

    /**
     * fragment init
     */
    private StartSeenBtnFragment mStartSeenBtnFragment = new StartSeenBtnFragment();    //start seen 버튼 프래그먼트
    private AdventureSeenBtnFragment mAdventureSeenBtnFragment = new AdventureSeenBtnFragment();    //Adventure seen 버튼 프래그먼트
    private FightSeenBtnFragment mFightSeenBtnFragment = new FightSeenBtnFragment();    //Fight seen 버튼 프래그먼트
    private IdSelectionFragment mIdSelectionFragment = new IdSelectionFragment();   //IdSelection seen 메뉴 프래그먼트 (id선택창)
    /**
     * View init
     */
    private StartSeenView mStartSeenView;

    /**
     * Realm init
     */
    private RealmInitiate mRealmInitiate;   //Realm 백엔드 조작 객체, 생성자에 context 파라미터 삽입 필요

    /**
     * life cycle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_game);

        Button testBtn1 = (Button) findViewById(R.id.activity_main_game_testBtn1);
        Button testBtn2 = (Button) findViewById(R.id.activity_main_game_testBtn2);
        Button testBtn3 = (Button) findViewById(R.id.activity_main_game_testBtn3);
        Button testBtn4 = (Button) findViewById(R.id.activity_main_game_testBtn4);
        Button testBtn5 = (Button) findViewById(R.id.activity_main_game_testBtn5);
        Button testBtn6 = (Button) findViewById(R.id.activity_main_game_testBtn6);

        testBtn1.setOnClickListener(this);
        testBtn2.setOnClickListener(this);
        testBtn3.setOnClickListener(this);
        testBtn4.setOnClickListener(this);
        testBtn5.setOnClickListener(this);
        testBtn6.setOnClickListener(this);

        Log.msg(DateFormat.getDateTimeInstance().format(new Date()));

        mRealmInitiate = new RealmInitiate(getApplicationContext());    //RealmInitiate 객체 초기화


        new DeviceEnvironment(getApplicationContext()); //디바이스 정보 얻기, 추후 게임 시작 페이지로 옮겨야됩니다.

        mStartSeenView = new StartSeenView(this);   //해당 SurfaceView 클래스는 디바이스 정보를 얻은 후 초기화해야 적절한 디바이스 사이즈를 구합니다.

    }

    //-----------------
    //life cycle
    //-----------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_game_testBtn1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_game_frame_button, mStartSeenBtnFragment)
                        .commit();
                Log.msg("activity_main_game_testBtn1 is clicked");
                break;

            case R.id.activity_main_game_testBtn2:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_game_frame_button, mAdventureSeenBtnFragment)
                        .commit();
                Log.msg("activity_main_game_testBtn2 is clicked");
                break;
            case R.id.activity_main_game_testBtn3:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_game_frame_button, mFightSeenBtnFragment)
                        .commit();
                Log.msg("activity_main_game_testBtn3 is clicked");
                break;
            case R.id.activity_main_game_testBtn4:
                Log.msg("activity_main_game_testBtn4 is clicked");
                FrameLayout frameLayout = (FrameLayout) findViewById(R.id.main_activity_main_game_frame);
                frameLayout.addView(mStartSeenView);
                break;
            case R.id.activity_main_game_testBtn5:
                Log.msg("activity_main_game_testBtn5 is clicked");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_game_frame_button, mIdSelectionFragment)
                        .commit();
                break;
            case R.id.activity_main_game_testBtn6:
                startSeenThread.setDampleDraw(BitmapFactory.decodeResource(this.getResources(), R.drawable.test_object2));
                break;
        }//switch
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmInitiate.closeRealm();
    }

    //-----------
    //Method Zone
    //-----------


    //-----------
    //callback Zone
    //-----------

    /**
     * IdSelectionFragment interface
     */
    @Override
    public void idSelectionFragmentCallback(int id) {
        Log.msg("idSelectionFragmentCallback is called, getParam id : " + id);
        /**
         * 1. 프래그먼트 종료
         * 2. 해당 Db가져오기
         * 3. startSeen불러오기
         */
        //1. 프래그먼트 종료
        getSupportFragmentManager().beginTransaction()
                .remove(mIdSelectionFragment)
                .commit();
        //2. 해당 Db가져오기
//        Character character = mRealmInitiate.loadCharacter(id);
//        Log.msg("캐릭터 정보 가져오기 : " + character.toString());
//        RealmResults<Item> itemRealmResults = mRealmInitiate.loadItem(id, character);
        Log.msg("아이템 정보 가져오기\n");
//        for (Item item : itemRealmResults) {
//            Log.msg(item.toString());
//        }

    }


    /**
     * StartSeenView interface
     */
    @Override
    public void StartSeenViewCallback(StartSeenThread obj) {
        Log.msg("StartSeenViewCallback is called");
        startSeenThread = obj;
    }
}
