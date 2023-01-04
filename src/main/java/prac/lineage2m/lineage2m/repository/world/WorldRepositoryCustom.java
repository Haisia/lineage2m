package prac.lineage2m.lineage2m.repository.world;

import prac.lineage2m.lineage2m.entity.World;

import java.util.Optional;

public interface WorldRepositoryCustom {
  Optional<World> findByWorldName(String worldName);
}
