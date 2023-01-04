package prac.lineage2m.lineage2m.repository.server;

import prac.lineage2m.lineage2m.entity.Server;

import java.util.Optional;

public interface ServerRepositoryCustom {
  Optional<Server> findByServerName(String serverName);
}
