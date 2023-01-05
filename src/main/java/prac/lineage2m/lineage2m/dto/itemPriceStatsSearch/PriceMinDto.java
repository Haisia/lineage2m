package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PriceMinDto {
  private Long serverId;
  private String serverName;
  private boolean world;
  private Long unitPrice;
}
