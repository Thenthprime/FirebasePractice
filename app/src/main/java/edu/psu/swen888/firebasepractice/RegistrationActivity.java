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

public class RegistrationActivity extends AppCompatActivity {
    FirebaseAuth mFireBaseAuth;
    Button registerMe;
    EditText email;
    EditText password;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        email = findViewById(R.id.editTextCreateEmailAddress);
        password = findViewById(R.id.editTextCreateNumberPassword);
        registerMe = findViewById(R.id.createAccountbutton);
        mFireBaseAuth = FirebaseAuth.getInstance();

        //when user selects the "create account" button the createUser method is called
        registerMe.setOnClickListener(view ->{
            createUser(email.getText().toString(), password.getText().toString());
        });
    }

    //create user takes an email and password to make a new user
    private void createUser(String email, String password){
        //firebase method to create a new user
        mFireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, AllTeams.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(RegistrationActivity.this, "Error creating a user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
