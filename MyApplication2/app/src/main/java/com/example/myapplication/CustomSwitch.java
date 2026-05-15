package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CustomSwitch extends FrameLayout {

    private TextView trackLabel;
    private View thumb;
    private boolean isChecked = false;
    private OnCheckedChangeListener listener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(boolean isChecked);
    }

    public CustomSwitch(Context context) {
        super(context);
        init(context);
    }

    public CustomSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomSwitch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_switch, this, true);
        trackLabel = findViewById(R.id.trackLabel);
        thumb = findViewById(R.id.thumb);
        updateUI();
        setOnClickListener(v -> toggle());
    }

    public void toggle() {
        setChecked(!isChecked);
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
        updateUI();
        if (listener != null) listener.onCheckedChanged(checked);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }

    private void updateUI() {
        if (isChecked) {
            trackLabel.setBackgroundResource(R.drawable.toggle_on);
            trackLabel.setText("ON");
            trackLabel.setTextColor(0xFF000000);
            trackLabel.setGravity(android.view.Gravity.CENTER_VERTICAL | android.view.Gravity.START);
            trackLabel.setPadding(dpToPx(12), 0, 0, 0);
            thumb.setBackgroundResource(R.drawable.thumb_circle_on);
            thumb.setLayoutParams(getThumbParams(true));
        } else {
            trackLabel.setBackgroundResource(R.drawable.toggle_off);
            trackLabel.setText("OFF");
            trackLabel.setTextColor(0xFFFFFFFF);
            trackLabel.setGravity(android.view.Gravity.CENTER_VERTICAL | android.view.Gravity.END);
            trackLabel.setPadding(0, 0, dpToPx(12), 0);
            thumb.setBackgroundResource(R.drawable.thumb_circle_off);
            thumb.setLayoutParams(getThumbParams(false));
        }
    }

    private FrameLayout.LayoutParams getThumbParams(boolean onRight) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(dpToPx(36), dpToPx(36));
        params.topMargin = dpToPx(0);
        if (onRight) {
            params.gravity = android.view.Gravity.END | android.view.Gravity.TOP;
            params.rightMargin = dpToPx(0);
        } else {
            params.gravity = android.view.Gravity.START | android.view.Gravity.TOP;
            params.leftMargin = dpToPx(0);
        }
        return params;
    }

    private int dpToPx(int dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }
}