package tm.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.domain.entity.Team;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamDAO extends JpaRepository<Team, Long> {
    List<Team> findAll();
    Optional<Team> findById(Long id);
}
