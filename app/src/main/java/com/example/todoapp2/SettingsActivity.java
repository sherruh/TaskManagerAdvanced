package com.example.todoapp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SettingsActivity extends AppCompatActivity {

    List<ImageView> imageViewList;
    ColorDrawable colorDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        imageViewList=new ArrayList<>();
        imageViewList.add((ImageView)findViewById(R.id.theme1_image));
        imageViewList.add((ImageView)findViewById(R.id.theme2_image));
        imageViewList.add((ImageView)findViewById(R.id.theme3_image));
        imageViewList.add((ImageView)findViewById(R.id.theme4_image));
        imageViewList.add((ImageView)findViewById(R.id.theme5_image));
        imageViewList.add((ImageView)findViewById(R.id.theme6_image));
        imageViewList.add((ImageView)findViewById(R.id.theme7_image));
        imageViewList.add((ImageView)findViewById(R.id.theme8_image));

    }

    public void onClickTheme(View view) {

        ImageView imageView= (ImageView) view;
        colorDrawable = ((ColorDrawable)imageView.getDrawable());
        SharedPreferences preferences=getSharedPreferences("settings",MODE_PRIVATE);
        preferences.edit().putString("theme",String.valueOf(colorDrawable.getColor())).apply();
        int intColor=colorDrawable.getColor();
        String hexColor = String.format("#%06X", (0xFFFFFF & colorDrawable.getColor()));
        Log.d("MyApp","Color hex "+hexColor);
        deselctAllImages();
        imageView.setPadding(15,15,15,15);

    }

    private void deselctAllImages(){
        for(ImageView imageView:imageViewList){
            imageView.setPadding(0,0,0,0);
            //imageView.setImageDrawable(colorDrawable);


        }
    }

    public void onClickFont(View view) {
        TextView textView=(TextView)view;
        ((TextView)findViewById(R.id.font_normal)).setBackgroundResource(R.color.white);
        ((TextView)findViewById(R.id.font_bold)).setBackgroundResource(R.color.white);
        ((TextView)findViewById(R.id.font_italic)).setBackgroundResource(R.color.white);

        textView.setBackgroundResource(R.color.yellow);
        Log.d("MyApp", String.valueOf(textView.getId()));
        String font="Normal";
        switch (textView.getId()){
            case R.id.font_normal:
                font="Normal";
                break;
            case R.id.font_bold:
                font="Bold";
                break;
            case R.id.font_italic:
                font="Italic";
                break;
        }

        SharedPreferences preferences=getSharedPreferences("settings",MODE_PRIVATE);
        preferences.edit().putString("font",font).apply();

    }

    public void onClickSave(View view) {
        finish();
    }
}
