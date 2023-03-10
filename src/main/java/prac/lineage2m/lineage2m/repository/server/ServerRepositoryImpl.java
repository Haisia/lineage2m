package prac.lineage2m.lineage2m.repository.server;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prac.lineage2m.lineage2m.entity.Server;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Repository
public class ServerRepositoryImpl implements ServerRepositoryCustom {
  private final EntityManager em;

  public Optional<Server> findByServerName(String serverName){
    String query = "select m from Server m where m.serverName like concat(:serverName,'%')";
    Optional<Server> result;

    try {
      result = Optional.of(em.createQuery(query, Server.class)
              .setParameter("serverName",serverName)
              .getSingleResult());
    } catch (Exception e) {
      return Optional.empty();
    }
    return result;
  }
}
