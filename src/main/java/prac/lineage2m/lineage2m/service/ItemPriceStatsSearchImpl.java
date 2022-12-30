package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ParamDtoForRepository;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ResultDto;
import prac.lineage2m.lineage2m.repository.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemPriceStatsSearchImpl implements ItemPriceStatsSearch{
  private final ApiKeyRepository apiKeyRepository;
  private final NCApiRepository NCApiRepository;
  private static String baseUrl = "https://dev-api.plaync.com/l2m/v1.0/market/items/";


  public ResultDto getItemPriceStatsToObject(ParamDto paramDto) {
    String newBaseUrl = baseUrl+paramDto.getItem_id()+"/price?";
    ParamDtoForRepository paramDtoForRepository = new ParamDtoForRepository(paramDto.getServer_id(), paramDto.getEnchant_level());

    List<String> keyList = apiKeyRepository.findAll();
    String key = GlobalUtil.keyMaker(keyList.get(0));

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl",newBaseUrl);
        put("Authorization",key);
      }
    };

    return NCApiRepository.getItemPriceStatsToObject(paramDtoForRepository, options);
  }
}
