package com.example.and_vidal.ui.workout;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.and_vidal.Workout;
import com.example.and_vidal.WorkoutRepository;

public class WorkoutViewModel extends ViewModel {

    //private final MutableLiveData<String> mText;

    WorkoutRepository repository;

    public WorkoutViewModel() {
        repository = WorkoutRepository.getInstance();
    }

    public LiveData<Workout> getSearchedWorkout() {
        Log.i("WorkoutViewModel", "getSearchedWorkout: " + repository.getSearchedWorkout().getValue());
        return repository.getSearchedWorkout();
    }

    public void searchWorkout(int id) {
        Log.i("WorkoutViewModel", "searchWorkout: " + id);
        repository.searchWorkoutById(id);
    }
}