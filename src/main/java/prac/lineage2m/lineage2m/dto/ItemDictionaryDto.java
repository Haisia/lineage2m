package prac.lineage2m.lineage2m.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoAttributeDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoOptionsDto;
import prac.lineage2m.lineage2m.entity.Attribute;
import prac.lineage2m.lineage2m.entity.EnchantLevel;
import prac.lineage2m.lineage2m.entity.ItemInfo;
import prac.lineage2m.lineage2m.entity.ItemOption;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter
@ToString
@NoArgsConstructor
public class ItemDictionaryDto {
  private Long itemId;
  private String itemName;
  private Long enchantLevel;
  private String grade;
  private String gradeName;
  private String image;
  private String tradeCategoryName;
  private InfoAttributeDto attribute;
  private List<InfoOptionsDto> itemOptions;

  public ItemDictionaryDto(ItemInfo itemInfo){
    this.setItemId(itemInfo.getItemId());
    this.setItemName(itemInfo.getItemName());
    this.setGrade(itemInfo.getGrade());
    this.setGradeName(itemInfo.getGradeName());
    this.setImage(itemInfo.getImage());
    this.setTradeCategoryName(itemInfo.getTradeCategoryName());
  }


  public ItemDictionaryDto(ItemInfo itemInfo, Attribute attribute, EnchantLevel enchantLevel){
    this.setItemId(itemInfo.getItemId());
    this.setItemName(itemInfo.getItemName());
    this.setGrade(itemInfo.getGrade());
    this.setGradeName(itemInfo.getGradeName());
    this.setImage(itemInfo.getImage());
    this.setTradeCategoryName(itemInfo.getTradeCategoryName());
    this.attribute = GlobalUtil.convertObjectBySameField(attribute,new InfoAttributeDto());
    this.setEnchantLevel(enchantLevel.getEnchantLevel());
  }


  @QueryProjection
  public ItemDictionaryDto(ItemInfo itemInfo, Attribute attribute, EnchantLevel enchantLevel, Set<ItemOption> itemOptions) {
    this.setItemId(itemInfo.getItemId());
    this.setItemName(itemInfo.getItemName());
    this.setEnchantLevel(enchantLevel.getEnchantLevel());
    this.setGrade(itemInfo.getGrade());
    this.setGradeName(itemInfo.getGradeName());
    this.setImage(itemInfo.getImage());
    this.setTradeCategoryName(itemInfo.getTradeCategoryName());
    this.attribute = GlobalUtil.convertObjectBySameField(attribute,new InfoAttributeDto());

    List<InfoOptionsDto> optionList = new ArrayList<>();
    for (ItemOption itemOption : itemOptions) {
      optionList.add(GlobalUtil.convertObjectBySameField(itemOption, new InfoOptionsDto()));
    }

    this.itemOptions = optionList;
  }

  public ItemDictionaryDto(ItemInfo itemInfo, Attribute attribute, EnchantLevel enchantLevel, List<ItemOption> itemOptions) {
    this.setItemId(itemInfo.getItemId());
    this.setItemName(itemInfo.getItemName());
    this.setEnchantLevel(enchantLevel.getEnchantLevel());
    this.setGrade(itemInfo.getGrade());
    this.setGradeName(itemInfo.getGradeName());
    this.setImage(itemInfo.getImage());
    this.setTradeCategoryName(itemInfo.getTradeCategoryName());
    this.attribute = GlobalUtil.convertObjectBySameField(attribute, new InfoAttributeDto());

    List<InfoOptionsDto> list = new ArrayList<>();
    for (ItemOption itemOption : itemOptions) {
      list.add(GlobalUtil.convertObjectBySameField(itemOption,new InfoOptionsDto()));
    }
    this.itemOptions = list;
  }
}
