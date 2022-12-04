package com.example.and_vidal.DAO;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import com.example.and_vidal.entities.Workout;
import com.example.and_vidal.entities.WorkoutResponse;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkoutsDAO implements IWorkoutsDAO {
    private static WorkoutsDAO instance;
    private final MutableLiveData<List<Workout>> workoutsMutable;
    private final MutableLiveData<Workout> workoutMutable;
    private final FirebaseFirestore db;
    private static final String TAG = "WorkoutsDAO";

    private WorkoutsDAO() {
        db = FirebaseFirestore.getInstance();
        workoutsMutable = new MutableLiveData<>();
        workoutMutable = new MutableLiveData<>();
    }

    public static WorkoutsDAO getInstance() {
        if (instance == null) {
            instance = new WorkoutsDAO();
        }
        return instance;
    }

    @Override
    public void addWorkout(WorkoutResponse workout) {
        db.collection("workouts").document(String.valueOf(workout.getWorkout().getId()))
                .set(workout.getWorkout())
                .addOnSuccessListener(unused -> Log.d(TAG,
                        "Workout added: " + workout.getWorkout().getId()))
                .addOnFailureListener(e -> Log.w(TAG,
                        "Workout not added: " + workout.getWorkout().getId(), e));
    }

    @Override
    public void deleteWorkout(WorkoutResponse workout) {
        db.collection("workouts").document(String.valueOf(workout.getWorkout().getId()))
                .delete()
                .addOnSuccessListener(unused -> Log.d(TAG, "Workout deleted"))
                .addOnFailureListener(e -> Log.w(TAG, "Workout not deleted"));
    }

    @Override
    public void updateWorkout(WorkoutResponse workout) {
        db.collection("workouts").document(String.valueOf(workout.getWorkout().getId()))
                .set(workout.getWorkout())
                .addOnSuccessListener(unused -> Log.d(TAG, "Workout updated: " + workout.getWorkout().getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Workout not updated"));
    }

    @Override
    public MutableLiveData<Workout> getWorkout(int id) {
        db.collection("workouts").document(String.valueOf(id))
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Workout workout = (task.getResult()).toObject(Workout.class);
                        workoutMutable.setValue(workout);
                        assert workout != null;
                        if (workout.getId() == id) {
                            Log.d(TAG, "getWorkout retrieved workout with id: " + workout.getId());
                        } else {
                            Log.d(TAG, "getWorkout retrieved workout with id: " + workout.getId() + " but was looking for id: " + id);
                        }
                    } else {
                        Log.w(TAG, "get failed with ", task.getException());
                    }
                });
        return workoutMutable;
    }

    @Override
    public MutableLiveData<List<Workout>> getAllWorkouts() {

        db.collection("workouts").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<Workout> workouts = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Workout workout = document.toObject(Workout.class);
                            workouts.add(workout);
                        }
                        workoutsMutable.postValue(workouts);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
        return workoutsMutable;
    }

    @Override
    public MutableLiveData<List<Workout>> updateWorkoutList(int type) {
        if (type == 0)
            return getAllWorkouts();
        else {
            workoutsMutable.setValue(new ArrayList<>());
            db.collection("workouts").get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            ArrayList<Workout> workouts = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Workout workout = document.toObject(Workout.class);
                                if (workout.getCategory() == type)
                                    workouts.add(workout);
                            }
                            workoutsMutable.postValue(workouts);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    });
            return workoutsMutable;
        }
    }
}
