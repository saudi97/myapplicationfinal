package com.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdapters  extends FirebaseRecyclerAdapter<CallLogModel, MyAdapters.myviewholder> {



    public MyAdapters(@NonNull FirebaseRecyclerOptions<CallLogModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_call_log,parent,false);
        return new myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull CallLogModel model) {
        holder.tv_ph_num.setText(model.getPhNumber());
        System.out.println(model.getCallDate());
        holder.tv_contact_name.setText(model.getContactName());
        holder.tv_call_type.setText(model.getCallType());
        holder.tv_call_date.setText(model.getCallDate());
        holder.tv_call_time.setText(model.getCallTime());
        holder.tv_call_duration.setText(model.getCallDuration());
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView tv_ph_num, tv_contact_name, tv_call_type, tv_call_date, tv_call_time, tv_call_duration;
        public myviewholder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            //assign variable
            tv_ph_num = itemView.findViewById(R.id.layout_call_log_ph_no);
            tv_contact_name = itemView.findViewById(R.id.layout_call_log_contact_name);
            tv_call_type = itemView.findViewById(R.id.layout_call_log_type);
            tv_call_date = itemView.findViewById(R.id.layout_call_log_date);
            tv_call_time = itemView.findViewById(R.id.layout_call_log_time);
            tv_call_duration = itemView.findViewById(R.id.layout_call_log_duration);
            cardView = itemView.findViewById(R.id.layout_call_log_cardview);

        }
    }}
