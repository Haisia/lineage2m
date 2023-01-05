package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PriceNowDto {
  private Long serverId;
  private String serverName;
  private Boolean world;
  private Long unitPrice;
}
