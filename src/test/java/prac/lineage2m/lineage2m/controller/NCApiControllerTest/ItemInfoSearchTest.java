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
class ItemInfoSearchTest {
  private final MockMvc mockMvc;

  @Autowired
  ItemInfoSearchTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  @DisplayName("검색결과가 존재하는 조건으로 API 호출하기")
  public void 무조건_검색결과가_존재해야_하는_테스트() throws Exception{
    //given
    String item_id = "100630002";

    //when
    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/market/items/" + item_id)
            .param("enchant_level", "5");

    //then
    mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("$.item_id").value(item_id))
            .andExpect(jsonPath("$.item_name").value("핸드 오브 카브리오"))
            .andExpect(jsonPath("$.enchant_level").value(5))
            .andExpect(jsonPath("$.grade").value("epic"))
            .andExpect(jsonPath("$.grade_name").value("영웅"))
            .andExpect(jsonPath("$.trade_category_name").value("오브"))
            .andExpect(jsonPath("$.attribute").isNotEmpty())
            .andDo(print());
  }

  @Test
  @DisplayName("검색결과가 없는 조건으로 API 호출하기")
  public void 무조건_검색결과가_없어야_하는_테스트() throws Exception{
    //given
    String item_id = "1006300022222";

    //when
    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/market/items/" + item_id)
            .param("enchant_level", "5");

    //then
    mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("$.item_id").value(0))
            .andExpect(jsonPath("$.item_name").isEmpty())
            .andExpect(jsonPath("$.enchant_level").value(0))
            .andExpect(jsonPath("$.grade").isEmpty())
            .andExpect(jsonPath("$.grade_name").isEmpty())
            .andExpect(jsonPath("$.trade_category_name").isEmpty())
            .andExpect(jsonPath("$.attribute").isEmpty())
            .andDo(print());
  }
}