package prac.lineage2m.lineage2m.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.util.TestUtile;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NCApiControllerTest {
  private final MockMvc mockMvc;

  @Autowired
  NCApiControllerTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  // 이하 itemStockSearch 테스트
  @Test
  @DisplayName("아이템검색 결과 아이템이름은 search_keyword 를 포함하고 있어야 한다.")
  public void 아이템_검색결과_아이템이름_검증_itemStockSearch() throws Exception {
    //given
    List<Map<String, String>> itemList = TestUtile.itemMaker();

    //expect
    for (Map<String, String> item : itemList) {
      RequestBuilder requestBuilder = TestUtile.requestBuilderMakerByMap("/market/items/search", item);
      ResultActions result = mockMvc.perform(requestBuilder)
              .andExpect(status().isOk())
              .andExpect(header().string("Content-Type", "application/json"));

      // 검색 결과가 존재하면 검색결과의 아이템 이름은 검색조건의 아이템이름을 항상 포함해야 한다.
      try {
        result.andExpect(jsonPath("$.contents").isEmpty())
                .andDo(print());
      } catch (AssertionError e) {
        result.andExpect(jsonPath("$.contents[0].item_name", Matchers.containsString(item.get("search_keyword"))))
                .andDo(print());
      }
    }
  }


  @Test
  @DisplayName("다른조건이 없고 아이템이름이 '핸드' 를 포함하는 검색결과는 무조건 1개이상 존재해야 한다. (ex : 핸드 오브 카브리오)")
  public void 검색결과가_무조건_존재해야_하는_테스트_itemStockSearch() throws Exception {
    String searchStr = "핸드";

    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/market/items/search")
            .param("search_keyword", searchStr);

    mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("$.contents").isNotEmpty())
            .andDo(print());
  }

  @Test
  @DisplayName("다른조건이 없고 아이템 이름이 '이런이름의아이템은없을걸' 을 포함하는 검색결과는 무조건 존재하지 않아야 한다.")
  public void 검색결과가_무조건_존재하지_않아야_하는_테스트_itemStockSearch() throws Exception {
    String searchStr = "이런이름의아이템은없을걸";

    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/market/items/search")
            .param("search_keyword", searchStr);

    mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("$.contents").isEmpty())
            .andDo(print());
  }

  // 이하 itemPriceStatsSearch 테스트
  @Test
  @DisplayName("검색결과가 있는 조건을 조건으로 걸고 API를 콜했기 때문에 결과가 무조건 존재해야 한다.")
  public void 검색결과가_무조건_존재해야_하는_테스트_itemPriceStatsSearch() throws Exception {
    //given
    String item_id = "100630002";

    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/market/items/" + item_id + "/price?")
            .param("item_id", item_id)
            .param("server_id", "1111")
            .param("enchant_level", "0");

    //expect
    mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("$.server_id").isNotEmpty())
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
    String item_id = "100630002999";

    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/market/items/" + item_id + "/price?")
            .param("item_id", item_id)
            .param("server_id", "1111")
            .param("enchant_level", "0");

    //expect
    mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("$.server_id").isEmpty())
            .andExpect(jsonPath("$.last").isEmpty())
            .andExpect(jsonPath("$.now").isEmpty())
            .andExpect(jsonPath("$.min").isEmpty())
            .andExpect(jsonPath("$.max").isEmpty())
            .andExpect(jsonPath("$.avg").isEmpty())
            .andDo(print());
  }

}