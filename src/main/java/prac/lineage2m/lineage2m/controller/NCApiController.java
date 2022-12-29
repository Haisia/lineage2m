package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ItemSearchDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.SearchParamDto;
import prac.lineage2m.lineage2m.service.ItemStockSearchService;

@RestController
@RequiredArgsConstructor
public class NCApiController {
  private final ItemStockSearchService itemStockSearchService;

  // http://localhost:8080/market/items/search?search_keyword=test
  // http://localhost:8080/market/items/search?search_keyword=%ED%95%B8%EB%93%9C
  // http://localhost:8080/market/items/search?search_keyword=%ED%95%B8%EB%93%9C&from_enchant_level=1&to_enchant_level=1&server_id=1&sale=false&page=1&size=10
  // %ED%95%B8%EB%93%9C
  @GetMapping("/market/items/search")
  public ItemSearchDto itemStockSearch(@ModelAttribute SearchParamDto searchParamDto, BindingResult bindingResult){
    return itemStockSearchService.getItemStocksToObject(searchParamDto);
  }

}
