package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PriceResultDto {
  private Long itemId;
  private Long serverId;
  private Long enchantLevel;
  private PriceLastDto last;
  private PriceNowDto now;
  private PriceMinDto min;
  private PriceMaxDto max;
  private PriceAvgDto avg;
}
