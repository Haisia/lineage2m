package prac.lineage2m.lineage2m.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class ItemPriceStatsSearchServiceImplTest {
  private final ItemPriceStatsSearchService itemPriceStatsSearchService;

  @Test
  @DisplayName("결과가 무조건 나와야 하는 테스트")
  public void 결과가_무조건_나와야_하는_테스트() throws Exception {
    //given
    PriceParamDto priceParamDto = PriceParamDto.builder()
            .item_id(100630002L)
            .build();

    //when
    PriceResultDto result = itemPriceStatsSearchService.getItemPriceStatsToObject(priceParamDto);

    //then
    assertThat(result).isNotNull();
    System.out.println("result.toString() = " + result.toString());
  }

  @Test
  @DisplayName("결과가 무조건 없어야 하는 테스트")
  public void 결과가_무조건_없어야_하는_테스트() throws Exception {
    //given
    PriceParamDto priceParamDto = PriceParamDto.builder()
            .item_id(1006304342002L)
            .enchant_level(0L)
            .server_id(1111L)
            .build();

    //when
    PriceResultDto result = itemPriceStatsSearchService.getItemPriceStatsToObject(priceParamDto);

    //then
    assertThat(result.getAvg()).isNull();
    System.out.println("result.toString() = " + result.toString());
  }

  @Autowired
  public ItemPriceStatsSearchServiceImplTest(ItemPriceStatsSearchService itemPriceStatsSearchService) {
    this.itemPriceStatsSearchService = itemPriceStatsSearchService;
  }

}