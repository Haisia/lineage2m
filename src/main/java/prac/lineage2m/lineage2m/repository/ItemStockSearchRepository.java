package prac.lineage2m.lineage2m.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendCond;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendDto;

import java.util.List;

public interface ItemStockSearchRepository{
  List<ItemStockSearchRecommendDto> getRecommendKeyword(@ModelAttribute ItemStockSearchRecommendCond itemStockSearchRecommendCond, Pageable pageable);

}
