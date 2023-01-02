package prac.lineage2m.lineage2m.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class ItemPriceStatsSearchImplTest {
  private final ItemPriceStatsSearch itemPriceStatsSearch;

  @Test
  @DisplayName("결과가 무조건 나와야 하는 테스트")
  public void 결과가_무조건_나와야_하는_테스트() throws Exception {
    //given
    PriceParamDto priceParamDto = PriceParamDto.builder()
            .item_id(100630002L)
            .build();

    //when
    PriceResultDto result = itemPriceStatsSearch.getItemPriceStatsToObject(priceParamDto);

    //then
    assertThat(result).isNotNull();
    System.out.println("result.toString() = " + result.toString());
  }

  @Test
  @DisplayName("결과가 무조건 없어야 하는 테스트")
  public void 결과가_무조건_없어야_하는_테스트() throws Exception {
    //given
    PriceParamDto priceParamDto = PriceParamDto.builder()
            .item_id(100630002L)
            .enchant_level(1000L)
            .build();

    //when
    PriceResultDto result = itemPriceStatsSearch.getItemPriceStatsToObject(priceParamDto);

    //then
    assertThat(result.getAvg()).isNull();
    System.out.println("result.toString() = " + result.toString());
  }

  @Autowired
  public ItemPriceStatsSearchImplTest(ItemPriceStatsSearch itemPriceStatsSearch) {
    this.itemPriceStatsSearch = itemPriceStatsSearch;
  }

}