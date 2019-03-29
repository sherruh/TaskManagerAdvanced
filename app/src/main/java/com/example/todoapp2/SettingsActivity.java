package com.example.todoapp2;

import android.content.Intent;
import android.graphics.Color;
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

        Color background = imageView.getSolidColor();
        int color=0;
        if (background instanceof ColorDrawable) {
             color= ((ColorDrawable) background).getColor();
            // Use color here
        }
        Log.d("MyApp", String.valueOf(color));
        deselctAllImages();
        imageView.setPadding(15,15,15,15);

    }

    private void deselctAllImages(){
        for(ImageView imageView:imageViewList){
            imageView.setPadding(0,0,0,0);
        }
    }

    public void onClickFont(View view) {
        TextView textView=(TextView)view;
        ((TextView)findViewById(R.id.font_normal)).setBackgroundResource(R.color.white);
        ((TextView)findViewById(R.id.font_bold)).setBackgroundResource(R.color.white);
        ((TextView)findViewById(R.id.font_italic)).setBackgroundResource(R.color.white);
        textView.setBackgroundResource(R.color.yellow);


    }

    public void onClickSave(View view) {
        Intent intent=new Intent();
        Log.d("MyApp","Color "+ getResources().getColor(R.color.yellow));
        setResult(RESULT_OK,intent);

        finish();
    }
}
