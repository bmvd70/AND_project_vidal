package com.example.and_vidal.ui.tabata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.and_vidal.databinding.FragmentTabataBinding;

public class TabataFragment extends Fragment {

    private FragmentTabataBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TabataViewModel dashboardViewModel =
                new ViewModelProvider(this).get(TabataViewModel.class);

        binding = FragmentTabataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTabata;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}