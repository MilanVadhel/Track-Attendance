package com.wayfortech.ebalsabha.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wayfortech.ebalsabha.Model.Child;
import com.wayfortech.ebalsabha.R;

import java.util.ArrayList;

public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.BirthdayViewHolder>
{

    private Context context;
    private ArrayList<Child> childArrayList;

    public BirthdayAdapter(Context context, ArrayList<Child> childArrayList) {
        this.context = context;
        this.childArrayList = childArrayList;
    }

    @NonNull
    @Override
    public BirthdayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.birthday_item,parent,false);
        return new BirthdayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BirthdayViewHolder holder, int position) {
        holder.name.setText(childArrayList.get(position).getCname()+" "
                +childArrayList.get(position).getCfathername()
        +" "+childArrayList.get(position).getCsurname());
        holder.birthdate.setText(childArrayList.get(position).getCdob());
    }

    @Override
    public int getItemCount() {
        return childArrayList.size();
    }

    public class BirthdayViewHolder extends RecyclerView.ViewHolder {
        private TextView name,birthdate;
        private CardView card;
        public BirthdayViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Birthday_childName);
            birthdate=itemView.findViewById(R.id.Birthday_childBirthdate);
            card=itemView.findViewById(R.id.Birth_card);
        }
    }
}
