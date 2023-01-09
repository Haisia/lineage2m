package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamForRepositoryDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;
import prac.lineage2m.lineage2m.repository.apikey.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.ncapi.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemPriceStatsSearchServiceImpl implements ItemPriceStatsSearchService {
  private final ApiKeyRepository apiKeyRepository;
  private final NCApiRepository NCApiRepository;
  private static String baseUrl = "https://dev-api.plaync.com/l2m/v1.0/market/items/";
  private final String apiKey;


  public PriceResultDto getItemPriceStatsToObject(PriceParamDto priceParamDto) {
    String newBaseUrl = baseUrl + priceParamDto.getItem_id() + "/price?";
    PriceParamForRepositoryDto priceParamForRepositoryDto = new PriceParamForRepositoryDto(priceParamDto.getServer_id(), priceParamDto.getEnchant_level());

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl", newBaseUrl);
        put("Authorization", apiKey);
      }
    };

    return NCApiRepository.getItemPriceStatsToObject(priceParamForRepositoryDto, options);
  }
}
