package prac.lineage2m.lineage2m.repository;

import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ItemSearchDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.SearchParamDto;

@Repository
public interface ItemStockSearchRepository {
  String getItemStocksToJsonString(SearchParamDto searchParamDto, String key);
  ItemSearchDto getItemStocksToObject(SearchParamDto searchParamDto, String key);
}
