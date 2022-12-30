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

  @GetMapping("/market/items/search")
  public ItemSearchDto itemStockSearch(@ModelAttribute SearchParamDto searchParamDto, BindingResult bindingResult){
    return itemStockSearchService.getItemStocksToObject(searchParamDto);
  }

}
