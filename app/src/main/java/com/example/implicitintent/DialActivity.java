package com.example.implicitintent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialActivity extends AppCompatActivity {

    @BindView(R.id.etNoTelp)
    EditText etNoTelp;
    @BindView(R.id.btnCall)
    Button btnCall;
    @BindView(R.id.btnDialPhone)
    Button btnDialPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);
        ButterKnife.bind(this);

        //todo check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 2);
            }
        }
    }

    @SuppressLint("MissingPermission")
    @OnClick({R.id.etNoTelp, R.id.btnCall, R.id.btnDialPhone})
    public void onViewClicked(View view) {

        String nomorTelepon = etNoTelp.getText().toString().trim();
        switch (view.getId()) {
            case R.id.etNoTelp:

               //todo atur intent ke kontak
               Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
               i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

               //todo atur data kembali dari kontak
                startActivityForResult(i, 1);
                break;
            case R.id.btnCall:

                if (TextUtils.isEmpty(nomorTelepon)){
                    Toast.makeText(this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse("tel:" + nomorTelepon)); //"tel:" itu aturan dari Uri
                    startActivity(intentCall);
                }

                break;
            case R.id.btnDialPhone:

                if(TextUtils.isEmpty(nomorTelepon)){
                    Toast.makeText(this, "Phone Number can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentDial = new Intent(Intent.ACTION_DIAL);
                    intentDial.setData(Uri.parse("tel:"+nomorTelepon));
                    startActivity(intentDial);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 ){
            Cursor cursor = null;
            Uri uri = data.getData();

            cursor = getContentResolver().query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                                                null,
                                                null,
                                                null);

            if (cursor!=null&&cursor.moveToNext()){
                String nama = cursor.getString(1);
                String noTelp = cursor.getString(0);

                etNoTelp.setText(noTelp);
            }
        }
    }
}
