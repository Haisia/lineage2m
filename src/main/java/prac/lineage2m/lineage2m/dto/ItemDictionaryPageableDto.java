package prac.lineage2m.lineage2m.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
@NoArgsConstructor
public class ItemDictionaryPageableDto {
  private List<ItemDictionaryDto> itemInfoList = new ArrayList<>();
  private Pagination pagination;

  public ItemDictionaryPageableDto(List<ItemDictionaryDto> itemInfoList, Pagination pagination) {
    this.itemInfoList = itemInfoList;
    this.pagination = pagination;
  }
}
