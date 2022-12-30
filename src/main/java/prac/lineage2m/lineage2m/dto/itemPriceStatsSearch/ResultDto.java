package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ResultDto {
  private Long item_id;
  private Long enchant_level;
  private Now now;
  private Min min;
  private Max max;
  private Avg avg;
}
