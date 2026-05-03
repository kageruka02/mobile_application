package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Switch switchMasterPower;
    private Switch switchPlaybackGain;
    private Switch switchFETCompressor;
    private Switch switchViperDDC;
    private Switch switchSpectrumExtension;
    private Switch switchFIREqualizer;
    private Switch switchConvolver;
    private Switch switchFieldSurround;
    private TextView tvViperTimer;

    private Handler timerHandler = new Handler();
    private int timerSeconds = 0;

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            timerSeconds++;
            int minutes = timerSeconds / 60;
            int seconds = timerSeconds % 60;
            tvViperTimer.setText(String.format("%02d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchMasterPower       = findViewById(R.id.switchMasterPower);
        switchPlaybackGain      = findViewById(R.id.switchPlaybackGain);
        switchFETCompressor     = findViewById(R.id.switchFETCompressor);
        switchViperDDC          = findViewById(R.id.switchViperDDC);
        switchSpectrumExtension = findViewById(R.id.switchSpectrumExtension);
        switchFIREqualizer      = findViewById(R.id.switchFIREqualizer);
        switchConvolver         = findViewById(R.id.switchConvolver);
        switchFieldSurround     = findViewById(R.id.switchFieldSurround);
        tvViperTimer            = findViewById(R.id.tvViperTimer);

        switchMasterPower.setChecked(true);
        switchPlaybackGain.setChecked(false);
        switchFETCompressor.setChecked(false);
        switchViperDDC.setChecked(false);
        switchSpectrumExtension.setChecked(false);
        switchFIREqualizer.setChecked(true);
        switchConvolver.setChecked(true);
        switchFieldSurround.setChecked(false);

        switchMasterPower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchPlaybackGain.setEnabled(isChecked);
                switchFETCompressor.setEnabled(isChecked);
                switchViperDDC.setEnabled(isChecked);
                switchSpectrumExtension.setEnabled(isChecked);
                switchFIREqualizer.setEnabled(isChecked);
                switchConvolver.setEnabled(isChecked);
                switchFieldSurround.setEnabled(isChecked);
                Toast.makeText(MainActivity.this, "Master Power: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            }
        });

        switchPlaybackGain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "Playback Gain: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            }
        });

        switchFETCompressor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "FET Compressor: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            }
        });

        switchViperDDC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvViperTimer.setVisibility(View.VISIBLE);
                    timerSeconds = 0;
                    timerHandler.post(timerRunnable);
                } else {
                    tvViperTimer.setVisibility(View.GONE);
                    timerHandler.removeCallbacks(timerRunnable);
                }
                Toast.makeText(MainActivity.this, "ViPER-DDC: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            }
        });

        switchSpectrumExtension.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "Spectrum Extension: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            }
        });

        switchFIREqualizer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "FIREqualizer: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            }
        });

        switchConvolver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "Convolver: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            }
        });

        switchFieldSurround.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "Field Surround: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(timerRunnable);
    }
}
