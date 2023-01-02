package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDtoForRepository;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;
import prac.lineage2m.lineage2m.repository.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemPriceStatsSearchImpl implements ItemPriceStatsSearch {
  private final ApiKeyRepository apiKeyRepository;
  private final NCApiRepository NCApiRepository;
  private static String baseUrl = "https://dev-api.plaync.com/l2m/v1.0/market/items/";


  public PriceResultDto getItemPriceStatsToObject(PriceParamDto priceParamDto) {
    String newBaseUrl = baseUrl + priceParamDto.getItem_id() + "/price?";
    PriceParamDtoForRepository priceParamDtoForRepository = new PriceParamDtoForRepository(priceParamDto.getServer_id(), priceParamDto.getEnchant_level());
    List<String> keyList = apiKeyRepository.findAll();
    String key = GlobalUtil.keyMaker(keyList.get(0));

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl", newBaseUrl);
        put("Authorization", key);
      }
    };

    return NCApiRepository.getItemPriceStatsToObject(priceParamDtoForRepository, options);
  }
}
