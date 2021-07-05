package com.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ConnectedEmailsAdapter extends RecyclerView.Adapter<ConnectedEmailsAdapter.MyViewHolder> {
Context context;
private ConnectedEmailsModels[] mylist;


    public ConnectedEmailsAdapter(Context context,ConnectedEmailsModels[] connectedemailArrayList) {
        this.context = context;
        this.mylist = connectedemailArrayList;
    }
    @NonNull
    @Override
    public ConnectedEmailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
//        ViewHolder viewHolder = new ViewHolder(listItem);
//        return viewHolder;
        Resources r = parent.getResources();
        View v = LayoutInflater.from(context).inflate(R.layout.layout_connected_emails, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConnectedEmailsAdapter.MyViewHolder holder, int position) {
       ConnectedEmailsModels current = mylist[position];
       holder.f.setText(mylist[position].deviceName);
       holder.s.setText(""+(position+1));
//        holder.devicename.setText("Device Name");
//        holder.always.setText("always");
//        holder.files.setText("Files");
//      //  holder.calllogs.setText("CAllogs");
//        holder.sms.setText("Sms");
//        holder.all.setText("all");
//        holder.once.setText("yeah");

    }

    @NonNull
    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
     ConstraintLayout cons;
     TextView f,s;
     CheckBox call,sms,calllogs,files,all,once,always, devicename;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            s =itemView.findViewById(R.id.Sno);
            f=itemView.findViewById(R.id.ConnDevices);
            devicename = itemView.findViewById(R.id.DeviceName);
            call=itemView.findViewById(R.id.call);
            sms = itemView.findViewById(R.id.messaging);
            files=itemView.findViewById(R.id.files);
             all = itemView.findViewById(R.id.All);
             once= itemView.findViewById(R.id.Once);
            always=itemView.findViewById(R.id.Always);

            cons = itemView.findViewById(R.id.connecteddevicelayout);
        }
    }
}
