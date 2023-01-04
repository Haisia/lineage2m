package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamForRepositoryDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoResultDto;
import prac.lineage2m.lineage2m.repository.apikey.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.ncapi.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemInfoSearchServiceImpl implements ItemInfoSearchService {
  private final ApiKeyRepository apiKeyRepository;
  private final NCApiRepository ncApiRepository;
  private static String baseUrl = "https://dev-api.plaync.com/l2m/v1.0/market/items/";

  public InfoResultDto getItemInfoToObject(InfoParamDto infoParamDto){
    String newBaseUrl = baseUrl+infoParamDto.getItem_id()+"/?";
    InfoParamForRepositoryDto infoParamForRepositoryDto = new InfoParamForRepositoryDto(infoParamDto.getEnchant_level());
    List<String> keyList = apiKeyRepository.findAll();
    String key = GlobalUtil.keyMaker(keyList.get(0));

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl", newBaseUrl);
        put("Authorization", key);
      }
    };

    return ncApiRepository.getItemInfoToObject(infoParamForRepositoryDto,options);
  }
}
