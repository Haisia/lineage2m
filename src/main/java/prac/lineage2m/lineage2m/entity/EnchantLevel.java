package prac.lineage2m.lineage2m.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@ToString
@Entity
@Table(name = "enchant_level")
public class EnchantLevel {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pk;

  @Column(name = "enchant_level")
  private Long enchantLevel;

  @ManyToOne
  @JoinColumn(name = "item_info_pk")
  private ItemInfo itemInfo;

  @OneToMany(mappedBy = "enchantLevel")
  private List<ItemOption> itemOptionList;

  @Builder
  public EnchantLevel(Long enchantLevel, ItemInfo itemInfo) {
    this.enchantLevel = enchantLevel;
    this.itemInfo = itemInfo;
  }

  public EnchantLevel() {
  }
}
