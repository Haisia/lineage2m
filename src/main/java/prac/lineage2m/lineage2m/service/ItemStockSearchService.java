package prac.lineage2m.lineage2m.service;

import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ItemSearchDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.SearchParamDto;

@Service
public interface ItemStockSearchService {
  ItemSearchDto getItemStocksToObject(SearchParamDto searchParamDto);
}
