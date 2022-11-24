package com.example.and_vidal.ui.signup;

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

import com.example.and_vidal.R;
import com.example.and_vidal.databinding.FragmentSignupBinding;
import com.example.and_vidal.ui.profileface.ProfileFaceFragment;
import com.example.and_vidal.ui.signin.SignInFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentSignupBinding binding;

    // TODO: Rename and change types of parameters
    private static final String TAG = "ProfileFragment";
    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button btnSignup = (Button) binding.btnSignup;
        ImageButton btnBack = (ImageButton) binding.btnSuBack;

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
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
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void signUp() {
        mAuth.createUserWithEmailAndPassword(binding.suEmail.getText().toString(), binding.suPassword.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmailPw:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            assert user != null;
                            Toast.makeText(getActivity(), user.getEmail() + " was signed up!", Toast.LENGTH_SHORT).show();
                            goBack();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmailPw:failure", task.getException());
                            Toast.makeText(getActivity(), "Sign up failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    void goBack() {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.profileContainer, new SignInFragment())
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: in Sign up");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: in Sign up");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: in Sign up");
    }

    private void updateUI(FirebaseUser user) {
        Log.d(TAG, "updateUI:" + user);
    }
}