package tm.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private long teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "amountOfPlayers")
    private int amountOfPlayers;

    @Column(name="points", columnDefinition = "integer default 0")
    private int points;

    @ManyToOne(targetEntity = Tournament.class)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public long getId() {
        return teamId;
    }

    public void setId(long id) {
        this.teamId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public void setAmountOfPlayers(int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }


    @Override
    public String toString() {
        return "Team{" +
                "id=" + teamId +
                ", name='" + name + '\'' +
                ", amountOfPlayers=" + amountOfPlayers +
                ", tournament=" + tournament +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamId == team.teamId &&
                amountOfPlayers == team.amountOfPlayers &&
                Objects.equals(name, team.name) &&
                Objects.equals(tournament, team.tournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, name, amountOfPlayers, tournament);
    }
}
