package com.example.cayxanh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.io.IOException;

public class Addlist extends AppCompatActivity {
    EditText edname, edname2, edcolor, edcharacteristic;
    ImageView edimg;
    Button add;
    ImageButton back;
    int Request_Code = 2;
    Uri pathuri;
    ProgressDialog progressDialog;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);

        edname = (EditText) findViewById(R.id.edname);
        edname2 = (EditText) findViewById(R.id.edname2);
        edcharacteristic = (EditText) findViewById(R.id.edcharacteristic);
        edcolor = (EditText) findViewById(R.id.edcolor);

        back = (ImageButton) findViewById(R.id.back);
        add = (Button) findViewById(R.id.add);
        databaseReference = FirebaseDatabase.getInstance().getReference("art");
        progressDialog = new ProgressDialog(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Addlist.this, ListviewActivity.class);
                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Request_Code && resultCode==RESULT_OK && data !=null && data.getData() !=null){
            pathuri=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), pathuri); edimg.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void Upload() {
        progressDialog.show();
        String name = edname.getText().toString().trim();
        String name2 = edname2.getText().toString().trim();
        String characteristic = edcharacteristic.getText().toString().trim();
        String color = edcolor.getText().toString().trim();
        progressDialog.dismiss();
        String id = databaseReference.push().getKey();
        listview listview = new listview(name, name2, color, characteristic);
        databaseReference.child(id).setValue(listview);
        Toast.makeText(Addlist.this, "Upload Success..", Toast.LENGTH_SHORT).show();
        edname.setText("");
        edname2.setText("");
        edcharacteristic.setText("");
        edcolor.setText("");
    }

}