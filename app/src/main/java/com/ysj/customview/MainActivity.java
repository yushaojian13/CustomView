package com.ysj.customview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.ysj.customview.views.LoadingIndicatorView;

public class MainActivity extends AppCompatActivity {
    private LoadingIndicatorView loadingIndicatorView;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingIndicatorView = (LoadingIndicatorView) findViewById(R.id.simple_view);
        toggleButton = (ToggleButton) findViewById(R.id.btn_toggle);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    loadingIndicatorView.setIndicatorColor(Color.BLUE);
                } else {
                    loadingIndicatorView.setIndicatorColor(Color.RED);
                }
            }
        });
    }
}
