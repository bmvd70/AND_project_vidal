package com.example.and_vidal.entities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
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
        this.name = "";
        this.description = "";
        this.id = 0;
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

    @NonNull
    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }

    public static class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {
        private final List<Workout> workouts;
        private final static String TAG = "WorkoutAdapter";

        public WorkoutAdapter(List<Workout> workouts) {
            this.workouts = workouts;
        }

        @NonNull
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
            Log.d(TAG, workouts.get(position).getName());
            holder.displaySingleWorkout(position);
        }

        @Override
        public int getItemCount() {
            return workouts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView workoutName, workoutDescription, workoutId;
            ImageView workoutImage;
            ConstraintLayout workoutLayout;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                workoutLayout = itemView.findViewById(R.id.workout_list_element);
                workoutName = itemView.findViewById(R.id.workout_list_element_name);
                workoutDescription = itemView.findViewById(R.id.workout_list_element_description);
            }

            public void displaySingleWorkout(int position) {
                this.workoutLayout.setOnClickListener(v -> {
                    int workoutId = workouts.get(position).getId();
                    Bundle bundle = new Bundle();
                    bundle.putInt("workoutId", workoutId);
                    Log.d(TAG, "Clicked on " + workoutId);
                    Navigation.findNavController(v)
                            .navigate(R.id.action_navigation_workout_to_navigation_single_workout,
                                    bundle);
                });
            }
        }


    }
}
