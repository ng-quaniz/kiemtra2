package com.example.cayxanh;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class ListviewAdapter extends RecyclerView.Adapter<ListviewAdapter.MyViewHolder> {
    Context context;
    ArrayList<listview> list;
    boolean kt = false;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private String a;


    public ListviewAdapter(Context context, ArrayList<listview> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.listview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        listview listview = list.get(position);
        holder.tv_name.setText(listview.getName());
        holder.tv_name2.setText(listview.getName2());
        holder.tv_characteristic.setText(listview.getCharacteristic());
        holder.tv_color.setText(listview.getColor());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("", (Serializable) listview);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                kt = true;
                AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(context);
                alertDiaLog.setTitle("Are you delete?");
                alertDiaLog.setIcon(R.mipmap.ic_launcher);
                alertDiaLog.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference.child("art").child(databaseReference.getRef().getKey()).setValue(null);
                        Toast.makeText(context, "Delate Success..", Toast.LENGTH_SHORT).show();

                    }
                });
                alertDiaLog.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDiaLog.show();
                return false;
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_name2,tv_characteristic,tv_color;

        private LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_name2 = itemView.findViewById(R.id.tv_name2);
            tv_characteristic = itemView.findViewById(R.id.tv_characteristic);
            tv_color = itemView.findViewById(R.id.tv_color);

        }
    }
}
