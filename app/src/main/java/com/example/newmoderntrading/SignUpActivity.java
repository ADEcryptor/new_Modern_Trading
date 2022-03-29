package com.example.newmoderntrading;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        Button signUpButton = findViewById(R.id.signUpButton);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        });
        signUpButton.setOnClickListener(view -> signUp());
    }

    public void signUp() {
        EditText etEmail = findViewById(R.id.emailInput);
        EditText etPassword = findViewById(R.id.passwordInput);
        EditText etPasswordVerification = findViewById(R.id.passwordVerifyInput);
        EditText etNickname = findViewById(R.id.nicknameInput);

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String passwordVerification = etPasswordVerification.getText().toString().trim();
        String nickname = etNickname.getText().toString().trim();

        if (nickname.length() < 2) {
            Toast.makeText(SignUpActivity.this,"Nickname must be at least 2 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (password.length() < 6) {
            Toast.makeText(SignUpActivity.this,"Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!password.equals(passwordVerification)) {
            Toast.makeText(SignUpActivity.this,"Password and password verification are not the same", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(email.length() < 1 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(SignUpActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Map<String, Object> user = new HashMap<>();
                user.put("nickname", nickname);
                db.collection("users").document(email).set(user);
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                Toast.makeText(SignUpActivity.this, "Signed up!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}