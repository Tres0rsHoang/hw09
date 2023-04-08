package org.group08.week04hw;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    Context context;
    String[] names;
    String[] phones;
    Integer[] faces;

    public CustomAdapter(Context context, int layoutToBeInflated, String[] names, String[] phones, Integer[] faces) {
        super(context, layoutToBeInflated, names);
        this.context = context;
        this.names = names;
        this.phones = phones;
        this.faces = faces;
    }

    public View getView(int postition, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_row_human, null);

        ImageView happyFace = (ImageView) row.findViewById(R.id.happyFace);
        TextView tvName = (TextView) row.findViewById(R.id.tvName);
        TextView tvPhone = (TextView) row.findViewById(R.id.tvPhone);

        happyFace.setImageResource(faces[postition]);
        tvName.setText(names[postition]);
        tvPhone.setText(phones[postition]);

        return (row);
    }
}
