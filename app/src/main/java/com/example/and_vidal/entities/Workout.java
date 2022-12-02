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
    private int category;

    public Workout(String name, String description, int id, int category) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.category = category;
    }

    public Workout() {
        this.name = "";
        this.description = "";
        this.id = 0;
        this.category = 0;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", category=" + category +
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
            caseCategory(holder, workouts.get(position).getCategory());
            Log.d(TAG, workouts.get(position).getName());
            holder.displaySingleWorkout(position);
        }

        private void caseCategory(ViewHolder holder, int category) {
            switch (category) {
                case 8:
                    holder.workoutImage.setImageResource(R.drawable.arms);
                    break;
                case 9:
                    holder.workoutImage.setImageResource(R.drawable.legs);
                    break;
                case 10:
                    holder.workoutImage.setImageResource(R.drawable.abs);
                    break;
                case 11:
                    holder.workoutImage.setImageResource(R.drawable.chest);
                    break;
                case 12:
                    holder.workoutImage.setImageResource(R.drawable.back);
                    break;
                case 13:
                    holder.workoutImage.setImageResource(R.drawable.shoulders);
                    break;
                case 14:
                    holder.workoutImage.setImageResource(R.drawable.calves);
                    break;
                case 15:
                    holder.workoutImage.setImageResource(R.drawable.cardio);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return workouts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView workoutName, workoutDescription, workoutId, workoutCategory;
            ImageView workoutImage;
            ConstraintLayout workoutLayout;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                workoutLayout = itemView.findViewById(R.id.workout_list_element);
                workoutName = itemView.findViewById(R.id.workout_list_element_name);
                workoutDescription = itemView.findViewById(R.id.workout_list_element_description);
                workoutImage = itemView.findViewById(R.id.workout_list_element_image);
            }

            public void displaySingleWorkout(int position) {
                this.workoutLayout.setOnClickListener(v -> {
                    int workoutId = workouts.get(position).getId();
                    Bundle bundle = new Bundle();
                    bundle.putInt("workoutId", workoutId);
                    bundle.putString("workoutName", workouts.get(position).getName());
                    bundle.putString("workoutDescription", workouts.get(position).getDescription());
                    bundle.putInt("workoutCategory", workouts.get(position).getCategory());

                    Log.d(TAG, "Clicked on " + workoutId);
                    Navigation.findNavController(v)
                            .navigate(R.id.action_navigation_workout_to_navigation_single_workout,
                                    bundle);
                });
            }
        }


    }
}
