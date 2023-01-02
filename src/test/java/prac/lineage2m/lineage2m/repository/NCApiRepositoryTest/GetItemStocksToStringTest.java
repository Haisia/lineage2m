package prac.lineage2m.lineage2m.repository.NCApiRepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockContentsDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockPaginationDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.repository.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GetItemStocksToStringTest {
  private final prac.lineage2m.lineage2m.repository.NCApiRepository NCApiRepository;
  private final ApiKeyRepository apiKeyRepository;
  String key;

  @BeforeEach
  void beforEach(){
    key = apiKeyRepository.findById(1L);
  }

  @Test
  @DisplayName("API call 결과는 NULL 또는 빈문자열이 아니여야 합니다.")
  void getItemStocks() throws IOException, IllegalAccessException {
    // given
    String search_keyword = URLEncoder.encode("핸드", StandardCharsets.UTF_8);
    StockParamDto stockParamDto = StockParamDto.builder()
            .search_keyword(search_keyword)
            .from_enchant_level(1L)
            .to_enchant_level(1L)
            .server_id(1L)
            .sale(false)
            .page(1L)
            .size(10L)
            .build();

    // when
    String key = GlobalUtil.keyMaker(this.key);
    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl","https://dev-api.plaync.com/l2m/v1.0/market/items/search?");
        put("Authorization",key);
      }
    };
    String result = NCApiRepository.apiCallOfGetToJsonString(stockParamDto, options);
    // then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
  }



  @Autowired
  public GetItemStocksToStringTest(NCApiRepository NCApiRepository, ApiKeyRepository apiKeyRepository) {
    this.NCApiRepository = NCApiRepository;
    this.apiKeyRepository = apiKeyRepository;
  }
}