package tm.service.schedule;

public class MatchToSchedule {
    private int id;
    private int firstTeam;
    private int secondTeam;

    public MatchToSchedule(int id, int firstTeam, int secondTeam) {
        this.id = id;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    @Override
    public String toString() {
        return "MatchToSchedule{" +
                "id=" + id +
                ", firstTeam=" + firstTeam +
                ", secondTeam=" + secondTeam +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getFirstTeam() {
        return firstTeam;
    }

    public int getSecondTeam() {
        return secondTeam;
    }
}