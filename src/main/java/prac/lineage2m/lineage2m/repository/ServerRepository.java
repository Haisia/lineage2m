package prac.lineage2m.lineage2m.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prac.lineage2m.lineage2m.entity.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
}
