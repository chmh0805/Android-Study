package com.example.fireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class JoinActivity extends AppCompatActivity {
    private static final String TAG = "JoinActivity";
    private TextInputEditText teEmail, tePassword;
    private MaterialButton btnJoin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mAuth = FirebaseAuth.getInstance();
        teEmail = findViewById(R.id.te_email);
        tePassword = findViewById(R.id.te_password);
        btnJoin = findViewById(R.id.btn_join);

        btnJoin.setOnClickListener(v -> {
            String email = teEmail.getText().toString().trim();
            String password = tePassword.getText().toString().trim();

            if (!(email.equals("")) && !(password.equals(""))) {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "onCreate: user: " + user.getEmail());
                            Intent intent = new Intent(this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Log.d(TAG, "onCreate: " + task.getException());
                            Snackbar.make(btnJoin.getRootView(), "회원가입 실패", BaseTransientBottomBar.LENGTH_LONG).show();
                        }
                    });
            } else {
                Snackbar.make(btnJoin.getRootView(), "회원가입 실패", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }
}