package prac.lineage2m.lineage2m.dto.itemInfoSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class InfoAttributeDto {
  private boolean droppable;
  private boolean tradeable;
  private Long collection_count;
  private Long weight;
  private String description;
  private boolean storable;
  private Long safe_enchant_level;
  private String material_name;
  private boolean enchantable;
}
