package edu.psu.swen888.firebasepractice;

import static kotlin.jvm.internal.Reflection.function;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AllTeamsFragment extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<Team> teamsList = new ArrayList<>();
    DatabaseReference databaseReference;
    RecyclerViewAdapter adapter;
    ArrayList<String> keys = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_teams, container, false);

        //initialize variables
        mRecyclerView = view.findViewById(R.id.recyclerViewAllTeams);
        //make reference to database to retreive data from teams path
        databaseReference = FirebaseDatabase.getInstance().getReference("teams");

        //send the teams array through the adapter
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(teamsList, keys);
        mRecyclerView.setAdapter(adapter);


        //this listener is to add values to the teams arraylist
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Team team = dataSnapshot.getValue(Team.class);
                    teamsList.add(team);
                    keys.add(dataSnapshot.getKey());
                }
                adapter.notifyDataSetChanged();
            }
            //required function for method
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //implement swipe left/right
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBackMethod);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        return view;
    }

    //swiping callback method

    ItemTouchHelper.SimpleCallback callBackMethod = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    //do left action (remove from teams list)
                    Team swipedTeam = teamsList.get(position);
                    teamsList.remove(swipedTeam);
                    mRecyclerView.getAdapter().notifyItemChanged(position);
                    //array of IDs on Firebase
                    String key = keys.get(position);
                    databaseReference.child(key).removeValue();
                    Toast toastLeft = Toast.makeText(mRecyclerView.getContext(), "Team removed from database!", Toast.LENGTH_SHORT);
                    toastLeft.show();
                    break;
            }
        }
    };
}
