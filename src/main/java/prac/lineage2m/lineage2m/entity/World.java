package prac.lineage2m.lineage2m.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@ToString
public class World {
  @Id @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pk;

  // NC가 일부 worldId를 누락해서 보내준다....
  // 고칠때까지 nullable 하자
  @Column(name = "world_id",nullable = true)
  private Long worldId;

  @Column(name = "world_name")
  private String worldName;

  public World(){}

  public World(Long worldId, String worldName) {
    this.worldId = worldId;
    this.worldName = worldName;
  }
}
