package emmek.entities;


import emmek.enumType.EventType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "football_matches")
public class FootballMatch extends Event {

    private String homeTeam;
    private String awayTeam;
    private String result;
    private int homeScore;
    private int awayScore;


    public FootballMatch() {
    }

    public FootballMatch(String title, LocalDate eventDate, String description, EventType eventType, int maxParticipants, Location location, String homeTeam, String awayTeam) {
        super(title, eventDate, description, eventType, maxParticipants, location);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.result = null;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

}
