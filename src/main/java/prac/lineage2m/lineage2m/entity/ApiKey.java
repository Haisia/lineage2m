package prac.lineage2m.lineage2m.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ApiKey {
  @Column @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pk;

  @Column
  private String key;

}
