package com.example.and_vidal.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.and_vidal.R;
import com.example.and_vidal.databinding.FragmentSigninBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInFragment extends Fragment {

    private FragmentSigninBinding binding;

    private static final String TAG = "ProfileFragment";
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSigninBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button btnSignin = (Button) binding.btnSignin;
        ImageButton btnBack = (ImageButton) binding.btnSiBack;
        TextView gotoSignup = (TextView) binding.siGotoSignup;

        gotoSignup.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_navigation_signin_to_navigation_signup);
        });

        btnSignin.setOnClickListener(v -> {
            TextView edPassword = binding.siPassword;
            TextView edEmail = binding.siEmail;
            String email = edEmail.getText().toString().trim();
            String password = edPassword.getText().toString().trim();

            if (password.isEmpty()) {
                edPassword.setError("Password is required!");
                edPassword.requestFocus();
                return;
            }
            if (edPassword.length() < 6) {
                edPassword.setError("Password must be at least 6 characters long.");
                edPassword.requestFocus();
                return;
            }
            if (email.isEmpty()) {
                edEmail.setError("Email is required!");
                edEmail.requestFocus();
                return;
            }
            signIn();
        });

        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void signIn() {
        mAuth.signInWithEmailAndPassword(binding.siEmail.getText().toString(),
                        binding.siPassword.getText().toString())
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmailPw:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                        assert user != null;
                        Toast.makeText(getActivity(), user.getEmail() + " was signed in", Toast.LENGTH_SHORT).show();
                        requireActivity().onBackPressed();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmailPw:failure", task.getException());
                        Toast.makeText(getActivity(), "Sign In failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    boolean checkLogin() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser != null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkLogin())
            requireActivity().onBackPressed();
    }

    private void updateUI(FirebaseUser user) {
        Log.d(TAG, "updateUI:" + user);
    }
}