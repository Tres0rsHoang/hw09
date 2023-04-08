package org.group08.week04hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView txtMsg;
    private ListView lvHuman;

    String[] names = {
            "Nguyễn Văn A",
            "Lê Thị B",
            "Trần Văn C",
            "Phan Văn D",
            "Đinh Văn E",
            "Nguyễn Văn F",
            "Lê Thị B",
            "Trần Văn C",
    };
    String[] phones = {
            "123",
            "456",
            "123456",
            "789",
            "012",
            "123535",
            "456",
            "123456",
    };

    Integer[] faces = {
            R.drawable.face1,
            R.drawable.face2,
            R.drawable.face3,
            R.drawable.face4,
            R.drawable.face5,
            R.drawable.face6,
            R.drawable.face1,
            R.drawable.face2,
            R.drawable.face3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        lvHuman = (ListView) findViewById(R.id.lvHuman);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_row_human, names, phones, faces);

        lvHuman.setAdapter(adapter);

        lvHuman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                txtMsg.setText(String.format("You Choose: %s", names[position]));
            }
        });
    }
}