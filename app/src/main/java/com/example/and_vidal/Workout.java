package com.example.and_vidal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Workout {
    private String name;
    private String description;

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

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
            holder.workoutName.setText(workouts.get(position).getName());
            holder.workoutDescription.setText(workouts.get(position).getDescription());
        }

        @Override
        public int getItemCount() {
            return workouts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView workoutName;
            TextView workoutDescription;
            ImageView workoutImage;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                workoutName = itemView.findViewById(R.id.workout_list_element_name);
                workoutDescription = itemView.findViewById(R.id.workout_list_element_description);
            }
        }
    }
}
