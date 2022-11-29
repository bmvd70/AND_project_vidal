package com.example.and_vidal.DAO;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.and_vidal.entities.Workout;
import com.example.and_vidal.entities.WorkoutResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

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
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Workout added: " + workout.getWorkout().getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Workout not added: " + workout.getWorkout().getId(), e);
            }
        });
    }

    @Override
    public void deleteWorkout(WorkoutResponse workout) {
        db.collection("workouts").document(String.valueOf(workout.getWorkout().getId()))
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Workout deleted");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Workout not deleted");
            }
        });
    }

    @Override
    public void updateWorkout(WorkoutResponse workout) {
        db.collection("workouts").document(String.valueOf(workout.getWorkout().getId()))
                .set(workout.getWorkout())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Workout updated");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Workout not updated");
            }
        });
    }

    @Override
    public MutableLiveData<Workout> getWorkout(int id) {
        db.collection("workouts").document(String.valueOf(id))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Workout workout = (task.getResult()).toObject(Workout.class);
                            workoutMutable.setValue(workout);
                            if (workout.getId() == id) {
                                Log.d(TAG, "getWorkout retrieved workout with id: " + workout.getId());
                            } else {
                                Log.d(TAG, "getWorkout retrieved workout with id: " + workout.getId() + " but was looking for id: " + id);
                            }
                        } else {
                            Log.w(TAG, "get failed with ", task.getException());
                        }
                    }
                });
        return workoutMutable;
    }

    @Override
    public MutableLiveData<List<Workout>> getAllWorkouts() {

        db.collection("workouts").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Workout> workouts = new ArrayList<>();
                            int truncateLength = 25;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Workout workout = document.toObject(Workout.class);
                                String local = workout.getDescription();
                                local = (local.length() > truncateLength) ? local.substring(0, truncateLength) : local;
                                workout.setDescription(local);
                                workouts.add(workout);
                            }
                            workoutsMutable.postValue(workouts);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return workoutsMutable;
    }
}
