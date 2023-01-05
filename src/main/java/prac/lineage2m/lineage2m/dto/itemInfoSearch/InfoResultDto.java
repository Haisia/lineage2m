package prac.lineage2m.lineage2m.dto.itemInfoSearch;

import lombok.*;

import java.util.List;

@Getter @Setter
@ToString
@NoArgsConstructor
public class InfoResultDto {
  private Long itemId;
  private String itemName;
  private Long enchantLevel;
  private String grade;
  private String gradeName;
  private String image;
  private String tradeCategoryName;
  private InfoAttributeDto attribute;
  private List<InfoOptionsDto> options;


  @Builder
  public InfoResultDto(String itemName, Long enchantLevel, String grade, String gradeName, String image, String tradeCategoryName, InfoAttributeDto attribute, List<InfoOptionsDto> options) {
    this.itemName = itemName;
    this.enchantLevel = enchantLevel;
    this.grade = grade;
    this.gradeName = gradeName;
    this.image = image;
    this.tradeCategoryName = tradeCategoryName;
    this.attribute = attribute;
    this.options = options;
  }

//  public InfoResultDto(ItemInfo itemInfo, Attribute attribute, List<ItemOption> itemOptions){
//    InfoResultDto.builder()
//            .item_name(itemInfo.getItemName())
//            .enchant_level(itemInfo.getEnchantLevel())
//            .grade(itemInfo.getGrade())
//            .grade_name(itemInfo.getGradeName())
//            .image(itemInfo.getImage())
//            .trade_category_name(itemInfo.getTradeCategoryName())
//            .attribute(new InfoAttributeDto(attribute))
//            .
//  }
}
