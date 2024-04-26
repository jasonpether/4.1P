package test.toDo;

import android.app.Activity;
import android.app.NativeActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
// JASON PETHER ID:222060862

public class MainActivity extends AppCompatActivity {
    RecyclerView TaskRV;
    FloatingActionButton AddTaskButton, sortButton;
    DB myTaskDB;
    ArrayList<String> task_id, task_title, task_desc, task_year, task_month, task_day;
    CAdapter Cadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TaskRV = findViewById(R.id.TaskRV);
        AddTaskButton = findViewById(R.id.AddTaskbutton);
        AddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addtaskintent = new Intent(MainActivity.this, AddTaskLevel.class);
                startActivity(addtaskintent);
            }
        });

        myTaskDB = new DB(MainActivity.this);
        task_id = new ArrayList<>();
        task_title = new ArrayList<>();
        task_desc = new ArrayList<>();
        task_year = new ArrayList<>();
        task_month = new ArrayList<>();
        task_day = new ArrayList<>();

        showData();

        Cadapter = new CAdapter(MainActivity.this, this, task_id, task_title, task_desc, task_year, task_month, task_day);
        TaskRV.setAdapter(Cadapter);
        TaskRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            recreate();
        }
    }

    void showData(){
        Cursor cursor = myTaskDB.readData();
        if (cursor.getCount() == 0)
            Toast.makeText(this, "No Tasks", Toast.LENGTH_SHORT).show();
        else {
            while (cursor.moveToNext()){
                task_id.add(cursor.getString(0));
                task_title.add(cursor.getString(1));
                task_desc.add(cursor.getString(2));
                task_year.add(cursor.getString(3));
                task_month.add(cursor.getString(4));
                task_day.add(cursor.getString(5));
            }

        }
    }
}