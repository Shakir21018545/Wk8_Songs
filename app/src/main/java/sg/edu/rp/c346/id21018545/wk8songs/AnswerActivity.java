package sg.edu.rp.c346.id21018545.wk8songs;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Song> songList;
    //ArrayAdapter<Song> adapter;
    Button btn5Stars;
    ArrayList<String> years;
    CustomAdapter adapter;



    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        songList.clear();
        songList.addAll(dbh.getAllSongs());
        adapter.notifyDataSetChanged();

        years.clear();
        years.addAll(dbh.getYears());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_answer));

        lv = this.findViewById(R.id.lv);
        btn5Stars = this.findViewById(R.id.btn5Stars);

        DBHelper dbh = new DBHelper(this);
        songList = dbh.getAllSongs();
        years = dbh.getYears();
        dbh.close();

        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songList);
        //lv.setAdapter(adapter);
        adapter = new CustomAdapter(this, R.layout.row,songList);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(AnswerActivity.this, EditSong.class);
                i.putExtra("song", songList.get(position));
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(AnswerActivity.this);
                songList.clear();
                songList.addAll(dbh.getAllSongsByStars(5));
                adapter.notifyDataSetChanged();
            }
        });



    }
}