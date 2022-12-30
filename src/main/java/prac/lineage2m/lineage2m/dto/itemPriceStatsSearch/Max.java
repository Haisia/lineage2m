package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class Max {
  private Long server_id;
  private String server_name;
  private Long unit_price;
}
