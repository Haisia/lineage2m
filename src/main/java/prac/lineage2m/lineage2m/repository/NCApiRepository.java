package prac.lineage2m.lineage2m.repository;

import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamForRepositoryDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;

import java.util.Map;

@Repository
public interface NCApiRepository {
  <T> String apiCallOfGetToJsonString(T paramDto, Map<String,String> options);
  StockResultDto getItemStocksToObject(StockParamDto stockParamDto, Map<String,String> options);

  PriceResultDto getItemPriceStatsToObject(PriceParamForRepositoryDto priceParamForRepositoryDto, Map<String, String> options);
}
