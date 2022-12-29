package prac.lineage2m.lineage2m.dto.itemStockSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class SearchParamDto {
  private String search_keyword;
  private Long from_enchant_level;
  private Long to_enchant_level;
  private Long server_id;
  private Boolean sale;
  private Long page;
  private Long size;

  @Builder
  public SearchParamDto(String search_keyword, Long from_enchant_level, Long to_enchant_level, Long server_id, boolean sale, Long page, Long size) {
    this.search_keyword = search_keyword;
    this.from_enchant_level = from_enchant_level;
    this.to_enchant_level = to_enchant_level;
    this.server_id = server_id;
    this.sale = sale;
    this.page = page;
    this.size = size;
  }
}
