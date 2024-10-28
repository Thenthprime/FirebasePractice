package edu.psu.swen888.firebasepractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopTeamsAdapter extends RecyclerView.Adapter<TopTeamsAdapter.MyViewHolder>{
    private ArrayList<Team> teamsList;

    public TopTeamsAdapter(ArrayList<Team> teamsList) {
        this.teamsList = teamsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView total_points;
        private TextView rank;

        public MyViewHolder(final View view){
            super(view);
            name = view.findViewById(R.id.top_teams_name);
            total_points = view.findViewById(R.id.top_teams_total_points);
        }
    }

    @NonNull
    @Override
    public TopTeamsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_top_team, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopTeamsAdapter.MyViewHolder holder, int position) {
        String rank = Integer.toString((position + 1));
        holder.name.setText(rank + ". " + teamsList.get(position).getName());
        holder.total_points.setText("Total Points: " + teamsList.get(position).getTotal_points());
    }

    @Override
    public int getItemCount() {
        if(teamsList != null) {
            return teamsList.size();
        }
        return 0;

    }
}
