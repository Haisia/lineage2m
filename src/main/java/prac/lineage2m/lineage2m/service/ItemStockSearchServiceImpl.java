package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;
import prac.lineage2m.lineage2m.repository.apikey.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.ncapi.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemStockSearchServiceImpl implements ItemStockSearchService {
  private final ApiKeyRepository apiKeyRepository;
  private final NCApiRepository NCApiRepository;
  private final String apiKey;
  private static String baseUrl = "https://dev-api.plaync.com/l2m/v1.0/market/items/search?";


  public StockResultDto getItemStocksToObject(StockParamDto stockParamDto) {
    if (stockParamDto.getSearch_keyword() != null && !stockParamDto.getSearch_keyword().contains("%")) {
      stockParamDto.setSearch_keyword(URLEncoder.encode(stockParamDto.getSearch_keyword(), StandardCharsets.UTF_8));
    }

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl", baseUrl);
        put("Authorization", apiKey);
      }
    };


    return NCApiRepository.getItemStocksToObject(stockParamDto, options);
  }
}
