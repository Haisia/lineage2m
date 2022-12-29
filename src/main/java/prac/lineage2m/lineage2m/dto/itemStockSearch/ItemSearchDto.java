package prac.lineage2m.lineage2m.dto.itemStockSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class ItemSearchDto {
  private List<ItemDto> contents;
  private PaginationDto pagination;
}
