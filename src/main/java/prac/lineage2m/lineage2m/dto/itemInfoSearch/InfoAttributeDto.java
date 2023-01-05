package prac.lineage2m.lineage2m.dto.itemInfoSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class InfoAttributeDto {
  private boolean droppable;
  private boolean tradeable;
  private Long collectionCount;
  private Long weight;
  private String description;
  private boolean storable;
  private Long safeEnchantLevel;
  private String materialName;
  private boolean enchantable;
}
