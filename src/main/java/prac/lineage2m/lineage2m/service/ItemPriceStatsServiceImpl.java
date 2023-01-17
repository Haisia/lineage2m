package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsCond;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsResultDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.entity.ItemInfo;
import prac.lineage2m.lineage2m.entity.ItemPriceStats;
import prac.lineage2m.lineage2m.repository.ItemInfoRepository;
import prac.lineage2m.lineage2m.repository.ItemPriceStatsRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemPriceStatsServiceImpl implements ItemPriceStatsService {
  private final ItemPriceStatsRepository itemPriceStatsRepository;
  private final ItemPriceStatsSearchService itemPriceStatsSearchService;
  private final ItemInfoRepository itemInfoRepository;


  public List<ItemPriceStatsResultDto> getItemPriceStats(ItemPriceStatsCond itemPriceStatsCond) {
    return itemPriceStatsRepository.findListByCond(itemPriceStatsCond);
  }


  /**
   * 1. /documents/itemId.scv 를 한줄씩 읽어들인다.
   * 2. 읽은 itemId 의 오늘자 가격정보가 item_price_stats 테이블에 존재하는지 확인한다.
   * 2-1. 없을 경우 -> 해당 가격정보를 DB에 등록한다.
   * 2-2. 있을 경우 -> 넘어간다.
   */
  public void updateItemPriceStatsPerDay() {
    try {
      BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ishift\\Desktop\\lineage2m\\documents\\ItemId.csv", StandardCharsets.UTF_8));
      String itemIdStr;
      while ((itemIdStr = br.readLine()) != null) {
        Long itemId = Long.parseLong(itemIdStr);
        if(!isExitItemPriceStats(itemId) && getLastPrice(itemId)!=null){
          ItemPriceStats itemPriceStats = itemPriceStatsMaker(itemId, getLastPrice(itemId));
          itemPriceStatsRepository.save(itemPriceStats);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isExitItemPriceStats(Long itemId) {
    LocalDate now = LocalDate.now();

    Long enchantLevel = 0L;
    Long worldId = 1001L;

    ItemPriceStatsCond itemPriceStatsCond = ItemPriceStatsCond.builder()
            .itemId(itemId)
            .createDate(now)
            .enchantLevel(enchantLevel)
            .worldId(worldId)
            .build();

    Optional<ItemPriceStats> itemPriceStats = itemPriceStatsRepository.findByCond(itemPriceStatsCond);
    return itemPriceStats.isPresent();
  }

  public Long getLastPrice(Long itemId) {
    PriceParamDto priceParamDto = PriceParamDto.builder()
            .item_id(itemId)
            .server_id(1001L)
            .enchant_level(0L)
            .build();

    try {
      return itemPriceStatsSearchService.getItemPriceStatsToObject(priceParamDto)
              .getLast()
              .getUnitPrice();
    } catch (Exception e) {
      return 0L;
    }
  }

  public ItemPriceStats itemPriceStatsMaker(Long itemId, Long lastPrice) {
    LocalDate now = LocalDate.now();
    Long enchantLevel = 0L;
    Long worldId = 1001L;
    String worldName = "바츠";
    ItemInfo itemInfo = itemInfoRepository.findByItemId(itemId).orElseThrow();

    ItemPriceStats itemPriceStats = new ItemPriceStats();
    itemPriceStats.setCreateDate(now);
    itemPriceStats.setLastPrice(lastPrice);
    itemPriceStats.setEnchantLevel(enchantLevel);
    itemPriceStats.setWorldId(worldId);
    itemPriceStats.setWorldName(worldName);
    itemPriceStats.setItemInfo(itemInfo);

    return itemPriceStats;
  }
}
