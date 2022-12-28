package prac.lineage2m.lineage2m.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.domain.ApiKey;

@SpringBootTest
public class ApiKeyRepositoryTest {
  private final ApiKeyRepository apiKeyRepository;

  @Autowired
  public ApiKeyRepositoryTest(ApiKeyRepository apiKeyRepository) {
    this.apiKeyRepository = apiKeyRepository;
  }

  @Test
  void findById(){
    ApiKey apiKey = apiKeyRepository.findById(0L);
    System.out.println("#############################");
    System.out.println(apiKey.getKey());
    System.out.println(apiKey.getPk());
  }
}
