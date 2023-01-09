package prac.lineage2m.lineage2m.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prac.lineage2m.lineage2m.entity.EnchantLevel;

public interface EnchantLevelRepository extends JpaRepository<EnchantLevel, Long>, EnchantLevelRepositoryCustom {
}
