package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.dto.ItemPriceStatsCond;
import prac.lineage2m.lineage2m.entity.ItemPriceStats;

import java.util.Optional;

public interface ItemPriceStatsRepositoryCustom {
  Optional<ItemPriceStats> findByCond(ItemPriceStatsCond itemPriceStatsCond);
}
