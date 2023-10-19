package emmek.entities;


import emmek.enumType.Sex;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {
    @OneToMany(mappedBy = "winner", cascade = CascadeType.REMOVE)
    Set<AthleticsCompetition> wonAthleticsCompetitions;
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @OrderBy("eventDate")
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private Set<Participation> participations;
    @ManyToMany
    @JoinTable(name = "athletics_competitions_athletes",
            joinColumns = @JoinColumn(name = "athlete_id"),
            inverseJoinColumns = @JoinColumn(name = "athletics_competition_id"))
    private Set<AthleticsCompetition> athleticsCompetitions;


    public Person() {
    }

    public Person(String name, String surname, String email, LocalDate birthDate, Sex sex) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    public Set<AthleticsCompetition> getWonAthleticsCompetitions() {
        return wonAthleticsCompetitions;
    }

    public Set<AthleticsCompetition> getAthleticsCompetitions() {
        return athleticsCompetitions;
    }
}
