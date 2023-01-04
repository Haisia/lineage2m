package prac.lineage2m.lineage2m.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item_info_option_map")
public class ItemInfoOptionMap {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pk;

  @ManyToOne
  @JoinColumn(name = "item_info_pk")
  private ItemInfo itemInfo;

  @ManyToOne
  @JoinColumn(name = "item_option_pk")
  private ItemOption itemOption;

  public ItemInfoOptionMap(ItemInfo itemInfo, ItemOption itemOption) {
    this.itemInfo = itemInfo;
    this.itemOption = itemOption;
  }

  public ItemInfoOptionMap() {
  }
}
