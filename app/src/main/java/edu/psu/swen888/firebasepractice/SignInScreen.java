package edu.psu.swen888.firebasepractice;

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


public class SignInScreen extends AppCompatActivity {
    FirebaseAuth mFireBaseAuth;
    Button signInButton;
    Button signUpButton;
    EditText email;
    EditText password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_activity);

        //get instance of firebase authorization
        mFireBaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextNumberPassword);
        signInButton = findViewById(R.id.signInbutton);
        signUpButton = findViewById(R.id.signUpbutton);

        //call signIn authorization method when signIn button is clicked
        signInButton.setOnClickListener(view -> {
            SignIn(email.getText().toString(), password.getText().toString());
        });

        //pass intent to move to registration page when signUp button is clicked
        signUpButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignInScreen.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    //method to authorize user from firebase database

    private void SignIn(String email, String password){
        mFireBaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //bring user to the main activity
                            Intent intent = new Intent(SignInScreen.this, MainActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(SignInScreen.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

