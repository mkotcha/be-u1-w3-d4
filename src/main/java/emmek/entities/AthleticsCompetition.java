package emmek.entities;


import emmek.enumType.EventType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "athletics_competitions")
@NamedQuery(name = "winByAthlete", query = "SELECT a FROM AthleticsCompetition a WHERE a.winner = :winner")

@NamedQuery(name = "partecipateByAthlete", query = "SELECT a FROM AthleticsCompetition a JOIN Person p WHERE p = :athlete")


public class AthleticsCompetition extends Event {

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Person winner;

    @ManyToMany
    @JoinTable(name = "athletics_competitions_athletes",
            joinColumns = @JoinColumn(name = "athletics_competition_id"),
            inverseJoinColumns = @JoinColumn(name = "athlete_id"))
    private Set<Person> athletes;

    public AthleticsCompetition() {
    }

    public AthleticsCompetition(String title, LocalDate eventDate, String description, EventType eventType, int maxParticipants, Location location) {
        super(title, eventDate, description, eventType, maxParticipants, location);
    }

    public Person getWinner() {
        return winner;
    }

    public void setWinner(Person winner) {
        this.winner = winner;
    }

    public Set<Person> getAthletes() {
        return athletes;
    }

    public void setAthletes(Set<Person> athletes) {
        this.athletes = athletes;
    }

    public void addAthlete(Person athlete) {
        this.athletes.add(athlete);
    }

    public void removeAthlete(Person athlete) {
        this.athletes.remove(athlete);
    }
}
