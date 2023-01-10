package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListResultDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoResultDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;
import prac.lineage2m.lineage2m.service.ItemInfoSearchService;
import prac.lineage2m.lineage2m.service.ItemPriceStatsSearchService;
import prac.lineage2m.lineage2m.service.ItemStockSearchService;
import prac.lineage2m.lineage2m.service.ServerListService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class NCApiController {
  private final ItemStockSearchService itemStockSearchService;
  private final ItemPriceStatsSearchService itemPriceStatsSearchService;
  private final ItemInfoSearchService itemInfoSearchService;
  private final ServerListService serverListService;

  @GetMapping("/market/items/search")
  public StockResultDto itemStockSearch(@ModelAttribute StockParamDto stockParamDto, BindingResult bindingResult){
    return itemStockSearchService.getItemStocksToObject(stockParamDto);
  }

  // http://localhost:8080/market/items/100630002/price?server_id=11&enchant_level=1
  @GetMapping("/market/items/{item_id}/price")
  public PriceResultDto itemPriceStatsSearch(@ModelAttribute PriceParamDto priceParamDto, BindingResult bindingResult){
    return itemPriceStatsSearchService.getItemPriceStatsToObject(priceParamDto);
  }

  // http://localhost:8080/market/items/100630002?enchant_level=5
  @GetMapping("/market/items/{item_id}")
  public ItemInfoIncludeAttributeItemOptionsDto itemInfoSearch(@ModelAttribute InfoParamDto infoParamDto, BindingResult bindingResult){
    if(infoParamDto.getEnchant_level() == null) infoParamDto.setEnchant_level(0L);
    return itemInfoSearchService.getItemInfoToObject(infoParamDto);
  }

  @GetMapping("/market/servers")
  public List<ServerListResultDto> serverListSearch(){
    return serverListService.getServerListToObject();
  }

}
