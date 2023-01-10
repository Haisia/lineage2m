package prac.lineage2m.lineage2m.dto.itemInfoSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class InfoAttributeDto {
  private Long safeEnchantLevel;
  private boolean tradeable;
  private boolean enchantable;
  private boolean droppable;
  private boolean storable;
  private String description;
  private Long weight;
  private String materialName;
  private Long collectionCount;
}
