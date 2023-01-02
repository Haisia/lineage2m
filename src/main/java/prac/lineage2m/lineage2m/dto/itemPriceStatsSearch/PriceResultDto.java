package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PriceResultDto {
  private Long item_id;
  private Long server_id;
  private Long enchant_level;
  private PriceLastDto last;
  private PriceNowDto now;
  private PriceMinDto min;
  private PriceMaxDto max;
  private PriceAvgDto avg;
}
