package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListResultDto;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListServerDto;
import prac.lineage2m.lineage2m.entity.Server;
import prac.lineage2m.lineage2m.entity.World;
import prac.lineage2m.lineage2m.repository.ServerCustomRepository;
import prac.lineage2m.lineage2m.repository.ServerRepository;
import prac.lineage2m.lineage2m.repository.WorldCustomRepository;
import prac.lineage2m.lineage2m.repository.WorldRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
  private final ServerListService serverListService;
  private final WorldRepository worldRepository;
  private final ServerRepository serverRepository;
  private final WorldCustomRepository worldCustomRepository;
  private final ServerCustomRepository serverCustomRepository;

  /**
   * API 로 월드랑 서버목록 전체 받아와서 DB에 등록한다.
   * 신서버 나올때만 한번씩 돌려주면 된다.
   * @return 성공여부
   */
  public boolean saveWorldAndServerList() {
    List<ServerListResultDto> worldListOfDto = serverListService.getServerListToObject();

    List<World> worldList = new ArrayList<>();
    List<Server> serverList = new ArrayList<>();

    dtoSeparateToWorldAndServerList(worldListOfDto, worldList, serverList);

    Map<String, Long> worldNameAndPk = saveDistinctWorld(worldList);

    return saveDistinctServer(serverList, worldNameAndPk);
  }

  private boolean saveDistinctServer(List<Server> serverList, Map<String, Long> worldNameAndPk) {
    for (Server server : serverList) {
      String serverName = server.getServerName();
      serverName = serverName.substring(0,serverName.length()-2);

      Long worldPk = worldNameAndPk.get(serverName);

      World world = null;
      try {
        world = worldRepository.findById(worldPk).orElseThrow();
      } catch (Exception e) {
        return false;
      }

      server.setWorld(world);

      if(serverCustomRepository.findByServerName(server.getServerName()).isEmpty()) {
        serverRepository.saveAndFlush(server);
      }
    }
    return true;
  }

  private Map<String, Long> saveDistinctWorld(List<World> worldList) {
    Map<String, Long> worldNameAndPk = new HashMap<>();
    for (World world : worldList) {
      if(worldCustomRepository.findByWorldName(world.getWorldName()).isEmpty()) worldRepository.saveAndFlush(world);
      worldNameAndPk.put(world.getWorldName(),world.getPk());
    }
    return worldNameAndPk;
  }

  private static void dtoSeparateToWorldAndServerList(List<ServerListResultDto> worldListOfDto, List<World> worldList, List<Server> serverList) {
    for (ServerListResultDto worldDto : worldListOfDto) {
      World world = new World(worldDto.getWorld_id(), worldDto.getWorld_name());
      worldList.add(world);

      List<ServerListServerDto> serverListOfDto = worldDto.getServers();
      for (ServerListServerDto serverDto : serverListOfDto) {
        Server server = new Server(serverDto.getServer_id(),serverDto.getServer_name());
        serverList.add(server);
      }
    }
  }
}
