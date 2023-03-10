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
    String itemId = "100630002";

    //when
    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/market/items/" + itemId)
            .param("enchant_level", "5");

    //then
    mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("$.itemId").value(itemId))
            .andExpect(jsonPath("$.itemName").value("핸드 오브 카브리오"))
            .andExpect(jsonPath("$.enchantLevel").value(5))
            .andExpect(jsonPath("$.grade").value("epic"))
            .andExpect(jsonPath("$.gradeName").value("영웅"))
            .andExpect(jsonPath("$.tradeCategoryName").value("오브"))
            .andExpect(jsonPath("$.attribute").isNotEmpty())
            .andDo(print());
  }

  @Test
  @DisplayName("검색결과가 없는 조건으로 API 호출하기")
  public void 무조건_검색결과가_없어야_하는_테스트() throws Exception{
//    //given
//    String itemId = "1006300022222";
//
//    //when
//    RequestBuilder requestBuilder = MockMvcRequestBuilders
//            .get("/market/items/" + itemId)
//            .param("enchant_level", "5");
//
//    //then
//    mockMvc.perform(requestBuilder)
//            .andExpect(status().is4xxClientError())
//            .ex
////            .andExpect(header().string("Content-Type", "application/json"))
////            .andExpect(jsonPath("$.itemId").value(0))
////            .andExpect(jsonPath("$.itemName").isEmpty())
////            .andExpect(jsonPath("$.enchantLevel").value(0))
////            .andExpect(jsonPath("$.grade").isEmpty())
////            .andExpect(jsonPath("$.gradeName").isEmpty())
////            .andExpect(jsonPath("$.tradeCategoryName").isEmpty())
////            .andExpect(jsonPath("$.attribute").isEmpty())
//            .andDo(print());
  }
}