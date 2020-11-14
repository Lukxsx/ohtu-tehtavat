package ohtu;

import java.util.Objects;

public class Player implements Comparable<Player> {

    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;
    private int pisteet;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPisteet() {
        return assists + goals;
    }

    @Override
    public String toString() {
        return name + " " + team + " " + goals + " + " + assists + " = " + getPisteet();
    }

    public String getNationality() {
        return nationality;
    }

    public int getAssists() {
        return assists;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGoals() {
        return goals;
    }

    public int getPenalties() {
        return penalties;
    }

    public String getTeam() {
        return team;
    }

    public int getGames() {
        return games;
    }

    @Override
    public int compareTo(Player o) {
        if (this.getPisteet() > o.getPisteet()) {
            return -1;
        } else if (this.getPisteet() < o.getPisteet()) {
            return 1;
        } else {
            return 0;
        }
    }

}
