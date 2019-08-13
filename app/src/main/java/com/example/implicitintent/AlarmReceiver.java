package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Berbunyi", Toast.LENGTH_SHORT).show();
        MediaPlayer player = MediaPlayer.create(context, R.raw.alarm);
        player.start();
    }
}
