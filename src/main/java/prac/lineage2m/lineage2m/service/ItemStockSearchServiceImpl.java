package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ItemSearchDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.SearchParamDto;
import prac.lineage2m.lineage2m.repository.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.ItemStockSearchRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemStockSearchServiceImpl implements ItemStockSearchService{
  private final ApiKeyRepository apiKeyRepository;
  private final ItemStockSearchRepository itemStockSearchRepository;

  public ItemSearchDto getItemStocksToObject(SearchParamDto searchParamDto){
    List<String> keyList = apiKeyRepository.findAll();
    // TODO : 임시로 첫번째 키 사용하는데 나중에 상황에 맞춰서 사용할 키값을 자동으로 선택하도록 바꾸자
    String key = GlobalUtil.keyMaker(keyList.get(0));

    return itemStockSearchRepository.getItemStocksToObject(searchParamDto, key);
  }



}
