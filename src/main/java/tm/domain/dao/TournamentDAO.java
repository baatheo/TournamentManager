package tm.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.domain.entity.Tournament;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentDAO extends JpaRepository<Tournament, Long> {
    List<Tournament> findAll();
    Optional<Tournament> findByName(String name);
    Optional<Tournament> findById(long id);
}
