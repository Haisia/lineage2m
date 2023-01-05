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
import prac.lineage2m.lineage2m.repository.apikey.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.ncapi.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GetItemStocksToObjectTest {
  private final prac.lineage2m.lineage2m.repository.ncapi.NCApiRepository NCApiRepository;
  private final ApiKeyRepository apiKeyRepository;
  String key;

  @BeforeEach
  void beforEach(){
    key = GlobalUtil.keyMaker(apiKeyRepository.findById(1L));
  }

  // 이하 getItemStocksToObject() 테스트

  @Test
  @DisplayName("일부 값은 검색조건과 동일해야 하고, 일부 값은 NULL이면 안된다.")
  void ApiReturnObject() throws IOException, IllegalAccessException {
    // given
    String searchStr = "핸드";
    String search_keyword = URLEncoder.encode(searchStr, StandardCharsets.UTF_8);
    StockParamDto stockParamDto = StockParamDto.builder()
            .search_keyword(search_keyword)
            .from_enchant_level(1L)
            .to_enchant_level(3L)
            .server_id(2L)
            .sale(false)
            .page(1L)
            .size(10L)
            .build();

    // when
    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl","https://dev-api.plaync.com/l2m/v1.0/market/items/search?");
        put("Authorization",key);
      }
    };
    StockResultDto result = NCApiRepository.getItemStocksToObject(stockParamDto, options);
    List<StockContentsDto> contents = result.getContents();
    StockPaginationDto pagination = result.getPagination();

    // then
    for (StockContentsDto content : contents) {
      assertThat(content.getItemName()).contains("핸드");
      assertThat(content.getEnchantLevel()).isGreaterThanOrEqualTo(1L);
      assertThat(content.getEnchantLevel()).isLessThanOrEqualTo(3L);
      assertThat(content.getImage()).isNotNull();
      assertThat(content.getNowMinUnitPrice()).isNotNull();
      assertThat(content.getAvgUnitPrice()).isNotNull();
//      assertThat(content.getServer_id()).isEqualTo(2L);
// TODO : 요청값이랑 동일하게 나와야 하는 것 같은데 다르게 나온다. 일단 nc에 문의해놓은 상태
//      https://help.plaync.com/qna/list/ticket
    }

    assertThat(pagination.getSize()).isEqualTo(10L);
  }


  @Autowired
  public GetItemStocksToObjectTest(NCApiRepository NCApiRepository, ApiKeyRepository apiKeyRepository) {
    this.NCApiRepository = NCApiRepository;
    this.apiKeyRepository = apiKeyRepository;
  }
}