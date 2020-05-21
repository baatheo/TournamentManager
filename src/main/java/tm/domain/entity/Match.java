package tm.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Match {

    @Id
    @GeneratedValue
    @Column(name = "match_id")
    private long matchId;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "first_team_id")
    private Team firstTeam;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "second_team_id")
    private Team secondTeam;


    @ManyToOne(targetEntity = Tournament.class)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Column
    private String score;

    public Match(){}

    public Match(Team firstTeam, Team secondTeam, Tournament tournament) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.tournament = tournament;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", firstTeam=" + firstTeam +
                ", secondTeam=" + secondTeam +
                ", tournament=" + tournament +
                ", score='" + score + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return matchId == match.matchId &&
                Objects.equals(firstTeam, match.firstTeam) &&
                Objects.equals(secondTeam, match.secondTeam) &&
                Objects.equals(tournament, match.tournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, firstTeam, secondTeam, tournament);
    }
}
