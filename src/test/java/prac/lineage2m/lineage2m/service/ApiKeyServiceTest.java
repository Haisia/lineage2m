package prac.lineage2m.lineage2m.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.repository.ApiKeyRepository;

import java.util.List;

@SpringBootTest
class ApiKeyServiceTest {
  private final ApiKeyRepository apiKeyRepository;
  String key;

  @BeforeEach
  void beforEach(){
    key = apiKeyRepository.findById(1L);
  }

  @Autowired
  ApiKeyServiceTest(ApiKeyRepository apiKeyRepository) {
    this.apiKeyRepository = apiKeyRepository;
  }


  @Test
  @DisplayName("pk 값을 매개로 디비에 저장된 특정 키값을 찾는다.")
  void findKeyById() {
    String findApiKey = apiKeyRepository.findById(1L);

    Assertions.assertThat(findApiKey).isEqualTo(key);
  }

  @Test
  @DisplayName("디비에 저장된 모든 키값을 가져온다.")
  void findAll(){
    List<String> keyList = apiKeyRepository.findAll();
    Assertions.assertThat(keyList.size()).isEqualTo(1);
    for (String s : keyList) {
      Assertions.assertThat(s).isEqualTo(key);
    }
  }
}