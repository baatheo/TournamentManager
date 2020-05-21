package tm.domain.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tournament_id")
    private long tournamentId;

    @Column(name="name")
    private String name;

    public long getId() {
        return tournamentId;
    }

    public void setId(long id) {
        this.tournamentId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + tournamentId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return tournamentId == that.tournamentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId);
    }
}
