package com.example.implicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnAlarm)
    Button btnAlarm;
    @BindView(R.id.btnAudio)
    Button btnAudio;
    @BindView(R.id.btnCamera)
    Button btnCamera;
    @BindView(R.id.btnDial)
    Button btnDial;
    @BindView(R.id.btnEmail)
    Button btnEmail;
    @BindView(R.id.btnNotification)
    Button btnNotification;
    @BindView(R.id.btnSms)
    Button btnSms;
    @BindView(R.id.btnWifi)
    Button btnWifi;
    @BindView(R.id.btnBrowser)
    Button btnBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btnAlarm, R.id.btnAudio, R.id.btnCamera, R.id.btnDial, R.id.btnEmail, R.id.btnNotification, R.id.btnSms, R.id.btnWifi, R.id.btnBrowser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAlarm:
                break;
            case R.id.btnAudio:
                Intent i = new Intent (MainActivity.this, AudioManagerActivity.class);
                startActivity(i);
                break;
            case R.id.btnCamera:
                Intent intentcam = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intentcam);
                break;
            case R.id.btnDial:
                Intent intentTelp = new Intent(MainActivity.this, DialActivity.class);
                startActivity(intentTelp);
                break;
            case R.id.btnEmail:
                Intent intentEmail = new Intent (MainActivity.this, EmailActivity.class);
                startActivity(intentEmail);
                break;
            case R.id.btnNotification:
                Intent intentNotif = new Intent (MainActivity.this, NotificationActivity.class);
                startActivity(intentNotif);
                break;
            case R.id.btnSms:
                Intent intentSMS = new Intent (MainActivity.this, SmsActivity.class);
                startActivity(intentSMS);
                break;
            case R.id.btnWifi:
                Intent intentWifi = new Intent (MainActivity.this, WifiActivity.class);
                startActivity(intentWifi);
                break;
            case R.id.btnBrowser:
                break;
        }
    }
}
