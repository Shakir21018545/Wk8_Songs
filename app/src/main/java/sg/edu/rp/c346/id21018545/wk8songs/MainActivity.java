package sg.edu.rp.c346.id21018545.wk8songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    ListView lv;
    ArrayAdapter<String> aaTodo;
    ArrayList<Song> alTodo;
    EditText editTextTitle, editTextSinger,editTextYear;
    RadioGroup radioGroup;
    boolean asc = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        radioGroup = findViewById(R.id.RadioGroupStars);
        lv = findViewById(R.id.lv);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSinger = findViewById(R.id.editTextSinger);
        editTextYear = findViewById(R.id.editTextYear);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                int rbid = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = findViewById(rbid);
                String text = rb.getText().toString();
                db.insertTask(editTextTitle.getText().toString(),
                        editTextSinger.getText().toString(),
                        editTextYear.getText().toString(),
                        text);

                db.close();

            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                //LIST VIEW
                DBHelper db2 = new DBHelper(MainActivity.this);
                alTodo = db2.getTasks(asc);
                db2.close();
                asc = !asc;
                aaTodo = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alTodo);
                lv.setAdapter(aaTodo);


            }
        });



    }
}