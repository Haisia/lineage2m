package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsCond;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsResultDto;
import prac.lineage2m.lineage2m.service.ItemPriceStatsService;

import java.util.List;

@EnableScheduling
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/itemPriceStats")
public class ItemPriceStatsController {
  private final ItemPriceStatsService itemPriceStatsService;

  // http://localhost:8080/itemPriceStats/100110001?enchantLevel=0&worldId=0
  @GetMapping("/{itemId}")
  public List<ItemPriceStatsResultDto> getItemPriceStats(@ModelAttribute ItemPriceStatsCond itemPriceStatsCond){
    return itemPriceStatsService.getItemPriceStats(itemPriceStatsCond);
  }

  // ref1 : https://andonekwon.tistory.com/70
  // ref2 : https://hyeonguj.github.io/2020/01/15/Spring-%EC%A3%BC%EA%B8%B0%EC%A0%81%EC%9C%BC%EB%A1%9C-%EC%BD%94%EB%93%9C-%EC%8B%A4%ED%96%89%ED%95%98%EA%B8%B0-Schedule/
  //  @Scheduled(fixedDelay = 24*60*60*1000, initialDelay = 24*60*60*1000)
  @Scheduled(fixedDelay = 24*60*60*1000, initialDelay = 1000)
  public void updateItemPriceStatsPerDay(){
    itemPriceStatsService.updateItemPriceStatsPerDay();
  }
}
