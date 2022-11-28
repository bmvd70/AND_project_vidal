package com.example.and_vidal.ui.DAO;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.and_vidal.Workout;
import com.example.and_vidal.WorkoutResponse;
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
    private MutableLiveData<List<Workout>> workoutsMutable;

    private FirebaseFirestore db;

    private WorkoutsDAO() {
        db = FirebaseFirestore.getInstance();
        workoutsMutable = new MutableLiveData<>();
    }

    public static WorkoutsDAO getInstance() {
        if (instance == null) {
            instance = new WorkoutsDAO();
        }
        return instance;
    }

    @Override
    public void addWorkout(WorkoutResponse workout) {
        db.collection("workouts").document(workout.getWorkout().getName()).set(workout.getWorkout()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("WorkoutsDAO", "Workout added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("WorkoutsDAO", "Workout not added");
            }
        });
    }



    @Override
    public void deleteWorkout(WorkoutResponse workout) {
        //TODO
    }

    @Override
    public void updateWorkout(WorkoutResponse workout) {
        //TODO
    }

    @Override
    public Workout getWorkout(int id) {
        return null;
    }

    @Override
    public MutableLiveData<List<Workout>> getAllWorkouts() {

        db.collection("workouts").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Workout> workouts = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                Workout workout = document.toObject(Workout.class);

                                String local = workout.getDescription();
                                local = local.replace("<p>.</p>","");
                                local = local.replace("<p>","");
                                local = local.replace("</p>","");
                                local = local.replace("<ul>","");



                                workout.setDescription(local);
                                if (workout.getId() == 0 || workout.getName() == null || local.trim().isEmpty()) {
                                    continue;
                                }
                                workouts.add(workout);
                                Log.i("WorkoutsDAO", workout.toString(), task.getException());
                            }
                            workoutsMutable.postValue(workouts);
                        }else {
                            Log.d("WorkoutsDAO", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return workoutsMutable;
    }
}
