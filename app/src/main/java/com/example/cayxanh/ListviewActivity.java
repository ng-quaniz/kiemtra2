package com.example.cayxanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListviewActivity extends AppCompatActivity {


    ImageButton btadd;
    boolean kt = false;
    BottomNavigationView nav;

    RecyclerView recview;
    ListviewAdapter adapter;
    ArrayList<listview> list;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


        btadd = (ImageButton) findViewById(R.id.btadd);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recview = (RecyclerView) findViewById(R.id.recview);
        list = new ArrayList<>();
        adapter = new ListviewAdapter(this, list);
        recview.setLayoutManager(linearLayoutManager);
        databaseReference = FirebaseDatabase.getInstance().getReference("art");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    listview list2 = dataSnapshot.getValue(listview.class);
                    list.add(list2);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recview.setAdapter(adapter);




        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListviewActivity.this, Addlist.class);
                startActivity(intent);
            }
        });


    }

}