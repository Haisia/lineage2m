package prac.lineage2m.lineage2m.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@ToString
public class ItemPriceStatsCond {
  private Long itemId;
  private LocalDate createDate;
  private Long enchantLevel;
  private Long worldId;

  @Builder
  public ItemPriceStatsCond(Long itemId, LocalDate createDate, Long enchantLevel, Long worldId) {
    this.itemId = itemId;
    this.createDate = createDate;
    this.enchantLevel = enchantLevel;
    this.worldId = worldId;
  }
}
