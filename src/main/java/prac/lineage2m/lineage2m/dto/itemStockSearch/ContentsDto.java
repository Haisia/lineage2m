package prac.lineage2m.lineage2m.dto.itemStockSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ContentsDto {
  private Long item_id;
  private String item_name;
  private Long server_id;
  private String server_name;
  private Boolean world;
  private Long enchant_level;
  private String grade;
  private String image;
  private Long now_min_unit_price;
  private Long avg_unit_price;


}
