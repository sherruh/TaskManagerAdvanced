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
        LayoutInflater layout=getLayoutInflater();
        ImageView imageView= (ImageView) view;
        deselctAllImages();
        imageView.setPadding(10,10,10,10);

    }

    private void deselctAllImages(){
        for(ImageView imageView:imageViewList){
            imageView.setPadding(0,0,0,0);
        }
    }

    public void onClickGrid(View view) {

    }
}
