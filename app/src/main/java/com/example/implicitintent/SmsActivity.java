package com.example.implicitintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsActivity extends AppCompatActivity {

    @BindView(R.id.etNomorTujuan)
    EditText etNomorTujuan;
    @BindView(R.id.etBodySMS)
    EditText etBodySMS;
    @BindView(R.id.btnSMSLangsung)
    Button btnSMSLangsung;
    @BindView(R.id.btnSMSAplikasi)
    Button btnSMSAplikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);

        //todo check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 2);
            }
        }
    }

    @OnClick({R.id.etNomorTujuan, R.id.btnSMSLangsung, R.id.btnSMSAplikasi})
    public void onViewClicked(View view) {

        String noTelp = etNomorTujuan.getText().toString().trim();
        String bodySMS= etBodySMS.getText().toString().trim();

        switch (view.getId()) {
            case R.id.etNomorTujuan:

                //todo atur intent ke kontak
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

                //todo atur data kembali dari kontak
                startActivityForResult(i, 1);

                break;
            case R.id.btnSMSLangsung:

                if (TextUtils.isEmpty(noTelp)|| TextUtils.isEmpty(bodySMS)){
                    Toast.makeText(this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(noTelp, null, bodySMS, null, null);
                    Toast.makeText(this, "SMS sent", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnSMSAplikasi:


                if (TextUtils.isEmpty(noTelp)|| TextUtils.isEmpty(bodySMS)){
                    Toast.makeText(this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentToSMSApp = new Intent (Intent.ACTION_SENDTO);
                    startActivity(intentToSMSApp);
                }

                break;
        }
    }
}
