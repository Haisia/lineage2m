package prac.lineage2m.lineage2m.dto.itemPriceStatsSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * GlobalUtil.getUriFromDto 는
 * Dto 의 모든 필드를 순회해서 Uri 화 시키기 때문에
 * 중간에 pathVariable 로 받는 item_id 필드가 없는
 * 새로운 ParamDto 가 필요해서 만듦.
 */
@Getter @Setter
@ToString
public class PriceParamDtoForRepository {
  private Long server_id;
  private Long enchant_level;

  @Builder
  public PriceParamDtoForRepository(Long server_id, Long enchant_level) {
    this.server_id = server_id;
    this.enchant_level = enchant_level;
  }
}
