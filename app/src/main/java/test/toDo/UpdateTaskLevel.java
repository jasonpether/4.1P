package test.toDo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateTaskLevel extends AppCompatActivity {
    EditText upTasktitle, upTaskDesc, uptaskYear, uptaskMonth, uptaskDay;
    Button updateButton, deleteButton;

    String id, title, desc, year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task_level);

       upTasktitle = findViewById(R.id.upETTaskTitle);
       upTaskDesc = findViewById(R.id.upETTaskDesc);
       uptaskYear = findViewById(R.id.upETYear);
       uptaskMonth = findViewById(R.id.upETMonth);
       uptaskDay = findViewById(R.id.upETDay);
       updateButton = findViewById(R.id.updateTaskButton);
       deleteButton =findViewById(R.id.DeleteTaskButton);

       getTaskData();
       updateButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               DB taskDB = new DB(UpdateTaskLevel.this);
               title=upTasktitle.getText().toString();
               desc=upTaskDesc.getText().toString();
               year=uptaskYear.getText().toString();
               month=uptaskMonth.getText().toString();
               day=uptaskDay.getText().toString();

               if (Integer.valueOf(month) <= 0 || Integer.valueOf(month)  > 12
                       || Integer.valueOf(day)<= 0 || Integer.valueOf(day) > 31)
               {
                   Toast.makeText(UpdateTaskLevel.this, "ERROR Invalid Month or Day, Month 1-12, Day MAX 31", Toast.LENGTH_SHORT).show();
               }
               else if (Integer.valueOf(month) == 2 &&  (Integer.valueOf(day) > 28 || Integer.valueOf(day) <= 0))
               {
                   Toast.makeText(UpdateTaskLevel.this, "ERROR Invalid Day, Day MAX 28", Toast.LENGTH_SHORT).show();
               }
               else if ((Integer.valueOf(month) == 4 ||
                       Integer.valueOf(month) == 6 ||
                       Integer.valueOf(month) == 9 ||
                       Integer.valueOf(month) == 11) &&
                       Integer.valueOf(day) <= 0 ||
                       Integer.valueOf(day) > 30 )
               {
                   Toast.makeText(UpdateTaskLevel.this, "ERROR Invalid Day, Day MAX 30", Toast.LENGTH_SHORT).show();
               }
               else {
                   taskDB.updateTaskData(id, title, desc, year, month, day);
                   finish();
               }
           }
       });

       deleteButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            DB taskDB = new DB(UpdateTaskLevel.this);
            taskDB.deleteTask(id);
           finish();
           }
       });

    }

    void getTaskData(){
        if ((getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("year")
                && getIntent().hasExtra("month") && getIntent().hasExtra("day")))
        {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            desc = getIntent().getStringExtra("description");
            year = getIntent().getStringExtra("year");
            month = getIntent().getStringExtra("month");
            day = getIntent().getStringExtra("day");

            upTasktitle.setText(title);
            upTaskDesc.setText(desc);
            uptaskYear.setText(year);
            uptaskMonth.setText(month);
            uptaskDay.setText(day);
        }
        else {
            Toast.makeText(this, "None", Toast.LENGTH_SHORT).show();
        }
    }
}