package prac.lineage2m.lineage2m.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiKeyRepositoryTest {
  private final ApiKeyRepository apiKeyRepository;

  @Autowired
  public ApiKeyRepositoryTest(ApiKeyRepository apiKeyRepository) {
    this.apiKeyRepository = apiKeyRepository;
  }

  // todo: API key 값 숨기려고 임시로 제거 함
  // 테스트 할 다른방법을 찾아보자
  @Test
  void findById(){
  }
}
