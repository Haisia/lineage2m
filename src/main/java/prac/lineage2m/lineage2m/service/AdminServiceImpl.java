package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListResultDto;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListServerDto;
import prac.lineage2m.lineage2m.entity.Server;
import prac.lineage2m.lineage2m.entity.World;
import prac.lineage2m.lineage2m.repository.ServerRepository;
import prac.lineage2m.lineage2m.repository.WorldRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
  private final ServerListService serverListService;
  private final WorldRepository worldRepository;
  private final ServerRepository serverRepository;

  /**
   * API 로 월드랑 서버목록 전체 받아와서 DB에 등록한다.
   * 서버가 추가됐을 때만 한번씩 돌려주면 된다.
   * TODO : DB에 존재하는지 확인하고 없을때만 추가하는 조건 추가하면 좋을 듯
   * @return 성공여부
   */
  public boolean saveWorldAndServerList() {
    List<ServerListResultDto> worldListOfDto = serverListService.getServerListToObject();

    List<World> worldList = new ArrayList<>();
    List<Server> serverList = new ArrayList<>();

    for (ServerListResultDto worldDto : worldListOfDto) {
      World world = new World(worldDto.getWorld_id(), worldDto.getWorld_name());
      worldList.add(world);

      List<ServerListServerDto> serverListOfDto = worldDto.getServers();
      for (ServerListServerDto serverDto : serverListOfDto) {
        Server server = new Server(serverDto.getServer_id(),serverDto.getServer_name());
        serverList.add(server);
      }
    }

    worldRepository.saveAllAndFlush(worldList);
    worldList = worldRepository.findAll();
    Map<String, Long> worldNameAndPk = new HashMap<>();

    for (World world : worldList) {
      worldNameAndPk.put(world.getWorldName(),world.getPk());
    }

    for (Server server : serverList) {
      String serverName = server.getServerName();
      serverName = serverName.substring(0,serverName.length()-2);

      Long worldPk = worldNameAndPk.get(serverName);
      World world = worldRepository.findById(worldPk).orElseThrow();

      server.setWorld(world);
    }

    serverRepository.saveAllAndFlush(serverList);

    return true;
  }
}
