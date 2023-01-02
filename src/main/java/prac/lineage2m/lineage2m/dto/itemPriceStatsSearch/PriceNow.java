package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PriceNow {
  private Long server_id;
  private String server_name;
  private Long unit_price;
  private Boolean world;
}
