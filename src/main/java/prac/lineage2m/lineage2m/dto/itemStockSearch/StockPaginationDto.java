package prac.lineage2m.lineage2m.dto.itemStockSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class StockPaginationDto {
  private Long page;
  private Long size;
  private Long lastPage;
  private Long total;
}
