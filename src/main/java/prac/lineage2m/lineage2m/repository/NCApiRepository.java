package prac.lineage2m.lineage2m.repository;

import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ParamDtoForRepository;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ParamDto;

import java.util.Map;

@Repository
public interface NCApiRepository {
  <T> String apiCallOfGetToJsonString(T paramDto, Map<String,String> options);
  ResultDto getItemStocksToObject(ParamDto paramDto, Map<String,String> options);

  prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ResultDto getItemPriceStatsToObject(ParamDtoForRepository paramDtoForRepository, Map<String, String> options);
}
