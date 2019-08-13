package com.example.implicitintent;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends AppCompatActivity {

    @BindView(R.id.btnRing)
    Button btnRing;
    @BindView(R.id.btnSilent)
    Button btnSilent;
    @BindView(R.id.btnVibrate)
    Button btnVibrate;

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    @OnClick({R.id.btnRing, R.id.btnSilent, R.id.btnVibrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRing:
                
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(this, "mode normal", Toast.LENGTH_SHORT).show();
                
                break;
            case R.id.btnSilent:

                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(this, "mode silent", Toast.LENGTH_SHORT).show();
                
                break;
            case R.id.btnVibrate:
                
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(this, "mode vibrate", Toast.LENGTH_SHORT).show();
                
                break;
        }
    }
}
