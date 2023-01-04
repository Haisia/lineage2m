package prac.lineage2m.lineage2m.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.entity.Server;
import prac.lineage2m.lineage2m.entity.World;
import prac.lineage2m.lineage2m.repository.ServerRepository;
import prac.lineage2m.lineage2m.repository.WorldRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {
  final static int THE_NUMBER_OF_WORLD = 16;
  private final AdminService adminService;
  private final WorldRepository worldRepository;
  private final ServerRepository serverRepository;


  @Test
  @DisplayName("외부API를 통해 받아온 Word, Server 리스트를 DB에 등록하고 find로 확인 해 본다.")
  public void saveServerAndWorldList() throws Exception {
    //given
    adminService.saveWorldAndServerList();

    //when
    List<World> worldList = worldRepository.findAll();
    List<Server> serverList = serverRepository.findAll();

    List<String> worldNameList = worldList.stream()
            .map(World::getWorldName)
            .collect(Collectors.toList());

    //then
    boolean isWorldContainAllServerName = serverList.stream()
            .map(server -> server.getServerName().substring(0, server.getServerName().length() - 2))
            .allMatch(serverName -> worldNameList.contains(serverName) && serverName.length() >= 1);

    assertThat(isWorldContainAllServerName).isTrue();
    assertThat(worldList.size()).isEqualTo(THE_NUMBER_OF_WORLD);
    assertThat(serverList.size()).isEqualTo(THE_NUMBER_OF_WORLD * 10);
  }


  @Autowired
  public AdminServiceImplTest(AdminService adminService, WorldRepository worldRepository, ServerRepository serverRepository) {
    this.adminService = adminService;
    this.worldRepository = worldRepository;
    this.serverRepository = serverRepository;
  }
}