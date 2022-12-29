package prac.lineage2m.lineage2m.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.itemStockSearch.SearchParamDto;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class ItemSearchRepositoryImplTest {
  private final ItemStockSearchRepositoryImpl itemSearchRepository = new ItemStockSearchRepositoryImpl();

  // 이하 getItemStocksToString() 테스트
  @Test
  void getItemStocks() throws IOException, IllegalAccessException {
    String key = "eyJraWQiOiI2YWFmYzEzZi1hMGJjLTQ1YjYtYTUyMS00YTAyMGUzMTljYWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOiI3MERFOTg3NS01OEZCLTQ0RTYtOEQ2MS0yNTMxQTU4REUzQTIifQ.aYZQcKmLGAW0-y-XE6xnOAy0q77w5MQUWPDCpErsHz8P8neb6VagevXAyke9quon7MqTpa5qufjIn8zJl2POGuBx-epQ2qKz-nBixSYyuxExOr8RnFJVROYHOoJ2X9xWsIkIrFi0O3dESSZvWOxEXi2KvFnoBAoKoqf7XA3CGBEjkCsHytbPOilwypE0AXvhaasglzUiYVzeUDyTKdn7h9SedVq-jmnvdzsOs-tCIlUvKesKLg1kFVy7_inipXWHuQrTtAtSVkN4-O2RtG_Pocl2wMHYBrOawxPbttS8ac35kMxzPPp7MkxsW6Krz6SVfGzsHJ0CCBIgHElyDzPIeQ";
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

    System.out.println(itemSearchRepository.getItemStocksToJsonString(searchParamDto, GlobalUtil.keyMaker(key)));
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