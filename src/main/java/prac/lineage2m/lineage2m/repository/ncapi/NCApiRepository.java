package prac.lineage2m.lineage2m.repository.ncapi;

import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListResultDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamForRepositoryDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamForRepositoryDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;

import java.util.List;
import java.util.Map;

public interface NCApiRepository {
  StockResultDto getItemStocksToObject(StockParamDto stockParamDto, Map<String,String> options);
  PriceResultDto getItemPriceStatsToObject(PriceParamForRepositoryDto priceParamForRepositoryDto, Map<String, String> options);
  ItemInfoIncludeAttributeItemOptionsDto getItemInfoToObject(InfoParamForRepositoryDto infoParamDto, Map<String, String> options);
  List<ServerListResultDto> getServerListToObject(Map<String, String> options);
  <T> String apiCallOfGetToJsonString(T paramDto, Map<String,String> options);
}
