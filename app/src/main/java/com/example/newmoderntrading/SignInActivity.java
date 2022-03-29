package com.example.newmoderntrading;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        Button signInButton = findViewById(R.id.signInButton);
        TextView forgotYourPasswordButton = findViewById(R.id.forgotYourPasswordButton);
        TextView dontHaveAnAccountYetButton = findViewById(R.id.dontHaveAnAccountYetButton);
        signInButton.setOnClickListener(view -> signIn());
        forgotYourPasswordButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, ChangePasswordOneActivity.class);
            startActivity(intent);
            finish();
        });
        dontHaveAnAccountYetButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, com.example.newmoderntrading.MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signIn() {
        EditText etEmail = findViewById(R.id.emailInput);
        EditText etPassword = findViewById(R.id.passwordInput);

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.length() > 0 && password.length() > 5) { // TODO: make better requirements here
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(SignInActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(this, task -> Toast.makeText(SignInActivity.this, "Account doesn't exists", Toast.LENGTH_SHORT).show());
            } else {
                Toast.makeText(SignInActivity.this,"Invalid email address", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(SignInActivity.this,"Wrong email address or password", Toast.LENGTH_SHORT).show();
        }
    }

}