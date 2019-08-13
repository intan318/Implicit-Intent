package com.example.implicitintent;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmActivity extends AppCompatActivity {

    @BindView(R.id.analog_clock)
    AnalogClock analogClock;
    @BindView(R.id.btnSetAlarm)
    Button btnSetAlarm;
    @BindView(R.id.txtSetAlarm)
    TextView txtSetAlarm;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {

            Calendar calnow = Calendar.getInstance();
            Calendar calset = (Calendar) calnow.clone();

            calset.set(Calendar.HOUR_OF_DAY, i);
            calset.set(Calendar.MINUTE, i1);
            calset.set(Calendar.SECOND, 0);
            calset.set(Calendar.MILLISECOND, 0);

            if (calset.compareTo(calnow) <= 0){
                calset.add(Calendar.DATE, 1);
            } else  if (calset.compareTo(calnow)>0) {
                Toast.makeText(AlarmActivity.this, "alarm diatur", Toast.LENGTH_SHORT).show();
            }

            txtSetAlarm.setText("Alarm di set untuk: " + calset.getTime());

            Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1, intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calset.getTimeInMillis(), pendingIntent);
        }
    };

    @OnClick(R.id.btnSetAlarm)
    public void onViewClicked() {
        Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(this, onTimeSetListener, calendar.get(calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        timePickerDialog.setTitle("Set Waktu Alarm");
        timePickerDialog.show();
    }
}
