package prac.lineage2m.lineage2m.service;

import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ResultDto;

@Service
public interface ItemPriceStatsSearch {
  ResultDto getItemPriceStatsToObject(ParamDto paramDto);
}
