package com.example.and_vidal.api;

import com.example.and_vidal.entities.WorkoutResponse;
import com.example.and_vidal.entities.WorkoutsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface WorkoutApi {
    @GET("api/v2/exercise/{id}")
    Call<WorkoutResponse> getWorkout(@Path("id") int id);

    @GET("api/v2/exercise/?language=2&limit=50")
    Call<WorkoutsListResponse> getWorkouts();

}

