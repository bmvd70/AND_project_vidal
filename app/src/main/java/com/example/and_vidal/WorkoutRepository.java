package com.example.and_vidal;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_vidal.ui.DAO.IWorkoutsDAO;
import com.example.and_vidal.ui.DAO.WorkoutsDAO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class WorkoutRepository {
    private static WorkoutRepository instance;
    private final MutableLiveData<Workout> searchedWorkout;
    private IWorkoutsDAO workoutsDAO;

    private WorkoutRepository() {
        searchedWorkout = new MutableLiveData<>();
        workoutsDAO = WorkoutsDAO.getInstance();
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

    public void requestWorkoutList() {
        ServiceGenerator.getWorkoutApi().getWorkouts().enqueue(new Callback<WorkoutsListResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<WorkoutsListResponse> call, Response<WorkoutsListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //searchedWorkout.setValue(response.body().getResults());
                    Log.i("WorkoutRepository_requestWorkoutList", "onSuccess: " + response.body().getResults());
                    saveToDb(response.body().getResults());
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<WorkoutsListResponse> call, Throwable t) {
                Log.e("WorkoutRepository_requestWorkoutList", "onFailure: ", t);
            }
        });
    }

    public MutableLiveData<List<Workout>> getAllWorkouts() {
        return workoutsDAO.getAllWorkouts();
    }

    private void saveToDb(ArrayList<WorkoutResponse> workouts) {
        for(WorkoutResponse item : workouts) {
            workoutsDAO.addWorkout(item);
        }
    }

}
