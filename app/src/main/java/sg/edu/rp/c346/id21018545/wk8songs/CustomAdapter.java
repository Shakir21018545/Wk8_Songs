package sg.edu.rp.c346.id21018545.wk8songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;

    ArrayList<Song> songs;

    public CustomAdapter(Context context, int resource,
                         ArrayList<Song> objects){
        super(context, resource, objects);

        //parent_context = context;
       // layout_id = resource;
        //versionList = objects;

        this.parent_context = context;
        this.layout_id = resource;
        this.songs = objects;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textTitle);
        TextView tvYear = rowView.findViewById(R.id.textYear);
        TextView tvStar = rowView.findViewById(R.id.textStar);
        TextView tvSinger = rowView.findViewById(R.id.textSinger);


        // Obtain the Android Version information based on the position
        Song currentSong = songs.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentSong.getTitle());
        tvSinger.setText(currentSong.getSingers());
        String star ="";
        for(int i=0; i< currentSong.getStars();i++){
            star += "*";
        }
        tvStar.setText(star);
        tvYear.setText(currentSong.getYearReleased()+"");

        return rowView;
    }


}

