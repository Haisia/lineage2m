package prac.lineage2m.lineage2m.service;

import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;

public interface ItemPriceStatsSearchService {
  PriceResultDto getItemPriceStatsToObject(PriceParamDto priceParamDto);
}
