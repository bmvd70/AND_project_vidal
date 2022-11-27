package com.example.and_vidal.ui.workout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_vidal.R;
import com.example.and_vidal.Workout;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    List<Workout> workouts;

    public WorkoutAdapter(List<Workout> workouts) {
        this.workouts = workouts;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_workout_list_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.workoutTitle.setText(workouts.get(position).getTitle());
        holder.workoutDescription.setText(workouts.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView workoutTitle;
        TextView workoutDescription;
        ImageView workoutImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutTitle = itemView.findViewById(R.id.workout_list_element_title);
            workoutDescription = itemView.findViewById(R.id.workout_list_element_description);
        }
    }
}

