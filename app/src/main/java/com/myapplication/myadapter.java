package com.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter  extends FirebaseRecyclerAdapter<ContactModel,myadapter.myviewholder> {
    public myadapter(@NonNull FirebaseRecyclerOptions<ContactModel> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ContactModel model)
    {
      // ContactModel cd = new ContactModel();
        holder.tvName.setText(model.getName());
        holder.tvMumber.setText(model.getNumber());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView tvName,tvMumber;
        public myviewholder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            //assign variable
            tvName=itemView.findViewById(R.id.tv_name);
            tvMumber=itemView.findViewById(R.id.tv_number);

        }
    }
}
