package prac.lineage2m.lineage2m.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import prac.lineage2m.lineage2m.entity.ItemInfo;
import prac.lineage2m.lineage2m.entity.ItemPriceStats;

import java.time.LocalDate;

@Getter @Setter
@ToString
public class ItemPriceStatsResultDto {
  private Long itemId;
  private LocalDate createDate;
  private Long lastPrice;
  private Long enchantLevel;
  private Long worldId;
  private String worldName;


  public ItemPriceStatsResultDto(ItemPriceStats itemPriceStats){
    this.itemId = itemPriceStats.getItemInfo().getItemId();
    this.createDate = itemPriceStats.getCreateDate();
    this.lastPrice = itemPriceStats.getLastPrice();
    this.enchantLevel = itemPriceStats.getEnchantLevel();
    this.worldId = itemPriceStats.getWorldId();
    this.worldName = itemPriceStats.getWorldName();
  }
}
