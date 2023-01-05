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
  private static String baseUrl = "https://dev-api.plaync.com/l2m/v1.0/market/items/search?";


  public StockResultDto getItemStocksToObject(StockParamDto stockParamDto) {
    List<String> keyList = apiKeyRepository.findAll();

    // todo : String 이 url 인코딩이 안되어있다고 가정하고 진행한다. 나중에 프론트 만들때 인코딩 할지 말지 정하자
    stockParamDto.setSearch_keyword(URLEncoder.encode(stockParamDto.getSearch_keyword(), StandardCharsets.UTF_8));

    // TODO : 임시로 첫번째 키 사용하는데 나중에 상황에 맞춰서 사용할 키값을 자동으로 선택하도록 바꾸자
    String key = GlobalUtil.keyMaker(keyList.get(0));

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl", baseUrl);
        put("Authorization",key);
      }
    };


    return NCApiRepository.getItemStocksToObject(stockParamDto, options);
  }
}
