package prac.lineage2m.lineage2m.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@ToString
@Entity(name = "item_price_stats")
public class ItemPriceStats {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pk;

  @Column(name = "create_date")
  private LocalDate createDate;

  @Column(name = "last_price")
  private Long lastPrice;

  @Column(name = "enchant_level")
  private Long enchantLevel;

  @Column(name = "world_id")
  private Long worldId;

  @Column(name = "world_name")
  private String worldName;

  @JoinColumn(name = "item_info_pk")
  @ManyToOne
  private ItemInfo itemInfo;
}
