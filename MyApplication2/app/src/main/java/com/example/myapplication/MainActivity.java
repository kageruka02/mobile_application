package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CustomSwitch switchMasterPower, switchPlaybackGain, switchFETCompressor;
    private CustomSwitch switchViperDDC, switchSpectrumExtension, switchFIREqualizer;
    private CustomSwitch switchConvolver, switchFieldSurround;
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

        switchMasterPower.setOnCheckedChangeListener(isChecked -> {
            switchPlaybackGain.setEnabled(isChecked);
            switchFETCompressor.setEnabled(isChecked);
            switchViperDDC.setEnabled(isChecked);
            switchSpectrumExtension.setEnabled(isChecked);
            switchFIREqualizer.setEnabled(isChecked);
            switchConvolver.setEnabled(isChecked);
            switchFieldSurround.setEnabled(isChecked);
            Toast.makeText(this, "Master Power: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
        });

        switchPlaybackGain.setOnCheckedChangeListener(isChecked ->
                Toast.makeText(this, "Playback Gain: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show());

        switchFETCompressor.setOnCheckedChangeListener(isChecked ->
                Toast.makeText(this, "FET Compressor: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show());

        switchViperDDC.setOnCheckedChangeListener(isChecked -> {
            if (isChecked) {
                tvViperTimer.setVisibility(View.VISIBLE);
                timerSeconds = 0;
                timerHandler.post(timerRunnable);
            } else {
                tvViperTimer.setVisibility(View.GONE);
                timerHandler.removeCallbacks(timerRunnable);
            }
            Toast.makeText(this, "ViPER-DDC: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
        });

        switchSpectrumExtension.setOnCheckedChangeListener(isChecked ->
                Toast.makeText(this, "Spectrum Extension: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show());

        switchFIREqualizer.setOnCheckedChangeListener(isChecked ->
                Toast.makeText(this, "FIREqualizer: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show());

        switchConvolver.setOnCheckedChangeListener(isChecked ->
                Toast.makeText(this, "Convolver: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show());

        switchFieldSurround.setOnCheckedChangeListener(isChecked ->
                Toast.makeText(this, "Field Surround: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(timerRunnable);
    }
}