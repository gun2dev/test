package gun2.dev.glovesquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import gun2.dev.glovesquest.db.RealmController;
import gun2.dev.glovesquest.main.MainGameActivity;
import gun2.dev.glovesquest.test.TestActivity;
import gun2.dev.glovesquest.utils.GameDataSetting;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn1 = (Button) findViewById(R.id.main_activity_btn1);
        Button testBtn = (Button) findViewById(R.id.main_activity_test_btn);
        Button realmMigration = (Button) findViewById(R.id.main_activity_magration_btn);    //DB Migration
        Button deleteRealm = (Button) findViewById(R.id.main_activity_deleteDb_btn);    //DB 삭제

        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainGameActivity.class);
                startActivity(intent);
            }
        });

        testBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        realmMigration.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteRealm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmController.deleteDb(getApplicationContext());

                try {
                    Toast.makeText(MainActivity.this, "DB삭제 성공", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "DB삭제 실패", Toast.LENGTH_SHORT).show();

                }

            }
        });

        new GameDataSetting();
    }
}
