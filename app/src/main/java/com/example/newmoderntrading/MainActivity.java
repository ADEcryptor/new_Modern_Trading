package com.example.newmoderntrading;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView helloUser = (TextView)findViewById(R.id.subtitle);
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DocumentReference docRef = db.collection("users").document(currentUser.getEmail().trim());
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String helloUserValue = "Hello " + documentSnapshot.getString("nickname");
                helloUser.setText(helloUserValue);
            }
        });
        Button chatsPageButton = findViewById(R.id.chatsPageButton);
        Button currentTradesButton = findViewById(R.id.currentTradesButton);
        Button uploadItemButton = findViewById(R.id.uploadItemButton);
        Button signOutButton = findViewById(R.id.signOutButton);
        chatsPageButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ChatsActivity.class);
            startActivity(intent);
            finish();
        });
        currentTradesButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CurrentTradesActivity.class);
            startActivity(intent);
            finish();
        });
        uploadItemButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UploadItemActivity.class);
            startActivity(intent);
            finish();
        });
        signOutButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            finish();
        });
    }
}