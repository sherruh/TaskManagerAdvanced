package com.example.todoapp2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ClickListener clickListener;
    public interface ClickListener{
        void onClick(int pos);
    }

    List<Task> list;

    public TaskAdapter(List<Task> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_task,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Task task = list.get(i);
        viewHolder.textTitle.setText(task.getTitle());
        viewHolder.textDesc.setText(task.getDescription());
        setStatusColor(viewHolder,task);
    }

    private void setStatusColor(ViewHolder viewHolder, Task task) {
        switch (task.getSTATUS()){
            case CRITICAL:
                viewHolder.layoutStatus.setBackgroundColor(viewHolder.
                        itemView.getResources().getColor(R.color.red));
                break;
            case MAJOR:
                viewHolder.layoutStatus.setBackgroundColor(viewHolder.
                        itemView.getResources().getColor(R.color.orange));
                break;
            case MINOR:
                viewHolder.layoutStatus.setBackgroundColor(viewHolder.
                        itemView.getResources().getColor(R.color.green));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        TextView textDesc;
        FrameLayout layoutStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle=itemView.findViewById(R.id.tetxTitle);
            textDesc=itemView.findViewById(R.id.textDescription);
            layoutStatus=itemView.findViewById(R.id.layout_status);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
