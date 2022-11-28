package com.example.and_vidal.ui.DAO;

import androidx.lifecycle.MutableLiveData;

import com.example.and_vidal.Workout;
import com.example.and_vidal.WorkoutResponse;

import java.util.List;

public interface IWorkoutsDAO {
    void addWorkout(WorkoutResponse workout);
    void deleteWorkout(WorkoutResponse workout);
    void updateWorkout(WorkoutResponse workout);
    Workout getWorkout(int id);
    MutableLiveData<List<Workout>> getAllWorkouts();

}