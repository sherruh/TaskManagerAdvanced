package com.example.todoapp2;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private List<Task> tasksAll;
    private List<Task> tasksCritical;
    private List<Task> tasksMajor;
    private List<Task> tasksMinor;
    private List<Task> tasksDeleted;
    private int position;
    private Task.Status STATUS_FILTER;
    private AlertDialog.Builder ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,FormActivity.class   );
                startActivityForResult(intent,100);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initList();
        initAlertDialodDelete();
    }

    private void initAlertDialodDelete() {
        ad=new AlertDialog.Builder(MainActivity.this);
        ad.setMessage("Delete this task?");
        ad.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(MainActivity.this, "Task deleted",
                        Toast.LENGTH_LONG).show();
                deleteTask(taskList.get(position));
            }
        });
        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(MainActivity.this, "Task didn't delete", Toast.LENGTH_LONG)
                        .show();
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "Task didn't delete",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteTask(Task task) {
        task.setSTATUS(Task.Status.DELETED);
        distributeTask(task);
        setTaskToList(task,tasksDeleted);
        taskAdapter.notifyDataSetChanged();
    }

    private void initList(){
        tasksAll=new ArrayList<>();
        tasksCritical=new ArrayList<>();
        tasksMajor=new ArrayList<>();
        tasksMinor=new ArrayList<>();
        tasksDeleted=new ArrayList<>();
        taskList=new ArrayList<>();
        taskList=generateTasks();

        STATUS_FILTER=Task.Status.ALL;

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter=new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.setClickListener(new TaskAdapter.ClickListener() {
            @Override
            public void onClick(int pos) {
                position=pos;
                Intent intent=new Intent(MainActivity.this,FormActivity.class);
                intent.putExtra("task",taskList.get(pos));
                startActivityForResult(intent,101);
            }

            @Override
            public void onLongClick(int pos) {
                position=pos;
                ad.show();
            }
        });
    }

    private List<Task> generateTasks() {
        List<Task> tasks=new ArrayList<>();
        Task task1=new Task();
        task1.setTitle("Task1");
        task1.setDescription("Description1");
        task1.setSTATUS(Task.Status.CRITICAL);
        Task task2=new Task();
        task2.setTitle("Task2");
        task2.setDescription("Description2");
        task2.setSTATUS(Task.Status.MINOR);
        Task task3=new Task();
        task3.setTitle("Task3");
        task3.setDescription("Description3");
        task3.setSTATUS(Task.Status.MAJOR);
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        for (Task task:tasks){
            distributeTask(task);
        }
        return tasks;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && data!=null){
            switch (requestCode){
                case 100:
                    Task task= (Task) data.getSerializableExtra("task");
                    distributeTask(task);
                    showTask(task);
                    break;
                case 101:
                    taskList.set(position,(Task)data.getSerializableExtra("task"));
                    taskAdapter.notifyDataSetChanged();
                    break;
            }
            taskAdapter.notifyDataSetChanged();
        }
    }

    private void showTask(Task task) {
        if(task.getSTATUS()==STATUS_FILTER || STATUS_FILTER== Task.Status.ALL){
            taskList.add(0,task);
        }
    }

    private void distributeTask(Task task){
        Task.Status STATUS=task.getSTATUS();
        switch (STATUS){
            case CRITICAL:
                setTaskToList(task,tasksCritical);
                break;
            case MAJOR:
                setTaskToList(task,tasksMajor);
                break;
            case MINOR:
                setTaskToList(task,tasksMinor);
                break;
            case DELETED:
                tasksDeleted.add(0,task);
                tasksAll.remove(task);
                taskList.remove(task);
        }
        if(STATUS!= Task.Status.DELETED){
            tasksAll.add(0,task);
        }
    }

    void setTaskToList(Task task,List<Task> targetList){
        if(tasksCritical.contains(task)){
            tasksCritical.remove(task);
        }
        if(tasksMajor.contains(task)){
            tasksMajor.remove(task);
        }
        if(tasksMinor.contains(task)){
            tasksMinor.remove(task);
        }
        targetList.add(0,task);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            switchTasks(tasksAll);
            STATUS_FILTER= Task.Status.ALL;
        } else if (id == R.id.nav_critical) {
            switchTasks(tasksCritical);
            STATUS_FILTER= Task.Status.CRITICAL;
        } else if (id == R.id.nav_major) {
            switchTasks(tasksMajor);
            STATUS_FILTER= Task.Status.MAJOR;
        } else if (id == R.id.nav_minor) {
            switchTasks(tasksMinor);
            STATUS_FILTER= Task.Status.MINOR;
        } else if (id == R.id.nav_deleted) {
            STATUS_FILTER= Task.Status.DELETED;
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchTasks(List<Task> currentTasks){
        taskList.clear();
        taskAdapter.notifyDataSetChanged();
        taskList.addAll(currentTasks);
        taskAdapter.notifyDataSetChanged();
    }
}
