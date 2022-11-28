package com.example.and_vidal;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class WorkoutRepository {
    private static WorkoutRepository instance;
    private final MutableLiveData<Workout> searchedWorkout;

    private WorkoutRepository() {
        searchedWorkout = new MutableLiveData<>();
    }

    public static synchronized WorkoutRepository getInstance() {
        if (instance == null) {
            instance = new WorkoutRepository();
        }
        return instance;
    }

    public LiveData<Workout> getSearchedWorkout() {
        return searchedWorkout;
    }

    public void searchWorkoutById(int id) {
        ServiceGenerator.getWorkoutApi().getWorkout(id).enqueue(new Callback<WorkoutResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<WorkoutResponse> call, Response<WorkoutResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    searchedWorkout.setValue(response.body().getWorkout());
                    Log.i("WorkoutRepository", "onSuccess: " + response.body().getWorkout());
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<WorkoutResponse> call, Throwable t) {
                Log.e("WorkoutRepository", "onFailure: ", t);
            }
        });
    }

}
