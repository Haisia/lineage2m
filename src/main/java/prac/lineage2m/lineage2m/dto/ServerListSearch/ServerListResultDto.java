package prac.lineage2m.lineage2m.dto.ServerListSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class ServerListResultDto {
  private Long world_id;
  private String world_name;
  private List<ServerListServerDto> servers;
}
