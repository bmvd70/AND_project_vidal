package com.example.and_vidal.ui.signin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
import com.example.and_vidal.ui.profile.ProfileFragment;
import com.example.and_vidal.ui.profileface.ProfileFaceFragment;
import com.example.and_vidal.ui.signup.SignUpFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentSigninBinding binding;

    // TODO: Rename and change types of parameters
    private static final String TAG = "ProfileFragment";
    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;
    private String mCustomToken;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
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
        binding = FragmentSigninBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button btnSignin = (Button) binding.btnSignin;
        ImageButton btnBack = (ImageButton) binding.btnSiBack;
        TextView gotoSignup = (TextView) binding.siGotoSignup;
        /*btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn((String)binding.siUsername.getText().toString(), binding.siPassword.getText().toString());
            }
        });*/

        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.profileContainer, new SignUpFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        // Inflate the layout for this fragment
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
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmailPw:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(getActivity(), user.getEmail() + " was signed in", Toast.LENGTH_SHORT).show();
                            goBack();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmailPw:failure", task.getException());
                            Toast.makeText(getActivity(), "Sign In failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    boolean checkLogin() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(getActivity(), currentUser.getEmail() + " is signedin", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(getActivity(), "No user", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    void goBack() {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.profileContainer, new ProfileFaceFragment())
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: in Sign in");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: in Sign in");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: in Sign in");
    }



    private void updateUI(FirebaseUser user) {
        Log.d(TAG, "updateUI:" + user);
    }
}