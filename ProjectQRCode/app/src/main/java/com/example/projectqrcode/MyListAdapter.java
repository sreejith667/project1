package com.example.projectqrcode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private ArrayList<MyListData> listdata;
    Context publics;
    // RecyclerView recyclerView;
    public MyListAdapter(ArrayList<MyListData> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        publics=parent.getContext();
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        final MyListData myListData = listdata[position];
//        holder.textView.setText(listdata[position].getDescription());
//        holder.imageView.setImageResource(listdata[position].getImgId());

        holder.Vechilename.setText(listdata.get(position).regnum);
holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


//        listdata.get(position).regnum;
//        listdata.get(position).rcowner;
//        listdata.get(position).vehicle_type;
//        listdata.get(position).chassisnum;



        Intent intent = new Intent(publics, viewqrcode.class);
        intent.putExtra("regnumber",  listdata.get(position).regnum);
        intent.putExtra("rcowner",  listdata.get(position).rcowner);
        intent.putExtra("vehicle_type",  listdata.get(position).vehicle_type);
        intent.putExtra("chassisnum",  listdata.get(position).chassisnum);
        intent.putExtra("id",  listdata.get(position).id); // pass your values and retrieve them in the other Activity using keyName

        publics.startActivity(intent);
    }
});

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView   Vechilename;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.Vechilename = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}