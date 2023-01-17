package prac.lineage2m.lineage2m.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "attribute")
public class Attribute {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pk;

  @Column(name = "safe_enchant_level")
  private Long safeEnchantLevel;

  @Column(name="tradeable")
  private boolean tradeable;

  @Column(name="enchantable")
  private boolean enchantable;

  @Column(name="droppable")
  private boolean droppable;

  @Column(name="storable")
  private boolean storable;

  @Column(name="description")
  private String description;

  @Column(name="weight")
  private Long weight;

  @Column(name="material_name")
  private String materialName;

  @Column(name="collection_count")
  private Long collectionCount;

  @OneToOne
  @JoinColumn(name = "item_info_pk")
  @ToString.Exclude
  private ItemInfo itemInfo;


  public Attribute() {
  }
}
