package com.example.and_vidal.ui.tabata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.and_vidal.databinding.FragmentTabataBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TabataFragment extends Fragment {

    private FragmentTabataBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TabataViewModel tabataViewModel = new ViewModelProvider(this).get(TabataViewModel.class);
        binding = FragmentTabataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fabTabata = binding.fabTabata;
        fabTabata.setEnabled(false);
        fabTabata.setOnClickListener(v ->
                Toast.makeText(getActivity(), "FAB_tabata clicked", Toast.LENGTH_SHORT).show());

        final TextView textView = binding.textTabata;
        tabataViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}