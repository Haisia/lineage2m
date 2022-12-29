package prac.lineage2m.lineage2m.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.itemStockSearch.SearchParamDto;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class ItemSearchRepositoryImplTest {
  private final ItemStockSearchRepositoryImpl itemSearchRepository = new ItemStockSearchRepositoryImpl();

  // 이하 getItemStocksToString() 테스트
  @Test
  void getItemStocks() throws IOException, IllegalAccessException {
    String search_keyword = URLEncoder.encode("핸드", StandardCharsets.UTF_8);
    SearchParamDto searchParamDto = SearchParamDto.builder()
            .search_keyword(search_keyword)
            .from_enchant_level(1L)
            .to_enchant_level(1L)
            .server_id(1L)
            .sale(false)
            .page(1L)
            .size(30L)
            .build();

    System.out.println(itemSearchRepository.getItemStocksToJsonString(searchParamDto));
  }

  // 이하 getItemStocksToObject() 테스트

  @Test
  void getItemStocksToObject() {

  }

  @Test
  void getUriFromSearchParamDto() throws IllegalAccessException {
    String search_keyword = URLEncoder.encode("핸드", StandardCharsets.UTF_8);
    SearchParamDto searchParamDto = SearchParamDto.builder()
            .search_keyword(search_keyword)
            .from_enchant_level(1L)
            .to_enchant_level(1L)
            .server_id(1L)
            .sale(false)
            .page(1L)
            .size(30L)
            .build();

    System.out.println(itemSearchRepository.getUriFromDto(searchParamDto));

  }


}