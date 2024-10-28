package edu.psu.swen888.firebasepractice;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllTeams extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<Team> teamsList = new ArrayList<>();
    DatabaseReference databaseReference;
    RecyclerViewAdapter adapter;
    Button addATeam;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_teams_layout);

        mRecyclerView= findViewById(R.id.recyclerViewAllTeams);
        databaseReference = FirebaseDatabase.getInstance().getReference("teams");
        addATeam = findViewById(R.id.go_to_add_team);

        //get teams objects from database and add them to the teams array list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(teamsList);
        mRecyclerView.setAdapter(adapter);


        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Team team = dataSnapshot.getValue(Team.class);
                    teamsList.add(team);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //include a way to get to the add team page
        addATeam.setOnClickListener(view ->{
            Intent intent = new Intent(AllTeams.this, AddTeam.class);
            startActivity(intent);
        });
    }
}
