package com.wayfortech.ebalsabha.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wayfortech.ebalsabha.Model.Child;
import com.wayfortech.ebalsabha.R;
import com.wayfortech.ebalsabha.UpdateProfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> implements Filterable {
    private Context context;
    private ArrayList<Child> childArrayList;
    private ArrayList<Child> tempChildList;
    public ChildAdapter(Context context, List<Child> childArrayList) {
        this.context=context;
        this.childArrayList= (ArrayList<Child>) childArrayList;
        this.tempChildList=new ArrayList<>(childArrayList);
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.child_item,parent,false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, final int position) {
       // holder.childImage.setImageBitmap(R.drawable.ic_launcher_backgroun);
        holder.childName.setText(childArrayList.get(position).getCname()+" "+childArrayList.get(position).getCfathername()+" "+childArrayList.get(position).getCsurname());
        holder.childCid.setText(String.valueOf(childArrayList.get(position).getCid()));
        holder.childDOB.setText(childArrayList.get(position).getCdob());
        holder.childView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, UpdateProfile.class).putExtra("CHILDINFO",childArrayList.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return childArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Child> filteredChildList=new ArrayList<>();
            if(charSequence.toString().isEmpty())
            {
                filteredChildList.addAll(tempChildList);
            }
            else
            {
                for(Child child:tempChildList)
                {
                    if(child.getCname().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filteredChildList.add(child);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredChildList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            childArrayList.clear();
            childArrayList.addAll((Collection<? extends Child>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ChildViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView childImage;
        private TextView childName,childDOB,childCid;
        private LinearLayout childView;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            childImage=itemView.findViewById(R.id.childImageView);
            childName=itemView.findViewById(R.id.childNameTextView);
            childDOB=itemView.findViewById(R.id.childDOBTextView);
            childView=itemView.findViewById(R.id.childView);
            childCid=itemView.findViewById(R.id.childCID);
        }
    }
}
