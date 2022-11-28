package com.example.and_vidal;

import java.util.ArrayList;

public class WorkoutsListResponse {
    ArrayList<WorkoutResponse> results;

    public WorkoutsListResponse(ArrayList<WorkoutResponse> results) {
        this.results = results;
    }

    public ArrayList<WorkoutResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<WorkoutResponse> results) {
        this.results = results;
    }
}
