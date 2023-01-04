package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.entity.World;

import java.util.Optional;

public interface WorldCustomRepository {
  Optional<World> findByWorldName(String worldName);
}
