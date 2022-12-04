package com.example.and_vidal.DAO;

import androidx.lifecycle.MutableLiveData;

import com.example.and_vidal.entities.Workout;
import com.example.and_vidal.entities.WorkoutResponse;

import java.util.List;

public interface IWorkoutsDAO {
    void addWorkout(WorkoutResponse workout);
    void deleteWorkout(WorkoutResponse workout);
    void updateWorkout(WorkoutResponse workout);
    MutableLiveData<Workout> getWorkout(int id);
    MutableLiveData<List<Workout>> getAllWorkouts();

    MutableLiveData<List<Workout>> updateWorkoutList(int type);
}
