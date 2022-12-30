package prac.lineage2m.lineage2m.service;

import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ParamDto;

@Service
public interface ItemStockSearchService {
  ResultDto getItemStocksToObject(ParamDto paramDto);
}
