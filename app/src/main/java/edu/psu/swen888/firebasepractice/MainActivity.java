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


public class MainActivity extends AppCompatActivity {
    FirebaseAuth mFireBaseAuth;
    Button signInButton;
    Button signUpButton;
    EditText email;
    EditText password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

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
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    //method to authorize user from firebase database
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
                        }
                        else {
                            //if username/pw not in db, encourage user to check credentials or create an account
                            Toast.makeText(MainActivity.this, "Authentication Failed. Please check your password or create an account.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

