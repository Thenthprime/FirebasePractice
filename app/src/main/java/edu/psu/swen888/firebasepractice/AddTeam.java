package edu.psu.swen888.firebasepractice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTeam extends AppCompatActivity {
    EditText et_name, et_games_played, et_wins, et_losses, et_ties, et_goals_for, et_goals_against, et_total_points;
    Button AddTeam;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_team);

        //get instance of database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //make a reference path
        DatabaseReference myReference = database.getReference("teams");

        AddTeam = findViewById(R.id.SubmitNewTeambutton);

        //assign action to the addTeam button
        AddTeam.setOnClickListener(view ->{
            et_name = findViewById(R.id.edit_text_name);
            String name = et_name.getText().toString();
            et_games_played = findViewById(R.id.edit_text_games_played);
            int games_played = Integer.parseInt(et_games_played.getText().toString());
            et_wins= findViewById(R.id.edit_text_wins);
            int wins = Integer.parseInt(et_wins.getText().toString());
            et_losses = findViewById(R.id.edit_text_losses);
            int losses = Integer.parseInt(et_losses.getText().toString());
            et_ties = findViewById(R.id.edit_text_number_ties);
            int ties = Integer.parseInt(et_ties.getText().toString());
            et_goals_for = findViewById(R.id.edit_text_goals_for);
            int goals_for = Integer.parseInt(et_goals_for.getText().toString());
            et_goals_against = findViewById(R.id.edit_text_goals_against);
            int goals_against = Integer.parseInt(et_goals_against.getText().toString());
            et_total_points = findViewById(R.id.edit_text_total_points);
            int total_points = Integer.parseInt(et_total_points.getText().toString());

            //place values in team object
            Team team = new Team(name, games_played, wins, losses, ties, goals_for, goals_against, total_points);
            //push team object to database
            myReference.push().setValue(team);
            //alert success
            Toast.makeText(this, "Team added", Toast.LENGTH_SHORT).show();
        });
    }
}
