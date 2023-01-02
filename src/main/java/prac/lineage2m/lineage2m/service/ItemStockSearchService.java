package prac.lineage2m.lineage2m.service;

import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;

public interface ItemStockSearchService {
  StockResultDto getItemStocksToObject(StockParamDto stockParamDto);
}
