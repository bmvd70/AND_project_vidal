package com.example.and_vidal.ui.workout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.and_vidal.entities.Workout;
import com.example.and_vidal.WorkoutRepository;

import java.util.List;

public class WorkoutViewModel extends ViewModel {

    WorkoutRepository repository;

    public WorkoutViewModel() {
        repository = WorkoutRepository.getInstance();
    }

    public LiveData<Workout> getWorkout(int id) {
        return repository.getWorkout(id);
    }

    public void requestWorkoutList() {
        repository.requestWorkoutList();
    }

    public MutableLiveData<List<Workout>> getWorkoutList() {
        return repository.getAllWorkouts();
    }


}