package edu.psu.swen888.firebasepractice;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mFireBaseAuth;
    Button signInButton;
    Button signUpButton;
    EditText email;
    EditText password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mFireBaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextNumberPassword);
        signInButton = findViewById(R.id.signInbutton);
        signUpButton = findViewById(R.id.signUpbutton);
        signInButton.setOnClickListener(view -> {
            SignIn(email.getText().toString(), password.getText().toString());
        });
        signUpButton.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    private void SignIn(String email, String password) {
        mFireBaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, AllTeams.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication Failed. Please check your password or create an account.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

