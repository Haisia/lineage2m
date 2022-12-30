package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ParamDto {
  private Long item_id;
  private Long server_id;
  private Long enchant_level;

  @Builder
  public ParamDto(Long item_id, Long server_id, Long enchant_level) {
    this.item_id = item_id;
    this.server_id = server_id;
    this.enchant_level = enchant_level;
  }
}
