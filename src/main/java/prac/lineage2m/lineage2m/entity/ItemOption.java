package prac.lineage2m.lineage2m.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item_option")
public class ItemOption {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pk;

  @Column(name = "option_name")
  private String optionName;

  @Column(name = "display")
  private String display;

  @Column(name = "extra_display")
  private String extraDisplay;

  @Column(name = "description")
  private String description;

  @Builder
  public ItemOption(String optionName, String display, String extraDisplay, String description) {
    this.optionName = optionName;
    this.display = display;
    this.extraDisplay = extraDisplay;
    this.description = description;
  }

  public ItemOption() {
  }
}
