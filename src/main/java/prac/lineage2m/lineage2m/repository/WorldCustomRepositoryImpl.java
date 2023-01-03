package prac.lineage2m.lineage2m.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prac.lineage2m.lineage2m.entity.World;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Repository
public class WorldCustomRepositoryImpl implements WorldCustomRepository{
  private final EntityManager em;

  public Optional<World> findByWorldName(String worldName){
    String query = "select m from World m where m.worldName like concat(:worldName,'%')";
    Optional<World> result;

    try {
      result = Optional.of(em.createQuery(query,World.class)
              .setParameter("worldName",worldName)
              .getSingleResult());
    } catch (Exception e) {
      return Optional.empty();
    }
    return result;
  }
}
