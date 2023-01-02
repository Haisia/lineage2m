package prac.lineage2m.lineage2m.dto.itemInfoSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class InfoResultDto {
  private String item_id;
  private String item_name;
  private Long enchant_level;
  private String grade;
  private String grade_name;
  private String image;
  private String trade_category_name;
  private InfoAttributeDto attribute;
  private List<InfoOptionsDto> options;
}
