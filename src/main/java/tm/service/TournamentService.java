package tm.service;

import tm.domain.entity.Match;
import tm.domain.entity.Team;
import tm.domain.entity.Tournament;
import tm.service.impl.TournamentServiceImpl;

import java.util.List;
import java.util.Optional;

public interface TournamentService{

    List<Tournament> getAllTournament();
    void postTournament(Tournament tournament);

    List<Team> sortedTeamsByPoints();
    List<Team> getAllTeam();

    Optional<Tournament> findTourByName(String name);

    void postTeamWithUnknownTour(Team team);
    void postTeam(Team team);

    void setSchedule(long id);
    void setSchedule(String name);
    void setSchedule(Tournament tournament);
    List<Match> getAllMatches();
    void updateScore(Match match);

    void deleteAll();
}
