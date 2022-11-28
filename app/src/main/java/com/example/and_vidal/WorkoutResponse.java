package com.example.and_vidal;

import android.util.Log;

public class WorkoutResponse {
    private String name;
    private String description;

    public WorkoutResponse(String name, String description) {
        Log.i("WorkoutResponse", "WorkoutResponse: " + name + " " + description);
        this.name = name;
        this.description = description;
    }

    public Workout getWorkout() {
        Log.i("WorkoutResponse", "getWorkout: " + name + " " + description);
        return new Workout(name, description);
    }

}
