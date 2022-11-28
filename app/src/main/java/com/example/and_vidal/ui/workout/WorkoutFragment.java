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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_vidal.R;
import com.example.and_vidal.Workout;
import com.example.and_vidal.databinding.FragmentWorkoutBinding;
import com.example.and_vidal.ui.profileface.ProfileFaceFragment;
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
        workoutViewModel.searchWorkoutList();
        workoutViewModel.requestWorkoutList().observeForever(workouts -> {
            workoutAdapter = new Workout.WorkoutAdapter(workouts);
            recyclerView.setAdapter(workoutAdapter);
        });


        FloatingActionButton fabHome = binding.fabHome;

        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "FAB_home clicked", Toast.LENGTH_SHORT).show();
                searchWorkout(root);
                /*FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.profileContainer, new ProfileFaceFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();*/
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