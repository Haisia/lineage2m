package prac.lineage2m.lineage2m.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Pagination {
  private long total;
  private long offset;
  private long limit;

  @Builder
  public Pagination(long total, long offset, long limit) {
    this.total = total;
    this.offset = offset;
    this.limit = limit;
  }
}
