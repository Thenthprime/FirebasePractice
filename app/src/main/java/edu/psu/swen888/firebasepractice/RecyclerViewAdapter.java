package edu.psu.swen888.firebasepractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private ArrayList<Team> teamsList;

    public RecyclerViewAdapter(ArrayList<Team> teamsList) {
        this.teamsList = teamsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView games_played;
        private TextView wins;
        private TextView losses;
        private TextView ties;
        private TextView goals_for;
        private TextView goals_against;
        private TextView total_points;

        public MyViewHolder(final View view){
            super(view);
            name = view.findViewById(R.id.textview_team_name);
            games_played = view.findViewById(R.id.textview_games_played);
            wins = view.findViewById(R.id.textview_wins);
            losses = view.findViewById(R.id.textview_losses);
            ties = view.findViewById(R.id.textview_ties);
            goals_for = view.findViewById(R.id.textview_goals_for);
            goals_against = view.findViewById(R.id.textview_goals_against);
            total_points = view.findViewById(R.id.textview_total_points);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.name.setText("Team: " + teamsList.get(position).getName());
        holder.games_played.setText("Games Played: " + teamsList.get(position).getGames_played());
        holder.wins.setText("Wins: " + teamsList.get(position).getWins());
        holder.losses.setText("Losses" + teamsList.get(position).getLosses());
        holder.ties.setText("Ties: " + teamsList.get(position).getTies());
        holder.goals_for.setText("Goals For: " + teamsList.get(position).getGoals_for());
        holder.goals_against.setText("Goals Against: " + teamsList.get(position).getGoals_against());
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
