package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendCond;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendDto;
import prac.lineage2m.lineage2m.dto.PageRequest;
import prac.lineage2m.lineage2m.service.ItemStockSearchService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@RestController
@RequestMapping("/itemStock")
public class ItemStockController {
  private final ItemStockSearchService itemStockSearchService;

  @GetMapping("/recommendKeyword")
  public List<ItemStockSearchRecommendDto> getRecommendKeyword(@ModelAttribute ItemStockSearchRecommendCond itemStockSearchRecommendCond, PageRequest pageRequest) {
    if(pageRequest.getSize()>10) pageRequest.setSize(10);
    Pageable pageable = pageRequest.of();
    return itemStockSearchService.getRecommendKeyword(itemStockSearchRecommendCond, pageable);
  }
}

