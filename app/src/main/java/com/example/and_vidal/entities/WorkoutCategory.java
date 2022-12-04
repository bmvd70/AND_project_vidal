package com.example.and_vidal.entities;

public enum WorkoutCategory {
    WORKOUT(0),
    ARMS(8),
    LEGS(9),
    ABS(10),
    CHEST(11),
    BACK(12),
    SHOULDERS(13),
    CALVES(14),
    CARDIO(15);

    private final int id;
    private final String name;

    WorkoutCategory(int id) {
        this.id = id;
        this.name = name();
    }

    public int getId() {
        return id;
    }

    public static String getName(int category) {
        for (WorkoutCategory workoutCategory : WorkoutCategory.values()) {
            if (workoutCategory.getId() == category) {
                return workoutCategory.name;
            }
        }
        return null;
    }
}
