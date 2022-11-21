package com.example.cayxanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    ImageButton dtback;
    TextView name, name2, color, characteristi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dtback = (ImageButton) findViewById(R.id.dtback);

        name = (TextView) findViewById(R.id.name);
        name2 = (TextView) findViewById(R.id.name2);
        color = (TextView) findViewById(R.id.color);
        characteristi = (TextView) findViewById(R.id.characteristic);


        dtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, ListviewActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        listview list = (listview) bundle.get("");
        name.setText(list.getName());
        name2.setText(list.getName2());
        color.setText(list.getColor());
        characteristi.setText(list.getCharacteristic());
    }
}