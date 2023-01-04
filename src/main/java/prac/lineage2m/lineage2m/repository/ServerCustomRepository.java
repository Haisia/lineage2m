package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.entity.Server;

import java.util.Optional;

public interface ServerCustomRepository {
  Optional<Server> findByServerName(String serverName);
}
