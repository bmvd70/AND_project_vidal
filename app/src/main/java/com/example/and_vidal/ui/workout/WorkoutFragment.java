package com.example.and_vidal.ui.workout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_vidal.Workout;
import com.example.and_vidal.databinding.FragmentWorkoutBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WorkoutFragment extends Fragment {

    private FragmentWorkoutBinding binding;
    RecyclerView recyclerView;
    Workout.WorkoutAdapter workoutAdapter;
    //testing
    private EditText editText;
    private WorkoutViewModel workoutViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //WorkoutViewModel workoutViewModel =
              //  new ViewModelProvider(this).get(WorkoutViewModel.class);
        binding = FragmentWorkoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recyclerView = binding.rv;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        // testing
        editText = binding.editText;
        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        workoutViewModel.getSearchedWorkout().observe(getViewLifecycleOwner(), workout -> {
            //editText.setText(workout.getName());
            Toast.makeText(getContext(), workout.getName(), Toast.LENGTH_SHORT).show();
            Log.i("WorkoutFragment", "onCreateView: " + workout.getName());
        });

        List<Workout> workouts = new ArrayList<>();//Workout.getWorkouts();
        workouts.add(new Workout("Workout 1", "Description 1"));
        workouts.add(new Workout("Workout 2", "Description 2"));
        workouts.add(new Workout("Workout 3", "Description 3"));
        workouts.add(new Workout("Workout 4", "Description 4"));
        workouts.add(new Workout("Workout 5", "Description 5"));
        workouts.add(new Workout("Workout 6", "Description 6"));
        workouts.add(new Workout("Workout 7", "Description 7"));
        workouts.add(new Workout("Workout 8", "Description 8"));
        workouts.add(new Workout("Workout 9", "Description 9"));
        workouts.add(new Workout("Workout 10", "Description 10"));
        workouts.add(new Workout("Workout 11", "Description 11"));
        workouts.add(new Workout("Workout 12", "Description 12"));
        workouts.add(new Workout("Workout 13", "Description 13"));
        workouts.add(new Workout("Workout 14", "Description 14"));
        workouts.add(new Workout("Workout 15", "Description 15"));
        workouts.add(new Workout("Workout 16", "Description 16"));
        workouts.add(new Workout("Workout 17", "Description 17"));
        workouts.add(new Workout("Workout 18", "Description 18"));
        workouts.add(new Workout("Workout 19", "Description 19"));
        workouts.add(new Workout("Workout 20", "Description 20"));
        workouts.add(new Workout("Workout 21", "Description 21"));
        workouts.add(new Workout("Workout 22", "Description 22"));
        workouts.add(new Workout("Workout 23", "Description 23"));
        workouts.add(new Workout("Workout 24", "Description 24"));
        workouts.add(new Workout("Workout 25", "Description 25"));
        workouts.add(new Workout("Workout 26", "Description 26"));
        workouts.add(new Workout("Workout 27", "Description 27"));
        workouts.add(new Workout("Workout 28", "Description 28"));
        workouts.add(new Workout("Workout 29", "Description 29"));
        workouts.add(new Workout("Workout 30", "Description 30"));


        workoutAdapter = new Workout.WorkoutAdapter(workouts);
        recyclerView.setAdapter(workoutAdapter);



        FloatingActionButton fabHome = binding.fabHome;

        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "FAB_home clicked", Toast.LENGTH_SHORT).show();
                searchWorkout(root);
            }
        });

        //final TextView textView = binding.textWorkout;
        //workoutViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void searchWorkout(View view) {
        Log.i("WorkoutFragment", "searchWorkout: " + editText.getText().toString() + workoutViewModel.toString());
        int val = Integer.parseInt(editText.getText().toString());
        if (val != 0) {
            Log.i("WorkoutFragment", "searchWorkoutNotNull: " + val);
            workoutViewModel.searchWorkout(val);
        }
        Log.i("WorkoutFragment", "searchWorkoutIsNull: " + val);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}