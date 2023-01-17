package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.dto.ItemPriceStatsCond;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsResultDto;
import prac.lineage2m.lineage2m.entity.ItemPriceStats;

import java.util.List;
import java.util.Optional;

public interface ItemPriceStatsRepositoryCustom {
  Optional<ItemPriceStats> findByCond(ItemPriceStatsCond itemPriceStatsCond);
  List<ItemPriceStatsResultDto> findListByCond(ItemPriceStatsCond itemPriceStatsCond);
}
