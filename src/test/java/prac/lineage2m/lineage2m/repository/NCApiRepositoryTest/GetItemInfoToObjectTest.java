package prac.lineage2m.lineage2m.repository.NCApiRepositoryTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamForRepositoryDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoResultDto;
import prac.lineage2m.lineage2m.repository.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class GetItemInfoToObjectTest {
  private final NCApiRepository ncApiRepository;
  private final ApiKeyRepository apiKeyRepository;
  String key;

  @BeforeEach
  void beforEach(){
    key = GlobalUtil.keyMaker(apiKeyRepository.findById(1L));
  }

  @Test
  @DisplayName("검색결과가 항상 존재하는 조건으로 API 호출하기")
  public void 무조건_검색결과가_존재해야_하는_테스트() throws Exception{
    //given
    Long item_id = 100630002L;
    InfoParamForRepositoryDto param = new InfoParamForRepositoryDto(5L);

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl","https://dev-api.plaync.com/l2m/v1.0/market/items/"+item_id+"/?");
        put("Authorization",key);
      }
    };

    //when
    InfoResultDto result = ncApiRepository.getItemInfoToObject(param, options);

    //then
    assertThat(result.getItem_id()).isEqualTo(100630002L);
    assertThat(result.getItem_name()).isEqualTo("핸드 오브 카브리오");
    assertThat(result.getEnchant_level()).isEqualTo(5L);
    assertThat(result.getGrade()).isEqualTo("epic");
    assertThat(result.getGrade_name()).isEqualTo("영웅");
    assertThat(result.getTrade_category_name()).isEqualTo("오브");
    assertThat(result.getOptions().size()).isGreaterThanOrEqualTo(1);
    assertThat(result.getAttribute().getMaterial_name()).isEqualTo("미스릴");
  }

  @Test
  @DisplayName("검색결과가 항상 없는 조건으로 API 호출하기")
  public void 무조건_검색결과가_없어야_하는_테스트() throws Exception{
    //given
    Long item_id = 100630002222222L;
    InfoParamForRepositoryDto param = new InfoParamForRepositoryDto(5L);

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl","https://dev-api.plaync.com/l2m/v1.0/market/items/"+item_id+"/?");
        put("Authorization",key);
      }
    };

    //when
    InfoResultDto result = ncApiRepository.getItemInfoToObject(param, options);

    //then
    System.out.println("result = " + result);
    assertThat(result.getItem_id()).isEqualTo(0L);  // 검색결과가 없으면 item_id 가 기본값인 0으로 반환됨
    assertThat(result.getItem_name()).isNull();
    assertThat(result.getEnchant_level()).isEqualTo(0L);
    assertThat(result.getGrade()).isNull();
    assertThat(result.getGrade_name()).isNull();
    assertThat(result.getImage()).isNull();
    assertThat(result.getTrade_category_name()).isNull();
    assertThat(result.getAttribute()).isNull();
    assertThat(result.getOptions().size()).isEqualTo(0);

  }

  @Autowired
  public GetItemInfoToObjectTest(NCApiRepository ncApiRepository, ApiKeyRepository apiKeyRepository) {
    this.ncApiRepository = ncApiRepository;
    this.apiKeyRepository = apiKeyRepository;
  }
}
