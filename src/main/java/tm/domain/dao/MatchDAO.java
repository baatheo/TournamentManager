package tm.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.domain.entity.Match;

import java.util.List;

@Repository
public interface MatchDAO extends JpaRepository<Match, Long> {
    List<Match> findAll();
}
