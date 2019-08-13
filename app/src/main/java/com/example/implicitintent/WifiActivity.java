package com.example.implicitintent;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WifiActivity extends AppCompatActivity {

    @BindView(R.id.switchWifi)
    Switch switchWifi;
    @BindView(R.id.statusWifi)
    TextView statusWifi;

    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager.isWifiEnabled()){
            switchWifi.setChecked(true);
            statusWifi.setText("Wifi is on");
        } else {
            switchWifi.setChecked(false);
            statusWifi.setText("Wifi is not enabled");
        }

        switchWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    wifiManager.setWifiEnabled(true);
                    statusWifi.setText("Wifi dinyalakan");
                } else {
                    wifiManager.setWifiEnabled(false);
                    statusWifi.setText("Wifi dimatikan");
                }
            }
        });

    }
}
