package com.example.and_vidal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface WorkoutApi {
    @GET("api/v2/exerciseinfo/{id}") // "api/v2/pokemon/{name}"
    Call<WorkoutResponse> getWorkout(@Path("id") int id); // (@Path("name") String name);

    @GET("api/v2/exerciseinfo/") // "api/v2/pokemon/{name}"
    Call<WorkoutsListResponse> getWorkouts(); // (@Path("name") String name);

}

