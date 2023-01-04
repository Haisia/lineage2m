package prac.lineage2m.lineage2m.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import prac.lineage2m.lineage2m.entity.Server;
import prac.lineage2m.lineage2m.entity.World;
import prac.lineage2m.lineage2m.repository.ServerRepository;
import prac.lineage2m.lineage2m.repository.WorldRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
  private static final int THE_NUMBER_OF_WORLD = 16;
  private static final int THE_NUMBER_OF_SERVER_PER_WORLD = 10;
  private final MockMvc mockMvc;
  private final WorldRepository worldRepository;
  private final ServerRepository serverRepository;

  @Test
  @DisplayName("World 와 Server 목록을 DB에 등록합니다.")
  public void saveServerAndWorldList() throws Exception{
    //when
    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/admin/update/serverlist");

    //then
    mockMvc.perform(requestBuilder).andExpect(status().isOk());

    List<World> worldList = worldRepository.findAll();
    List<Server> serverList = serverRepository.findAll();

    assertThat(worldList.size()).isEqualTo(THE_NUMBER_OF_WORLD);
    assertThat(serverList.size()).isEqualTo(THE_NUMBER_OF_WORLD * THE_NUMBER_OF_SERVER_PER_WORLD);
  }


  @Autowired
  public AdminControllerTest(MockMvc mockMvc, WorldRepository worldRepository, ServerRepository serverRepository) {
    this.mockMvc = mockMvc;
    this.worldRepository = worldRepository;
    this.serverRepository = serverRepository;
  }
}