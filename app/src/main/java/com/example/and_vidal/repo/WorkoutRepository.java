package com.example.and_vidal.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_vidal.api.ServiceGenerator;
import com.example.and_vidal.entities.Workout;
import com.example.and_vidal.entities.WorkoutResponse;
import com.example.and_vidal.entities.WorkoutsListResponse;
import com.example.and_vidal.DAO.IWorkoutsDAO;
import com.example.and_vidal.DAO.WorkoutsDAO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class WorkoutRepository {
    private static WorkoutRepository instance;
    private final IWorkoutsDAO workoutsDAO;

    private WorkoutRepository() {
        workoutsDAO = WorkoutsDAO.getInstance();
    }

    public static synchronized WorkoutRepository getInstance() {
        if (instance == null) {
            instance = new WorkoutRepository();
        }
        return instance;
    }

    public LiveData<Workout> getWorkout(int id) {
        return workoutsDAO.getWorkout(id);
    }

    public void requestWorkoutList() {
        ServiceGenerator.getWorkoutApi().getWorkouts().enqueue(new Callback<WorkoutsListResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<WorkoutsListResponse> call, Response<WorkoutsListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("WorkoutRepository_requestWorkoutList",
                            "onSuccess: " + response.body().getResults());
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
            Workout workout = item.getWorkout();
            // Modifying the description, here a replace on each is more
            // effective in the small amount of data that we have,
            // Aho-Corasick Algorithm would be better for a larger amount of data
            String local = workout.getDescription()
                    .replace("<p>.</p>","")
                    .replace("<p style=\"\">","")
                    .replace("<p>","")
                    .replace("</p>","")
                    .replace("<ul>","")
                    .replace("</ul>","")
                    .replace("<ol>","")
                    .replace("</ol>","")
                    .replace("<li>","")
                    .replace("</li>","\n")
                    .replace("<em>","")
                    .replace("</em>","")
                    .replace("-","")
                    .trim();
            // Only load data that is complete since some of the api data is not complete
            // Category 8 to 15 are the only valid ones in the API
            if ((workout.getId() == 0 || workout.getName().isEmpty()
                    || local.isEmpty() || local.length() < 5 )
                    && workout.getCategory() >= 8 && workout.getCategory() <= 15) {
                continue;
            }
            workoutsDAO.addWorkout(new WorkoutResponse(workout.getName(), local, workout.getId(), workout.getCategory()));
        }
    }
}
