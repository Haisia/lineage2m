package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListResultDto;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListServerDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockContentsDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.entity.Server;
import prac.lineage2m.lineage2m.entity.World;
import prac.lineage2m.lineage2m.repository.server.ServerRepository;
import prac.lineage2m.lineage2m.repository.world.WorldRepository;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
  private final static int MAX_ENCHANT_LEVEL = 13;
  private final ServerListService serverListService;
  private final WorldRepository worldRepository;
  private final ServerRepository serverRepository;
  private final ItemStockSearchService itemStockSearchService;
  private final ItemInfoSearchService itemInfoSearchService;

  /**
   * API 로 월드랑 서버목록 전체 받아와서 DB에 등록한다.
   * 신서버 나올때만 한번씩 돌려주면 된다.
   *
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

  /**
   * 아이템이름으로 거래소에 검색하고 결과로 받은 item_id 로 itemInfoSearch 를 한다.
   * NC가 아이템검색은 아이템 이름으로 검색을 지원하지 않고,
   * itemId 목록을 알아낼 방법이 없어서
   * 어쩔 수 없이 일단 이렇게 했다.
   * @return 성공 여부
   */
  public boolean updateItemList() {
    try {
      BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ishift\\Desktop\\lineage2m\\documents\\ItemList", StandardCharsets.UTF_8));
      String itemName;
      while ((itemName = br.readLine()) != null) {
        if (!itemName.startsWith("<") && itemName.length() != 0) {
          List<StockContentsDto> contents = itemStockSearchService.getItemStocksToObject(StockParamDto.builder()
                  .search_keyword(itemName)
                  .sale(false)
                  .build()).getContents();

          if (contents.size() > 0) {
            Long itemId = contents.get(0).getItemId();
            for (int i = 0; i <= MAX_ENCHANT_LEVEL; i++) {
              itemInfoSearchService.getItemInfoToObject(InfoParamDto.builder().item_id(itemId).enchant_level((long) i).build());
            }
          }
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


    return true;
  }

  private boolean saveDistinctServer(List<Server> serverList, Map<String, Long> worldNameAndPk) {
    for (Server server : serverList) {
      String serverName = server.getServerName();
      serverName = serverName.substring(0, serverName.length() - 2);

      Long worldPk = worldNameAndPk.get(serverName);

      World world = null;
      try {
        world = worldRepository.findById(worldPk).orElseThrow();
      } catch (Exception e) {
        return false;
      }

      server.setWorld(world);

      if (serverRepository.findByServerName(server.getServerName()).isEmpty()) {
        serverRepository.saveAndFlush(server);
      }
    }
    return true;
  }

  private Map<String, Long> saveDistinctWorld(List<World> worldList) {
    Map<String, Long> worldNameAndPk = new HashMap<>();
    for (World world : worldList) {
      if (worldRepository.findByWorldName(world.getWorldName()).isEmpty()) worldRepository.saveAndFlush(world);
      worldNameAndPk.put(world.getWorldName(), world.getPk());
    }
    return worldNameAndPk;
  }

  private static void dtoSeparateToWorldAndServerList(List<ServerListResultDto> worldListOfDto, List<World> worldList, List<Server> serverList) {
    for (ServerListResultDto worldDto : worldListOfDto) {
      World world = new World(worldDto.getWorldId(), worldDto.getWorldName());
      worldList.add(world);

      List<ServerListServerDto> serverListOfDto = worldDto.getServers();
      for (ServerListServerDto serverDto : serverListOfDto) {
        Server server = new Server(serverDto.getServerId(), serverDto.getServerName());
        serverList.add(server);
      }
    }
  }
}
