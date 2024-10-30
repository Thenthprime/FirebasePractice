package edu.psu.swen888.firebasepractice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;

public class TopTeamsFragment extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<Team> teamsList = new ArrayList<>();
    DatabaseReference databaseReference;
    TopTeamsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_teams, container, false);

        //initialize variables
        mRecyclerView = view.findViewById(R.id.recyclerViewTopTeams);
        //get teams objects from database and add them to the teams array list
        databaseReference = FirebaseDatabase.getInstance().getReference("teams");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TopTeamsAdapter(teamsList);
        mRecyclerView.setAdapter(adapter);

        //add teams to the list from Firebase
        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Team team = dataSnapshot.getValue(Team.class);
                    teamsList.add(team);
                }
                teamsList.sort(Comparator.comparingInt(Team::getTotal_points).reversed());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}