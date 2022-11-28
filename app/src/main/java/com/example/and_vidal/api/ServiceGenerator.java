package com.example.and_vidal.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static WorkoutApi workoutApi;

    public static WorkoutApi getWorkoutApi() {
        if (workoutApi == null) {
            workoutApi = new Retrofit.Builder()
                    .baseUrl("https://wger.de") // "https://pokeapi.co"
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(WorkoutApi.class);
        }
        return workoutApi;
    }
}
