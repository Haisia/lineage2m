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
@Table(name = "item_info")
public class ItemInfo {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pk;

  @Column(name = "item_id")
  private Long itemId;

  @Column(name = "item_name")
  private String itemName;

  @Column(name = "enchant_level")
  private Long enchantLevel;

  @Column(name = "grade")
  private String grade;

  @Column(name = "grade_name")
  private String gradeName;

  @Column(name = "image")
  private String image;

  @Column(name = "trade_category_name")
  private String tradeCategoryName;

  @Builder
  public ItemInfo(Long itemId, String itemName, Long enchantLevel, String grade, String gradeName, String image, String tradeCategoryName) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.enchantLevel = enchantLevel;
    this.grade = grade;
    this.gradeName = gradeName;
    this.image = image;
    this.tradeCategoryName = tradeCategoryName;
  }

  public ItemInfo() {
  }
}


