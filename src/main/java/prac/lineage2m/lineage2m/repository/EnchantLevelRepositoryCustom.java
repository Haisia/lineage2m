package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.entity.EnchantLevel;

import java.util.Optional;

public interface EnchantLevelRepositoryCustom {
  Optional<EnchantLevel> findByEnchantLevelAndItemInfoPk(Long enchantLevel, Long itemInfoPk);
}
