package gun2.dev.glovesquest.test;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import gun2.dev.glovesquest.R;
import gun2.dev.glovesquest.db.RealmController;
import gun2.dev.glovesquest.db.data.Costume;
import gun2.dev.glovesquest.db.data.Monster;
import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.utils.BaseDbInitiateManager;
import gun2.dev.glovesquest.test.testView.TestViewActivity01;
import gun2.dev.glovesquest.utils.LogManager;
import io.realm.Realm;
import io.realm.RealmResults;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {


    private LogManager Log = new LogManager(getClass().getName().trim());

    private Resources mResource;

    ImageView testImg;

    Realm mRealm;

    private AssetManager am;
    private InputStream is = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button testBtn1 = (Button) findViewById(R.id.activity_test_btn1);
        Button testBtn2 = (Button) findViewById(R.id.activity_test_btn2);
        Button testBtn3 = (Button) findViewById(R.id.activity_test_btn3);
        Button testBtn4 = (Button) findViewById(R.id.activity_test_btn4);
        Button testBtn5 = (Button) findViewById(R.id.activity_test_btn5);

        testBtn1.setOnClickListener(this);
        testBtn2.setOnClickListener(this);
        testBtn3.setOnClickListener(this);
        testBtn4.setOnClickListener(this);
        testBtn5.setOnClickListener(this);


        testImg = (ImageView) findViewById(R.id.testImage_activity_test_imageView);


//        setContentView(new StartSeenView(this));
        mResource = getResources();


        Realm.init(getApplicationContext());

        mRealm = Realm.getInstance(RealmController.baseRealmConfig());
        am = mResource.getAssets();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_test_btn1:
                Log.msg("activity_test_btn1 is clicked");

                /*
                //Json파일 읽기
                final AssetManager am = mResource.getAssets();
                InputStream is = null;

                //Item DB값 가져오기
                Realm realm = Realm.getInstance(RealmController.baseRealmConfig());
                RealmResults<Item> dbItem = realm.where(Item.class).findAll();
                try {
                    is = am.open("itemList");

                    Gson gson = new Gson();
                    Reader reader = new InputStreamReader(is);
                    Item[] items = gson.fromJson(reader, Item[].class);
                    ArrayList<Item> itemArrayList = new ArrayList<Item>();
                    reader.close();
                    for (Item item : items) {
                        Log.msg(item.toString());
                        //테스트 후 삭제
                    }

                    //Item DB가 비어있으면
                    if (dbItem.isEmpty()) {
                        InputStream input = am.open("itemList");
                        realm.beginTransaction();
                        realm.createAllFromJson(Item.class, input);
                        realm.commitTransaction();
                        input.close();
                    }
                    //최신 Item이 존재하는지 확인 (업데이트 위함)
                    else {
                        for (Item item : items) {
                            Item col = realm.where(Item.class).equalTo("id", item.getId()).findFirst();
                            if (col == null) {
                                realm.beginTransaction();
                                realm.createObjectFromJson(Item.class, item.toJson());
                                realm.commitTransaction();
                            }
                        }
                    }

                    is.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                realm.close();
                */

                try {
                    InputStream input = am.open("costume");

                    mRealm.beginTransaction();
                    mRealm.createOrUpdateAllFromJson(Costume.class, input);
                    mRealm.commitTransaction();
                    input.close();

                    RealmResults<Costume> costumeRealmList = mRealm.where(Costume.class).findAll();

                    for (Costume costume : costumeRealmList) {
                        Log.msg(costume.toString());
                        for (ImgAnim imgAnim : costume.getFrontImgList()) {
                            Log.msg(imgAnim.toString());
                            for (String img : imgAnim.getImgList()) {
                                Log.msg(img);
                            }
                        }
                    }
                } catch (Exception e) {

                }


                break;


            case R.id.activity_test_btn2:


                //Json파일 읽기
                final AssetManager aM = mResource.getAssets();
                InputStream iS = null;

                try {

                    InputStream input = aM.open("imgTest");
                    mRealm.beginTransaction();
                    mRealm.createOrUpdateAllFromJson(Dog.class, input);
                    mRealm.commitTransaction();
                    input.close();

                    RealmResults<Dog> dogRealmResults = mRealm.where(Dog.class).findAll();
                    for (Dog dog : dogRealmResults) {
                        Log.msg(dog.toString());
                    }

                    InputStream input2 = aM.open("test2");
                    mRealm.beginTransaction();
                    mRealm.createOrUpdateAllFromJson(Person.class, input2);
                    mRealm.commitTransaction();
                    input.close();

                    RealmResults<Person> personRealmResults = mRealm.where(Person.class).findAll();
                    for (Person person : personRealmResults) {
                        Log.msg(person.toString());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

            case R.id.activity_test_btn3:

                Log.msg("activity_test_btn3 btn");

                BaseDbInitiateManager baseDbInitiateManager = new BaseDbInitiateManager(getApplicationContext(), getResources());
                baseDbInitiateManager.InitiateDb();


                break;

            case R.id.activity_test_btn4:
                Log.msg("activity_test_btn4");

                Intent intent = new Intent(TestActivity.this, TestViewActivity01.class);
                startActivity(intent);

                break;

            case R.id.activity_test_btn5:
                Log.msg("activity_test_btn5");

                Monster monster = mRealm.where(Monster.class).equalTo("id", 1001).findFirst();

                Log.msg("to String :" + monster.toString());
                Log.msg("getImg" + monster.getImgList().toString());
                for (ImgAnim imgAnim : monster.getImgList()) {
                    Log.msg("00000");
                    /*for (MotionSet motionSet : imgAnim.getMotionSetList()) {
                        Log.msg("motionSet : " + motionSet.toString());
                        for (MotionAnim motionAnim : motionSet.getMotionAnimList()) {
                            Log.msg("motionAnim : " + motionAnim.toString());
                        }
                    }*/
                    for (String img : imgAnim.getImgList()) {
                        Log.msg("monster img" + img);

                        int resId = mResource.getIdentifier(img, "drawable", "gun2.dev.glovesquest");
                        Log.msg("id : " + resId);
                    }
                }


                break;

        }
    }

    private Bitmap getBitmapFromString(String stringPicture) {
        /*
         * This Function converts the String back to Bitmap
         * */
        byte[] decodedString = Base64.decode(stringPicture, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
