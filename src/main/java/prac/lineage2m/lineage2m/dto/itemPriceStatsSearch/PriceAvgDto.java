package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class PriceAvgDto {
  private Double unitPrice;
  private boolean world;
}
