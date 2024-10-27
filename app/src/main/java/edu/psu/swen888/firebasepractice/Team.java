package edu.psu.swen888.firebasepractice;

import java.io.Serializable;

public class Team implements Serializable {
    private String name;
    private int games_played;
    private int wins;
    private int losses;
    private int ties;
    private int goals_for;
    private int goals_against;
    private int total_points;

    public Team(String name, int games_played, int wins, int losses, int ties, int goals_for, int goals_against, int total_points) {
        this.name = name;
        this.games_played = games_played;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.goals_for = goals_for;
        this.goals_against = goals_against;
        this.total_points = total_points;
    }

    //Firebase requires a no argument constructor
    public Team(){

    }

    public String getName() {
        return name;
    }

    public int getGames_played() {
        return games_played;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public int getGoals_for() {
        return goals_for;
    }

    public int getGoals_against() {
        return goals_against;
    }

    public int getTotal_points() {
        return total_points;
    }

    public void setName(String name) {
        this.name= name;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public void setGoals_for(int goals_for) {
        this.goals_for = goals_for;
    }

    public void setGoals_against(int goals_against) {
        this.goals_against = goals_against;
    }

    public void setTotal_points(int total_points) {
        this.total_points = total_points;
    }
}
