package tm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tm.domain.dao.TournamentDAO;
import tm.domain.entity.Match;
import tm.domain.entity.Team;
import tm.domain.entity.Tournament;
import tm.service.TournamentService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api") //tymczasowo
public class TournamentRestController {

    @Autowired
    TournamentService tournamentService;

    @GetMapping("/team/sorted/points")
    public List<Team> getSortedTeams(){
        return tournamentService.sortedTeamsByPoints();
    }

    @GetMapping("/delete/all")
    public ResponseEntity<?> deleteAll(){
        tournamentService.deleteAll();
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/team/all")
    public List<Team> getAllTeam(){
        return tournamentService.getAllTeam();
    }

    @GetMapping("/match/schedule/id/{id}")
    public ResponseEntity<?> scheduleSet(@PathVariable(name = "id") long id){
        tournamentService.setSchedule(id);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @GetMapping("/match/schedule/name/{name}")
    public ResponseEntity<?> scheduleSet(@PathVariable(name = "name") String name){
        tournamentService.setSchedule(name);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/match/all")
    public List<Match> getAllMatches(){
        List<Match> mecze = tournamentService.getAllMatches();
        return mecze;
    }

    @PutMapping("/match")
    public ResponseEntity<?> insertScore(@RequestBody Match match){
        tournamentService.updateScore(match);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/team")
    public ResponseEntity<?> addTeam(@RequestBody Team team){
        tournamentService.postTeamWithUnknownTour(team);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/tournament/all")
    public List<Tournament> getAllTournament(){
        return tournamentService.getAllTournament();
    }

    @PostMapping("/tournament")
    public ResponseEntity<?> addTournament(@RequestBody Tournament tournament){
        tournamentService.postTournament(tournament);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/team/{name}")
    public ResponseEntity<?> postTeamByNameOfTournament(@PathVariable(name = "name") String name, @RequestBody Team team){
        if(tournamentService.findTourByName(name).isPresent()){
            team.setTournament(tournamentService.findTourByName(name).get());
        }
        tournamentService.postTeam(team);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }



    @GetMapping("/test")
    public String test(){
        String t="test";
        System.out.println(t);
        return t;
    }
}
