package test.toDo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddTaskLevel extends AppCompatActivity {
    EditText ETTitle, ETDesc, ETYear, ETMonth, ETDay;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_level);
        ETTitle = findViewById(R.id.ETTaskTitle);
        ETDesc = findViewById(R.id.ETTaskDesc);
        ETYear = findViewById(R.id.ETYear);
        ETMonth =findViewById(R.id.ETMonth);
        ETDay = findViewById(R.id.ETDay);
        Submit = findViewById(R.id.SubmitTaskButton);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB TaskDB = new DB(AddTaskLevel.this);
                if (Integer.valueOf(ETMonth.getText().toString()) <= 0 || Integer.valueOf(ETMonth.getText().toString()) > 12
                        || Integer.valueOf(ETDay.getText().toString())<= 0 || Integer.valueOf(ETDay.getText().toString()) > 31)
                {
                    Toast.makeText(AddTaskLevel.this, "ERROR Invalid Month or Day, Month 1-12, Day MAX 31", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.valueOf(ETMonth.getText().toString()) == 2 &&  (Integer.valueOf(ETDay.getText().toString()) > 28 || Integer.valueOf(ETDay.getText().toString()) <= 0))
                {
                    Toast.makeText(AddTaskLevel.this, "ERROR Invalid Day, Day MAX 28", Toast.LENGTH_SHORT).show();
                }
                else if ((Integer.valueOf(ETMonth.getText().toString()) == 4 ||
                        Integer.valueOf(ETMonth.getText().toString()) == 6 ||
                        Integer.valueOf(ETMonth.getText().toString()) == 9 ||
                        Integer.valueOf(ETMonth.getText().toString()) == 11) &&
                        Integer.valueOf(ETDay.getText().toString()) <= 0 ||
                        Integer.valueOf(ETDay.getText().toString()) > 30 )
                {
                    Toast.makeText(AddTaskLevel.this, "ERROR Invalid Day, Day MAX 30", Toast.LENGTH_SHORT).show();
                }
                else {
                    TaskDB.addTask(ETTitle.getText().toString(),ETDesc.getText().toString(), Integer.valueOf(ETYear.getText().toString()),
                            Integer.valueOf(ETMonth.getText().toString()), Integer.valueOf(ETDay.getText().toString()));
                    Intent Back = new Intent(AddTaskLevel.this, MainActivity.class);
                    startActivity(Back);
                }

            }
        });
    }
}