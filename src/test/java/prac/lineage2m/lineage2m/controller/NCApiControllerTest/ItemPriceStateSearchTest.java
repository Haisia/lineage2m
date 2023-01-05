package prac.lineage2m.lineage2m.controller.NCApiControllerTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemPriceStateSearchTest {
  private final MockMvc mockMvc;

  @Autowired
  ItemPriceStateSearchTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  // 이하 itemPriceStatsSearch 테스트
  @Test
  @DisplayName("검색결과가 있는 조건을 조건으로 걸고 API를 콜했기 때문에 결과가 무조건 존재해야 한다.")
  public void 검색결과가_무조건_존재해야_하는_테스트_itemPriceStatsSearch() throws Exception {
    //given
    String item_id = "100630002";

    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/market/items/" + item_id + "/price?")
            .param("server_id", "1111")
            .param("enchant_level", "0");

    //expect
    mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("$.serverId").isNotEmpty())
            .andExpect(jsonPath("$.last").isNotEmpty())
            .andExpect(jsonPath("$.now").isNotEmpty())
            .andExpect(jsonPath("$.min").isNotEmpty())
            .andExpect(jsonPath("$.max").isNotEmpty())
            .andExpect(jsonPath("$.avg").isNotEmpty())
            .andDo(print());
  }

  @Test
  @DisplayName("검색결과가 없는 조건을 조건으로 걸고 API를 콜했기 때문에 결과가 무조건 없어야 한다.")
  public void 검색결과가_무조건_없어야_하는_테스트_itemPriceStatsSearch() throws Exception {
    //given
    String itemId = "100630002999";

    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/market/items/" + itemId + "/price?")
            .param("server_id", "1111")
            .param("enchant_level", "0");

    System.out.println("itemId = " + itemId);

    //expect
    mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("$.serverId").isEmpty())
            .andExpect(jsonPath("$.last").isEmpty())
            .andExpect(jsonPath("$.now").isEmpty())
            .andExpect(jsonPath("$.min").isEmpty())
            .andExpect(jsonPath("$.max").isEmpty())
            .andExpect(jsonPath("$.avg").isEmpty())
            .andDo(print());
  }

}