package com.example.todoapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class FormActivity extends AppCompatActivity {

    EditText editTitle;
    RadioButton rbCritical;
    RadioButton rbMajor;
    RadioButton rbMinor;
    View rootView;
    Task.Status STATUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        rootView=getWindow().getDecorView().getRootView();
        editTitle=findViewById(R.id.editTitle);
        rbCritical=findViewById(R.id.rb_critical);
        rbMajor=findViewById(R.id.rb_major);
        rbMinor=findViewById(R.id.rb_minor);
        rbMajor.setChecked(true);

        Task task=(Task)getIntent().getSerializableExtra("task");
        if(task!=null){ showTask(task);}
    }

    private void showTask(Task task) {
        editTitle.setText(task.getTitle());
        switch (task.getSTATUS()){
            case CRITICAL:
                rbCriticalClick(rootView);
                break;
            case MAJOR:
                rbMajorClick(rootView);
                break;
            case MINOR:
                rbMinorClick(rootView);
                break;
        }
    }

    public void onClick(View view) {
        String title=editTitle.getText().toString().trim();
        Task task=new Task();
        task.setTitle(title);
        task.setSTATUS(STATUS);
        Intent intent=new Intent();
        intent.putExtra("task",task);
        setResult(RESULT_OK,intent);
        finish();
    }


    public void rbCriticalClick(View view) {
        uncheckAll(rbCritical);
        STATUS= Task.Status.CRITICAL;
    }

    public void rbMajorClick(View view) {
        uncheckAll(rbMajor);
        STATUS= Task.Status.MAJOR;
    }

    public void rbMinorClick(View view) {
        uncheckAll(rbMinor);
        STATUS= Task.Status.MINOR;
    }

    private void uncheckAll(RadioButton checkedRabioButton){
        rbCritical.setChecked(false);
        rbMajor.setChecked(false);
        rbMinor.setChecked(false);
        checkedRabioButton.setChecked(true);
    }
}
