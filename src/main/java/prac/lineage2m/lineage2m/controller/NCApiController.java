package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;
import prac.lineage2m.lineage2m.service.ItemPriceStatsSearch;
import prac.lineage2m.lineage2m.service.ItemStockSearchService;


@RestController
@RequiredArgsConstructor
public class NCApiController {
  private final ItemStockSearchService itemStockSearchService;
  private final ItemPriceStatsSearch itemPriceStatsSearch;

  @GetMapping("/market/items/search")
  public StockResultDto itemStockSearch(@ModelAttribute StockParamDto stockParamDto, BindingResult bindingResult){
    return itemStockSearchService.getItemStocksToObject(stockParamDto);
  }

  // http://localhost:8080/market/items/100630002/price?server_id=11&enchant_level=1
  @GetMapping("/market/items/{item_id}/price")
  public PriceResultDto itemPriceStatsSearch(@ModelAttribute PriceParamDto priceParamDto, BindingResult bindingResult){
    return itemPriceStatsSearch.getItemPriceStatsToObject(priceParamDto);
  }

}
