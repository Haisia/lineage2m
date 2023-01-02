package prac.lineage2m.lineage2m.service;

import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;

@Service
public interface ItemPriceStatsSearch {
  PriceResultDto getItemPriceStatsToObject(PriceParamDto priceParamDto);
}
