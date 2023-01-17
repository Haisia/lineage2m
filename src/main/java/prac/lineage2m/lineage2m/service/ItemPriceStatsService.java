package prac.lineage2m.lineage2m.service;

import org.springframework.web.bind.annotation.RequestParam;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsCond;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsResultDto;

import java.util.List;

public interface ItemPriceStatsService {
  List<ItemPriceStatsResultDto> getItemPriceStats(ItemPriceStatsCond itemPriceStatsCond);
  void updateItemPriceStatsPerDay();
  }
