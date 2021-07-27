package com.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    //initialize variable
    Activity activity;
    ArrayList<ContactModel> arrayList;
    //create constructor
    public MainAdapter(Activity activity, ArrayList<ContactModel> arrayList){
        this.activity=activity;
        this.arrayList=arrayList;

        notifyDataSetChanged();
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        //initialize view
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact,parent,false);
        //return view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MainAdapter.ViewHolder holder, int position) {
        //initizalize contact model
        ContactModel model=arrayList.get(position);
        //set name
        holder.tvName.setText(model.getName());
        //set number
        holder.tvMumber.setText(model.getNumber());

    }

    @Override
    public int getItemCount() {
        //return array list size
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //initialize variable
        TextView tvName,tvMumber;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            //assign variable
            tvName=itemView.findViewById(R.id.tv_name);
            tvMumber=itemView.findViewById(R.id.tv_number);

        }
    }
}
