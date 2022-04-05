package ataie.sina.chamedoon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import pick_activitys.Train_Pick_city;
import recivers.Reciver_Check_Internet;

public class Splash_Activity extends AppCompatActivity {
        Reciver_Check_Internet reciver_check_internet;
        LinearLayout linearLayout_splash_activity;
        Train_Pick_city train_pick_city;  int Json_ok=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reciver_check_internet=new Reciver_Check_Internet();
        registerReceiver(reciver_check_internet,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        setContentView(R.layout.activity_splash);
        setupvies();
        reciver_check_internet.setOnErorinConnection(new Reciver_Check_Internet.OnErorinConnection() {
            @Override
            public void onAnswer() {
                linearLayout_splash_activity.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnswer_connected() {
                    linearLayout_splash_activity.setVisibility(View.GONE);
                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            timer.cancel();
                            finish();
                        }
                    }, 1500, 2000);

            }
        });






    }


    public void setupvies()
    {
        linearLayout_splash_activity=(LinearLayout)findViewById(R.id.leinearlayout_splash);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       unregisterReceiver(reciver_check_internet);
    }
}