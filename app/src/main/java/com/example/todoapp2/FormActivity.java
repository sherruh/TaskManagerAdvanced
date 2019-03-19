package com.example.todoapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    EditText editTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle=findViewById(R.id.editTitle);

        Task task=(Task)getIntent().getSerializableExtra("task");
        if(task!=null){ editTitle.setText(task.getTitle());}
    }

    public void onClick(View view) {
        String title=editTitle.getText().toString().trim();
        Task task=new Task();
        task.setTitle(title);
        Intent intent=new Intent();
        intent.putExtra("task",task);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void radiobuttonClick(View view) {
    }
}
