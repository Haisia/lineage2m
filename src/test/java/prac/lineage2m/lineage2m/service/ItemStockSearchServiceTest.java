package prac.lineage2m.lineage2m.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ItemDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ItemSearchDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.PaginationDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.SearchParamDto;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemStockSearchServiceTest {
  private final ItemStockSearchService itemStockSearchService;

  // 이하 getItemStocksToObject() 테스트
  @Test
  @DisplayName("일부 값은 검색조건과 동일해야 하고, 일부 값은 NULL이면 안된다.")
  public void getItemStocksToObject() throws Exception{
    //given
    String search_keyword = URLEncoder.encode("핸드", StandardCharsets.UTF_8);
    SearchParamDto searchParamDto = SearchParamDto.builder()
            .search_keyword(search_keyword)
            .from_enchant_level(1L)
            .to_enchant_level(1L)
            .server_id(1L)
            .sale(false)
            .page(1L)
            .size(10L)
            .build();

    //when
    ItemSearchDto result = itemStockSearchService.getItemStocksToObject(searchParamDto);
    List<ItemDto> contents = result.getContents();
    PaginationDto pagination = result.getPagination();

    //then
    for (ItemDto content : contents) {
      assertThat(content.getItem_name()).contains("핸드");
      assertThat(content.getEnchant_level()).isGreaterThanOrEqualTo(1L);
      assertThat(content.getEnchant_level()).isLessThanOrEqualTo(3L);
      assertThat(content.getImage()).isNotNull();
      assertThat(content.getNow_min_unit_price()).isNotNull();
      assertThat(content.getAvg_unit_price()).isNotNull();
    }

    assertThat(pagination.getSize()).isEqualTo(10L);
  }


  @Autowired
  public ItemStockSearchServiceTest(ItemStockSearchService itemStockSearchService) {
    this.itemStockSearchService = itemStockSearchService;
  }
}