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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ItemSearchDto;

import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.regex.Pattern;

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


  @Test
  @DisplayName("아이템검색 결과 아이템이름은 search_keyword 를 포함하고 있어야 한다.")
  public void 아이템_검색결과_아이템이름_검증() throws Exception{
    //given
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    String searchStr = "핸드이런아이템은없음";
    params.put("search_keyword", Collections.singletonList(searchStr));
    params.put("from_enchant_level", Collections.singletonList("1"));
    params.put("to_enchant_level", Collections.singletonList("1"));
    params.put("server_id", Collections.singletonList("1"));
    params.put("page", Collections.singletonList("1"));
    params.put("size", Collections.singletonList("10"));

    RequestBuilder requestBuilder
            = MockMvcRequestBuilders
            .get("/market/items/search")
            .queryParams(params);

    //expect
    ResultActions result = mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"));

    // 검색 결과가 존재하면 검색결과의 아이템 이름은 검색조건의 아이템이름을 항상 포함해야 한다.
    try {
      result.andExpect(jsonPath("$.contents").isEmpty())
              .andDo(print());
    } catch (AssertionError e) {
      result.andExpect(jsonPath("$.contents[0].item_name", Matchers.containsString(searchStr)))
              .andDo(print());
    }
  }
}