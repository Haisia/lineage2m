package prac.lineage2m.lineage2m.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@ToString
public class Server {
  @Id @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pk;

  @Column(name = "server_id")
  private Long serverId;

  @Column(name = "server_name")
  private String serverName;

//  @Column
//  private Long worldPk;

  @ManyToOne
  @JoinColumn(name = "world_pk")
  private World world;


  public Server(){}

  public Server(Long serverId, String serverName) {
    this.serverId = serverId;
    this.serverName = serverName;
  }
}
