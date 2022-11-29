package com.example.and_vidal.entities;

import android.util.Log;

import com.example.and_vidal.entities.Workout;

public class WorkoutResponse {
    private final String name;
    private final String description;
    private final int id;
    private static final String TAG = "WorkoutResponse";


    public WorkoutResponse(String name, String description, int id) {
        Log.d(TAG, "WorkoutResponse: " + name + " " + description);
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public Workout getWorkout() {
        return new Workout(name, description, id);
    }
}
