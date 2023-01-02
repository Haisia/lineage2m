package prac.lineage2m.lineage2m.dto.itemInfoSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class InfoParamDto {
  private Long item_id;
  private Long enchant_level;

  @Builder
  public InfoParamDto(Long item_id, Long enchant_level) {
    this.item_id = item_id;
    this.enchant_level = enchant_level;
  }
}
