package prac.lineage2m.lineage2m.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import prac.lineage2m.lineage2m.entity.ItemInfo;

@Getter @Setter
@ToString
public class ItemStockSearchRecommendDto {
  private String recommendKeyword;

  public ItemStockSearchRecommendDto(ItemInfo itemInfo){
    this.recommendKeyword = itemInfo.getItemName();
  }
}
