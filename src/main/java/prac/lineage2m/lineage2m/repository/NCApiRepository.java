package prac.lineage2m.lineage2m.repository;

import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ParamDto;

import java.util.Map;

@Repository
public interface NCApiRepository {
  String apiCallOfGetToJsonString(ParamDto paramDto, Map<String,String> options);
  ResultDto getItemStocksToObject(ParamDto paramDto, Map<String,String> options);
}
