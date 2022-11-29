package com.example.and_vidal.entities;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_vidal.R;

import java.util.List;

public class Workout {
    private String name;
    private String description;
    private int id;

    public Workout(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public Workout() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
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
        public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.workoutName.setText(workouts.get(position).getName());
            holder.workoutDescription.setText(workouts.get(position).getDescription());
            System.out.println(workouts.get(position).getName());
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
