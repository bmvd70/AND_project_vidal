package com.example.and_vidal.ui.workout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_vidal.ILoginHandler;
import com.example.and_vidal.LoginHandler;
import com.example.and_vidal.R;
import com.example.and_vidal.entities.Workout;
import com.example.and_vidal.databinding.FragmentWorkoutBinding;
import com.example.and_vidal.entities.WorkoutCategory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WorkoutFragment extends Fragment {
    private static final String TAG = "WorkoutFragment";

    private FragmentWorkoutBinding binding;
    private RecyclerView recyclerView;
    private Workout.WorkoutAdapter workoutAdapter;
    private ILoginHandler loginHandler;
    private WorkoutViewModel workoutViewModel;

    private FloatingActionButton fabHome;
    private TextView tvWorkoutType;

    private int type = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loginHandler = LoginHandler.getInstance();

        fabHome = binding.fabHome;
        tvWorkoutType = binding.singleWorkoutCategory;


        // if no one is logged in, do not display anything
        if (loginHandler.getCurrentUser() == null) {
            Toast.makeText(getContext(), "Please login to use this feature", Toast.LENGTH_SHORT).show();
            fabHome.setVisibility(View.GONE);
        } else {
            fabHome.setVisibility(View.VISIBLE);
            //fabHome.setEnabled(false);
            recyclerView = binding.rv;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.hasFixedSize();

            workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
            // Call API and feed missing entries into DB
            workoutViewModel.requestWorkoutList();
            // Get from DB
            workoutViewModel.updateWorkoutList(0).observeForever(workouts -> {
                workoutAdapter = new Workout.WorkoutAdapter(workouts);
                recyclerView.setAdapter(workoutAdapter);
            });
        }

        fabHome.setOnClickListener(v -> {
            // loop types
            if (type == 0)
                type = 8;
            else if (type >= 8 && type < 15)
                type++;
            else if (type == 15)
                type = 0;
            setFabIcon(type);
            tvWorkoutType.setText(WorkoutCategory.getName(type));
            updateWorkoutList(type);
        });

        return root;
    }

    private void setFabIcon(int type) {
        int icon;
        switch (type) {
            case 8:
                icon = R.drawable.arms;
                break;
            case 9:
                icon = R.drawable.legs;
                break;
            case 10:
                icon = R.drawable.abs;
                break;
            case 11:
                icon = R.drawable.chest;
                break;
            case 12:
                icon = R.drawable.back;
                break;
            case 13:
                icon = R.drawable.shoulders;
                break;
            case 14:
                icon = R.drawable.calves;
                break;
            case 15:
                icon = R.drawable.cardio;
                break;
            default:
                icon = R.drawable.ic_baseline_search_24;
                break;
        }
        fabHome.setImageResource(icon);
    }

    private void updateWorkoutList(int type) {
        workoutViewModel.updateWorkoutList(type);
    }


    public void searchWorkout() {
        int val = 0;
        if (val != 0) {
            Log.d(TAG, "searchWorkoutNotZero: " + val);
            workoutViewModel.getWorkout(val).observeForever(workout ->
                    Log.d(TAG, "getWorkoutObservedOnChanged: " + workout.getId()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}