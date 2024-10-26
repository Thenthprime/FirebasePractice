package edu.psu.swen888.firebasepractice;

import android.content.pm.PackageManager;
import android.os.Bundle;

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
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    DatabaseReference databaseReference;
    RecyclerViewAdapter adapter;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_teams_layout);

        mRecyclerView= findViewById(R.id.recyclerViewAllTeams);
        databaseReference = FirebaseDatabase.getInstance().getReference("teams");

        //Check for permission to access contacts
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            //Permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS},
                    PERMISSION_REQUEST_READ_CONTACTS);
            //get books objects from database and add them to the books array list
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
        }
        else{
            //Permission is already granted, proceed with app logic
            //get books objects from database and add them to the books array list
            //get books objects from database and add them to the books array list
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
        }
    }
}
