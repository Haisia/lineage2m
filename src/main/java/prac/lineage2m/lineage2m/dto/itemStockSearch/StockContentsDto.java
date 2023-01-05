package prac.lineage2m.lineage2m.dto.itemStockSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class StockContentsDto {
  private Long itemId;
  private String itemName;
  private Long serverId;
  private String serverName;
  private Boolean world;
  private Long enchantLevel;
  private String grade;
  private String image;
  private Long nowMinUnitPrice;
  private Long avgUnitPrice;


}
