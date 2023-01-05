package prac.lineage2m.lineage2m.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoAttributeDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoOptionsDto;
import prac.lineage2m.lineage2m.entity.Attribute;
import prac.lineage2m.lineage2m.entity.ItemInfo;
import prac.lineage2m.lineage2m.entity.ItemOption;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemInfoIncludeAttributeItemOptionsDto {
  private Long itemId;
  private String itemName;
  private Long enchantLevel;
  private String grade;
  private String gradeName;
  private String image;
  private String tradeCategoryName;
  private Attribute attribute;
  private List<ItemOption> options;


  public ItemInfoIncludeAttributeItemOptionsDto(Long itemId) {
    this.itemId = itemId;
  }

  public ItemInfoIncludeAttributeItemOptionsDto(ItemInfo itemInfo, Attribute attribute){
    this.itemId = itemInfo.getItemId();
    this.itemName = itemInfo.getItemName();
    this.enchantLevel = itemInfo.getEnchantLevel();
    this.grade = itemInfo.getGrade();
    this.gradeName = itemInfo.getGradeName();
    this.image = itemInfo.getImage();
    this.tradeCategoryName = itemInfo.getTradeCategoryName();
    this.attribute = attribute;
  }

  public ItemInfoIncludeAttributeItemOptionsDto(ItemInfo itemInfo, Attribute attribute, List<ItemOption> options){
    this.itemId = itemInfo.getItemId();
    this.itemName = itemInfo.getItemName();
    this.enchantLevel = itemInfo.getEnchantLevel();
    this.grade = itemInfo.getGrade();
    this.gradeName = itemInfo.getGradeName();
    this.image = itemInfo.getImage();
    this.tradeCategoryName = itemInfo.getTradeCategoryName();
    this.attribute = attribute;
    this.options = options;
  }
}
