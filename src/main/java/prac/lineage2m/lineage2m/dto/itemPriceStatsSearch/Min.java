package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Min {
  private Long server_id;
  private String server_name;
  private Long unit_price;
}
