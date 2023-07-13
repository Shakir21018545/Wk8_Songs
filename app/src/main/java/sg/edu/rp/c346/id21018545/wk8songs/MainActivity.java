package sg.edu.rp.c346.id21018545.wk8songs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    Button btnInsert, btnShowList;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_main));

        etTitle = findViewById(R.id.editTextTitle);
        etSingers = findViewById(R.id.editTextSinger);
        etYear = findViewById(R.id.editTextYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnGetTasks);
        rg = findViewById(R.id.RadioGroupStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString().trim();
                String singers = etSingers.getText().toString().trim();
                if (title.length() == 0 || singers.length() == 0) {
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }


                String year_str = etYear.getText().toString().trim();
                int year = 0;
                try {
                    year = Integer.valueOf(year_str);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);

                int stars = getStars();
                dbh.insertSong(title, singers, year, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etTitle.setText("");
                etSingers.setText("");
                etYear.setText("");

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, AnswerActivity.class);
                startActivity(i);
            }
        });

    }


    private int getStars() {
        int stars = 1;
        if(rg.getCheckedRadioButtonId()==R.id.radioButton1) {
            stars = 1;

        }
        if(rg.getCheckedRadioButtonId()==R.id.radioButton2) {
            stars = 2;

        }
        if(rg.getCheckedRadioButtonId()==R.id.radioButton3) {
            stars = 3;

        }
        if(rg.getCheckedRadioButtonId()==R.id.radioButton4) {
            stars = 4;

        }
        if(rg.getCheckedRadioButtonId()==R.id.radioButton5) {
            stars = 5;

        }

        return stars;
    }

}