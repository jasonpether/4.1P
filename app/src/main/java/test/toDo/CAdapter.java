package test.toDo;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CAdapter extends RecyclerView.Adapter<CAdapter.MyViewHolder> {

    Context context;
    private ArrayList task_id, task_title, task_desc, task_year, task_month, task_day;
    Activity activity;
    int position;
    CAdapter(Activity activity, Context context, ArrayList task_id, ArrayList task_title, ArrayList task_desc, ArrayList task_year, ArrayList task_month, ArrayList task_day){
            this.context = context;
            this.task_id = task_id;
            this.task_title = task_title;
            this.task_desc = task_desc;
            this.task_year = task_year;
            this.task_month = task_month;
            this.task_day = task_day;
            this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from((context));
        View view = inflate.inflate(R.layout.tasklayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.TVtask_id.setText(String.valueOf(task_id.get(position)));
        holder.TVtask_title.setText(String.valueOf(task_title.get(position)));
        holder.TVtask_desc.setText(String.valueOf(task_desc.get(position)));
        holder.TVtask_year.setText(String.valueOf(task_year.get(position)));
        holder.TVtask_month.setText(String.valueOf(task_month.get(position)));
        holder.TVtask_day.setText(String.valueOf(task_day.get(position)));
        holder.MainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateTaskLevel.class);
                intent.putExtra("id", String.valueOf(task_id.get(position)));
                intent.putExtra("title", String.valueOf(task_title.get(position)));
                intent.putExtra("description", String.valueOf(task_desc.get(position)));
                intent.putExtra("year", String.valueOf(task_year.get(position)));
                intent.putExtra("month", String.valueOf(task_month.get(position)));
                intent.putExtra("day", String.valueOf(task_day.get(position)));

                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TVtask_id, TVtask_title, TVtask_desc, TVtask_year, TVtask_month, TVtask_day;
        LinearLayout MainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TVtask_id = itemView.findViewById(R.id.TaskID);
            TVtask_title = itemView.findViewById(R.id.TaskTitle);
            TVtask_desc = itemView.findViewById(R.id.TaskDesc);
            TVtask_year = itemView.findViewById(R.id.DueYear);
            TVtask_month = itemView.findViewById(R.id.DueMonth);
            TVtask_day = itemView.findViewById(R.id.DueDay);
            MainLayout = itemView.findViewById(R.id.MainLayout);
        }
    }
}
