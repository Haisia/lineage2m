package prac.lineage2m.lineage2m.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PaginationDto {
  private Long page;
  private Long size;
  private Long last_page;
  private Long total;
}
