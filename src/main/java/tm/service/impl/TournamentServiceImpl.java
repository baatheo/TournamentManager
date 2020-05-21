package tm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tm.domain.dao.MatchDAO;
import tm.domain.dao.TeamDAO;
import tm.domain.dao.TournamentDAO;
import tm.domain.entity.Match;
import tm.domain.entity.Team;
import tm.domain.entity.Tournament;
import tm.service.TournamentService;
import tm.service.schedule.MatchToSchedule;
import tm.service.schedule.Schedule;

import java.util.*;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    TournamentDAO tournamentDAO;
    @Autowired
    TeamDAO teamDAO;
    @Autowired
    MatchDAO matchDAO;


    @Override
    public List<Team> sortedTeamsByPoints(){
        List<Team> teams = getAllTeam();
        class SortbyPoints implements Comparator<Team>
        {
            public int compare(Team a, Team b)
            {
                return b.getPoints() - a.getPoints();
            }
        }

        Collections.sort(teams, new SortbyPoints());
        return teams;
    }

    @Override
    public void deleteAll(){
        matchDAO.deleteAll();
        teamDAO.deleteAll();
        tournamentDAO.deleteAll();
    }


    @Override
    public void updateScore(Match match){
        Optional<Team> optionalFirstTeam = teamDAO.findById(match.getFirstTeam().getId());
        Optional<Team> optionalSecondTeam = teamDAO.findById(match.getSecondTeam().getId());
        Team firstTeam = new Team();
        Team secondTeam = new Team();
        if(optionalFirstTeam.isPresent()) firstTeam = optionalFirstTeam.get();
        if(optionalSecondTeam.isPresent()) secondTeam= optionalSecondTeam.get();

        String score = match.getScore();
        String[] scores = score.split("\\:");
        System.out.println("wynik: " + Integer.getInteger(scores[0]));
        if(Integer.parseInt(scores[0]) > Integer.parseInt(scores[1])){
            firstTeam.setPoints(firstTeam.getPoints() + 3);
        }
        else if(Integer.parseInt(scores[0]) == Integer.parseInt(scores[1])){
            firstTeam.setPoints(firstTeam.getPoints() + 1);
            secondTeam.setPoints(secondTeam.getPoints() + 1);
        }else {
            secondTeam.setPoints(secondTeam.getPoints() + 3);
        }
        teamDAO.save(firstTeam);
        teamDAO.save(secondTeam);
        matchDAO.save(match);
    }

    @Override
    public void setSchedule(long id){
        Optional<Tournament> tempTour = tournamentDAO.findById(id);
        if(tempTour.isPresent()) setSchedule(tempTour.get());
    }

    @Override
    public void setSchedule(String name){
        Optional<Tournament> tempTour = tournamentDAO.findByName(name);
        if(tempTour.isPresent()) setSchedule(tempTour.get());
    }

    @Override
    public void setSchedule(Tournament tournament){
        Schedule schedule = new Schedule();
        ArrayList<Integer> ids = new ArrayList<>();
        for(Team team : getAllTeam()){
            ids.add((int) team.getId());
        }

        schedule.getSchedule(ids);
        for(MatchToSchedule match : schedule.getMatchSchedule()){
            Optional<Team> firstTeam = teamDAO.findById((long)match.getFirstTeam());
            Optional<Team> secondTeam = teamDAO.findById((long)match.getSecondTeam());
            if(firstTeam.isPresent() && secondTeam.isPresent()){
                Match newMatch = new Match(firstTeam.get(), secondTeam.get(), tournament);
                matchDAO.save(newMatch);
            }
        }
    }

    @Override
    public List<Match> getAllMatches(){
        List<Match> matches = new ArrayList<>();
        for(Match m:matchDAO.findAll()){
            matches.add(m);
        }
        return matches;
    }

    @Override
    public List<Tournament> getAllTournament(){
        List<Tournament> tournaments = new ArrayList<>();
        for(Tournament tm:tournamentDAO.findAll()){
            tournaments.add(tm);
        }
        return tournaments;
    }

    @Override
    public void postTournament(Tournament tournament) {
        tournamentDAO.save(tournament);
    }

    @Override
    public List<Team> getAllTeam() {
        List<Team> teams = new ArrayList<>();
        for(Team team: teamDAO.findAll()){
            teams.add(team);
        }
        return teams;
    }

    @Override
    public Optional<Tournament> findTourByName(String name){
        return tournamentDAO.findByName(name);
    }

    @Override
    public void postTeam(Team team){
        teamDAO.save(team);
    }

    @Override
    public void postTeamWithUnknownTour(Team team) {
        Optional<Tournament> tempTour = findTourByName(team.getTournament().getName());
        if(tempTour.isPresent()) team.setTournament(tempTour.get());
        teamDAO.save(team);
    }
}
