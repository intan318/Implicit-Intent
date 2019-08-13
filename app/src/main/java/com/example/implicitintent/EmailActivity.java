package com.example.implicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailActivity extends AppCompatActivity {

    @BindView(R.id.etEmailTujuan)
    EditText etEmailTujuan;
    @BindView(R.id.etSubject)
    EditText etSubject;
    @BindView(R.id.etBodyEmail)
    EditText etBodyEmail;
    @BindView(R.id.btnSend)
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSend)
    public void onViewClicked() {

        //todo ambil inputan dari user
        String email = etEmailTujuan.getText().toString().trim();
        String subject = etSubject.getText().toString().trim();
        String bodyEmail = etBodyEmail.getText().toString().trim();

        //todo buat validasi
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(subject) || TextUtils.isEmpty(bodyEmail)){
            Toast.makeText(this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            i.putExtra(Intent.EXTRA_SUBJECT, subject);
            i.putExtra(Intent.EXTRA_TEXT, bodyEmail);
            i.setType("message/rfc822"); //aturan kyk Uri
            startActivity(Intent.createChooser(i, "Silakan pilih aplikasi e-mail"));
        }

    }
}
