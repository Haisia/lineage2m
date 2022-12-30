package prac.lineage2m.lineage2m.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ContentsDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.PaginationDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ParamDto;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemSearchRepositoryImplTest {
  private final ItemStockSearchRepository itemStockSearchRepository;
  private final ApiKeyRepository apiKeyRepository;
  String key;

  @BeforeEach
  void beforEach(){
    key = apiKeyRepository.findById(1L);
  }

  // 이하 getItemStocksToString() 테스트
  @Test
  @DisplayName("API call 결과는 NULL 또는 빈문자열이 아니여야 합니다.")
  void getItemStocks() throws IOException, IllegalAccessException {
    // given
    String search_keyword = URLEncoder.encode("핸드", StandardCharsets.UTF_8);
    ParamDto paramDto = ParamDto.builder()
            .search_keyword(search_keyword)
            .from_enchant_level(1L)
            .to_enchant_level(1L)
            .server_id(1L)
            .sale(false)
            .page(1L)
            .size(10L)
            .build();

    // when
    String result = itemStockSearchRepository.getItemStocksToJsonString(paramDto, GlobalUtil.keyMaker(key));
    // then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
  }

  // 이하 getItemStocksToObject() 테스트

  @Test
  @DisplayName("일부 값은 검색조건과 동일해야 하고, 일부 값은 NULL이면 안된다.")
  void ApiReturnObject() throws IOException, IllegalAccessException {
    // given
    String searchStr = "핸드";
    String search_keyword = URLEncoder.encode(searchStr, StandardCharsets.UTF_8);
    ParamDto paramDto = ParamDto.builder()
            .search_keyword(search_keyword)
            .from_enchant_level(1L)
            .to_enchant_level(3L)
            .server_id(2L)
            .sale(false)
            .page(1L)
            .size(10L)
            .build();

    // when
    ResultDto result = itemStockSearchRepository.getItemStocksToObject(paramDto, GlobalUtil.keyMaker(key));
    List<ContentsDto> contents = result.getContents();
    PaginationDto pagination = result.getPagination();

    // then
    for (ContentsDto content : contents) {
      assertThat(content.getItem_name()).contains("핸드");
      assertThat(content.getEnchant_level()).isGreaterThanOrEqualTo(1L);
      assertThat(content.getEnchant_level()).isLessThanOrEqualTo(3L);
      assertThat(content.getImage()).isNotNull();
      assertThat(content.getNow_min_unit_price()).isNotNull();
      assertThat(content.getAvg_unit_price()).isNotNull();
//      assertThat(content.getServer_id()).isEqualTo(2L);
// TODO : 요청값이랑 동일하게 나와야 하는 것 같은데 다르게 나온다. 일단 nc에 문의해놓은 상태
//      https://help.plaync.com/qna/list/ticket
    }

    assertThat(pagination.getSize()).isEqualTo(10L);
  }


  @Autowired
  public ItemSearchRepositoryImplTest(ItemStockSearchRepository itemStockSearchRepository, ApiKeyRepository apiKeyRepository) {
    this.itemStockSearchRepository = itemStockSearchRepository;
    this.apiKeyRepository = apiKeyRepository;
  }
}