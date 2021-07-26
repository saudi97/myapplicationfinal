package com.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.MyViewHolder> {
    private int px=2;
    Context context;
    ArrayList<Smsmodel> smsModelArrayList;
    public SmsAdapter(Context context, ArrayList<Smsmodel> smsModelArrayList) {
        this.context = context;
        this.smsModelArrayList = smsModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Resources r = parent.getResources();
        View v = LayoutInflater.from(context).inflate(R.layout.layout_messages, parent, false);
        return new SmsAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int i = position;
        if(i == 0){
            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) holder.cardView.getLayoutParams();
            layoutParams.topMargin = px;
            holder.cardView.requestLayout();
        }
        Smsmodel currentLog = smsModelArrayList.get(position);
        holder.message.setText(currentLog.getMsg());
        holder.address.setText(currentLog.getAddress());
        holder.date.setText(currentLog.getDate());
    }

    @Override
    public int getItemCount() {
        return smsModelArrayList==null ? 0:smsModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView address,message,date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_message);
            address =itemView.findViewById(R.id.address);
            message=itemView.findViewById(R.id.Message);
            date=itemView.findViewById(R.id.dte);

        }

    }

}
