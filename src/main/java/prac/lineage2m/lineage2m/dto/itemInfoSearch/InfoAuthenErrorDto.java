package prac.lineage2m.lineage2m.dto.itemInfoSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class InfoAuthenErrorDto {
  private String instance;
  private String title;
  private Long status;
  private String detail;
}
