package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendCond;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockContentsDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;
import prac.lineage2m.lineage2m.repository.ItemInfoRepository;
import prac.lineage2m.lineage2m.repository.ItemStockSearchRepository;
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
  private final NCApiRepository NCApiRepository;
  private final ItemInfoRepository itemInfoRepository;
  private final ItemInfoSearchService itemInfoSearchService;
  private final ItemStockSearchRepository itemStockSearchRepository;
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
    StockResultDto stockResultDto = NCApiRepository.getItemStocksToObject(stockParamDto, options);

    List<StockContentsDto> contentList = stockResultDto.getContents();
    for (StockContentsDto stockContentsDto : contentList) {
      Long itemId = stockContentsDto.getItemId();
      if(itemInfoRepository.findByItemId(itemId).isEmpty()){
        for (int i = 0; i < 14; i++) {
          itemInfoSearchService.getItemInfoToObject(InfoParamDto.builder().item_id(itemId).enchant_level((long) i).build());
        }
      }
    }
    return stockResultDto;
  }

  public List<ItemStockSearchRecommendDto> getRecommendKeyword(@ModelAttribute ItemStockSearchRecommendCond itemStockSearchRecommendCond, Pageable pageable){
    return itemStockSearchRepository.getRecommendKeyword(itemStockSearchRecommendCond, pageable);
  }
}
