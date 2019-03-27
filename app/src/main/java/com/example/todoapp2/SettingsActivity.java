package com.example.todoapp2;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onClickTheme(View view) {
        LayoutInflater layout=getLayoutInflater();
        ImageView imageView= (ImageView) view;
        ColorDrawable drawable = (ColorDrawable) imageView.getBackground();
        getConte
    }
}
