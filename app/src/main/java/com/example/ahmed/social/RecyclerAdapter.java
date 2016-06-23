package com.example.ahmed.social;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Ahmed on 6/19/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    ImageView groupImage;
    private Context context;

    ArrayList<Contact> arrayList = new ArrayList<>();
    public RecyclerAdapter(Context  context, ArrayList<Contact> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;


    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Name.setText(arrayList.get(position).getName());
        holder.Email.setText(arrayList.get(position).getEmail());
        Picasso.with(context).load(arrayList.get(position).getImage()).resize(70,70).into(holder.groupImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name , Email;
        ImageView groupImage;



        public MyViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.name);
            Email= (TextView) itemView.findViewById(R.id.email);
            groupImage= (ImageView) itemView.findViewById(R.id.imageview);


        }
    }


}
