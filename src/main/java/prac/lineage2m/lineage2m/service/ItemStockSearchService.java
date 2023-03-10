package prac.lineage2m.lineage2m.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendCond;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;

import java.util.List;

public interface ItemStockSearchService {
  StockResultDto getItemStocksToObject(StockParamDto stockParamDto);
  List<ItemStockSearchRecommendDto> getRecommendKeyword(@ModelAttribute ItemStockSearchRecommendCond itemStockSearchRecommendCond, Pageable pageable);

}
