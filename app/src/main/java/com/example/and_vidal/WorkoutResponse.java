package com.example.and_vidal;

import android.util.Log;

public class WorkoutResponse {
    private String name;
    private String description;
    private int id;

    public WorkoutResponse(String name, String description) {
        Log.i("WorkoutResponse", "WorkoutResponse: " + name + " " + description);
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public Workout getWorkout() {
        return new Workout(name, description, id);
    }

}
