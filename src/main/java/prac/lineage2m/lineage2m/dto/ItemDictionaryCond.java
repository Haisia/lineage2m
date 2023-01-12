package prac.lineage2m.lineage2m.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class ItemDictionaryCond {
  private String itemName;
  private Long itemId;
  private String grade;
  private String tradeCategoryName;
  private Long enchantLevel;
  private List<String> optionName;

}
