package com.example.cardiacrecord;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewholder> {
    private Context mContext;
    private ArrayList<ModelClass> mclass;
    private ModelClass modelClass;
    private ClickListener clickListener;
    public  TaskAdapter(Context context, ArrayList<ModelClass>mclass) {
        this.mclass= mclass;
        this.mContext = context;
    }
    class TaskViewholder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView tx1,tx2,tx3,tx4,tx5;
        Button editButton,deleteButton;
        TextView status;
        ConstraintLayout cardView;

        public TaskViewholder(@NonNull View itemView) {
            super(itemView);
            tx1= itemView.findViewById(R.id.date_value);
            tx2= itemView.findViewById(R.id.systolic_value);
            tx3=itemView.findViewById(R.id.diastolic_value);
            tx4=itemView.findViewById(R.id.heart_rate_value);
            tx5=itemView.findViewById(R.id.item_status);
            editButton=itemView.findViewById(R.id.edit_btn);
            deleteButton = itemView.findViewById(R.id.delete_btn);
            cardView = itemView.findViewById(R.id.constraintLayout2);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


        }

        @Override
        public void onClick(View view) {
            clickListener.customOnClick(getAdapterPosition(), view);

        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.customOnLongClick(getAdapterPosition(), view);
            return true;
        }
    }
    public void setClickListener(ClickListener clickL)
    {
        this.clickListener = clickL;
    }

    @NonNull
    @Override
    public TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext())  ;
        View view= inflater.inflate(R.layout.singlerow,parent,false);
        return new TaskViewholder (view);
    }



    public interface ClickListener {
        void customOnClick(int position, View v);
        void customOnLongClick(int position, View v);
        void onDeleteClick(int position);
        void onEditClick(int position);
        void DetailClick(int position);
    }



    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.tx1.setText(mclass.get(position).getDate());
        holder.tx2.setText(mclass.get(position).getSystolic());
        holder.tx3.setText(mclass.get(position).getDiastolic());
        holder.tx4.setText(mclass.get(position).getBloodPressure());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onDeleteClick(position);
            }
        });


        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onEditClick(position);
            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.DetailClick(position);

            }
        });


        modelClass = mclass.get(position);

        int count_val = 0;

        if (Integer.parseInt(modelClass.getDiastolic())>=60 &&  Integer.parseInt(modelClass.getDiastolic())<=90)
        {
            holder.tx2.setTextColor(Color.parseColor("#000000"));
            count_val = count_val + 1;
        }
        else holder.tx3.setTextColor(Color.parseColor("#E74C3C"));


        if (Integer.parseInt(modelClass.getSystolic())>=90 && Integer.parseInt(modelClass.getSystolic())<=140) {
            holder.tx3.setTextColor(Color.parseColor("#000000"));
            count_val = count_val + 1;
        }
        else holder.tx2.setTextColor(Color.parseColor("#E74C3C"));


        if (Integer.parseInt(modelClass.getBloodPressure())>=60 && Integer.parseInt(modelClass.getBloodPressure())<=100) {
            holder.tx4.setTextColor(Color.parseColor("#000000"));
            count_val = count_val + 1;
        }
        else holder.tx4.setTextColor(Color.parseColor("#E74C3C"));


        if (count_val < 3) {
            holder.tx5.setBackgroundColor(Color.parseColor("#E74C3C"));
        }
        else holder.tx5.setBackgroundColor(Color.parseColor("#27AE60"));

    }


    @Override
    public int getItemCount() {
        return mclass.size() ;
    }
}

