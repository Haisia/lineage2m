package prac.lineage2m.lineage2m.controller.NCApiControllerTest;

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
import prac.lineage2m.lineage2m.util.TestUtile;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemStockSearchTest {
  private final MockMvc mockMvc;

  @Autowired
  ItemStockSearchTest(MockMvc mockMvc) {
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
}