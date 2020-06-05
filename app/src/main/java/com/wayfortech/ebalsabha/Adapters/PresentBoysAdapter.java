package com.wayfortech.ebalsabha.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wayfortech.ebalsabha.Model.Child;
import com.wayfortech.ebalsabha.R;

import java.util.ArrayList;

public class PresentBoysAdapter extends RecyclerView.Adapter<PresentBoysAdapter.PresentBoysViewHolder> {

    private Context context;
    private ArrayList<Child> presentChildList=new ArrayList<>();

    public PresentBoysAdapter(Context context, ArrayList<Child> presentChildList) {
        this.context = context;
        this.presentChildList = presentChildList;
    }

    @NonNull
    @Override
    public PresentBoysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.presentboy_item,parent,false);
        return new PresentBoysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresentBoysViewHolder holder, int position) {
        holder.cid.setText(String.valueOf(presentChildList.get(position).getCid()));
        holder.name.setText(presentChildList.get(position).getCname()+" "+presentChildList.get(position).getCsurname());
    }

    @Override
    public int getItemCount() {
        return presentChildList.size();
    }

    public class PresentBoysViewHolder extends RecyclerView.ViewHolder {
        private TextView name,cid;
        public PresentBoysViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Attendance_presentChildName);
            cid=itemView.findViewById(R.id.Attendance_presentBoyCID);

        }
    }
}
