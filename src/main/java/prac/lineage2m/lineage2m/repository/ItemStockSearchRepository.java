package prac.lineage2m.lineage2m.repository;

import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ParamDto;

@Repository
public interface ItemStockSearchRepository {
  String getItemStocksToJsonString(ParamDto paramDto, String key);
  ResultDto getItemStocksToObject(ParamDto paramDto, String key);
}
