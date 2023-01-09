package prac.lineage2m.lineage2m.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoResultDto;
import prac.lineage2m.lineage2m.repository.apikey.ApiKeyRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemInfoSearchServiceImplTest {
  private final ItemInfoSearchService itemInfoSearchService;
  private final ApiKeyRepository apiKeyRepository;
  private String key;

  @BeforeEach
  public void beforeEach(){
    key = GlobalUtil.keyMaker(apiKeyRepository.findAll().get(0));
  }

  @Test
  @DisplayName("결과가 존재하는 조건으로 API를 호출한다.")
  public void 무조건_결과가_존재해야_하는_테스트() throws Exception{
    //given
    InfoParamDto infoParamDto = InfoParamDto.builder()
            .item_id(100630002L)
            .enchant_level(5L)
            .build();

    //when
    ItemInfoIncludeAttributeItemOptionsDto result = itemInfoSearchService.getItemInfoToObject(infoParamDto);

    //then
    assertThat(result.getItemId()).isEqualTo(100630002L);
    assertThat(result.getItemName()).isEqualTo("핸드 오브 카브리오");
    assertThat(result.getEnchantLevel()).isEqualTo(5L);
    assertThat(result.getGrade()).isEqualTo("epic");
    assertThat(result.getGradeName()).isEqualTo("영웅");
    assertThat(result.getTradeCategoryName()).isEqualTo("오브");
    assertThat(result.getOptions().size()).isGreaterThanOrEqualTo(1);
    assertThat(result.getAttribute().getMaterialName()).isEqualTo("미스릴");
  }

  @Test
  @DisplayName("결과가 없는 조건으로 API를 호출한다.")
  public void 무조건_결과가_없어야_하는_테스트() throws Exception{
    //given
    InfoParamDto infoParamDto = InfoParamDto.builder()
            .item_id(10063000222222L)
            .enchant_level(5L)
            .build();

    //when
    ItemInfoIncludeAttributeItemOptionsDto result = itemInfoSearchService.getItemInfoToObject(infoParamDto);

    //then

    assertThat(result.getItemId()).isEqualTo(0L);  // 검색결과가 없으면 item_id 가 기본값인 0으로 반환됨
    assertThat(result.getItemName()).isNull();
    assertThat(result.getEnchantLevel()).isEqualTo(0L);
    assertThat(result.getGrade()).isNull();
    assertThat(result.getGradeName()).isNull();
    assertThat(result.getImage()).isNull();
    assertThat(result.getTradeCategoryName()).isNull();
    assertThat(result.getAttribute()).isNull();
    assertThat(result.getOptions().size()).isEqualTo(0);
  }






  @Autowired
  public ItemInfoSearchServiceImplTest(ItemInfoSearchService itemInfoSearchService, ApiKeyRepository apiKeyRepository) {
    this.itemInfoSearchService = itemInfoSearchService;
    this.apiKeyRepository = apiKeyRepository;
  }
}