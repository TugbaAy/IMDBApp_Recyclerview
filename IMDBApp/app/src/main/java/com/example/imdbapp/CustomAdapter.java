package com.example.imdbapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<String> dataSet;
    private Activity activityContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName; // filmname
        TextView textViewYear; // year
        TextView textViewRate; //rate
        ImageView imageViewIcon; //img

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewYear = (TextView) itemView.findViewById(R.id.textViewYear);
            this.textViewRate = (TextView) itemView.findViewById(R.id.textViewRate);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(Activity context) {

        activityContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);

        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewYear = holder.textViewYear;
        TextView textViewRate = holder.textViewRate;

        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(MainActivity.filmName.get(listPosition));
        textViewYear.setText(MainActivity.year.get(listPosition));
        textViewRate.setText(MainActivity.rate.get(listPosition));
        Picasso.with(activityContext).load(MainActivity.urlMov.get(listPosition)).into(imageView);
    }
    @Override
    public int getItemCount() {
        return MainActivity.filmName.size();
    }
}
