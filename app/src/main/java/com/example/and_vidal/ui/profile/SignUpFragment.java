package com.example.and_vidal.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.and_vidal.ILoginHandler;
import com.example.and_vidal.LoginHandler;
import com.example.and_vidal.R;
import com.example.and_vidal.databinding.FragmentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignUpFragment extends Fragment {

    private FragmentSignupBinding binding;
    private static final String TAG = "ProfileFragment";
    ILoginHandler loginHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginHandler = LoginHandler.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button btnSignup = (Button) binding.btnSignup;
        ImageButton btnBack = (ImageButton) binding.btnSuBack;

        btnSignup.setOnClickListener(v -> {
            TextView edPassword = binding.suPassword;
            TextView edEmail = binding.suEmail;
            String email = edEmail.getText().toString().trim();
            String password = edPassword.getText().toString().trim();

            if (email.isEmpty()) {
                edEmail.setError("Email is required!");
                edEmail.requestFocus();
                return;
            }
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
            signUp();
        });

        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void signUp() {
        mAuth.createUserWithEmailAndPassword(binding.suEmail.getText().toString(), binding.suPassword.getText().toString())
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmailPw:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            Toast.makeText(getActivity(), user.getEmail() + " was signed up!", Toast.LENGTH_SHORT).show();
                            requireActivity().onBackPressed();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmailPw:failure", task.getException());
                            Toast.makeText(getActivity(), "Sign up failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}