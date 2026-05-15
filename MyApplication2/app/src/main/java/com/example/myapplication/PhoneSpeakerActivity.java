package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneSpeakerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_speaker);

        RadioButton tabHeadset = findViewById(R.id.tabHeadset);
        tabHeadset.setOnClickListener(v -> {
            Intent intent = new Intent(PhoneSpeakerActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}