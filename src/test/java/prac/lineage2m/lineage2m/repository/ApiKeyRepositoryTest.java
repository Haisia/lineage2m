package prac.lineage2m.lineage2m.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.domain.ApiKey;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ApiKeyRepositoryTest {
  private final ApiKeyRepository apiKeyRepository;

  @Autowired
  public ApiKeyRepositoryTest(ApiKeyRepository apiKeyRepository) {
    this.apiKeyRepository = apiKeyRepository;
  }

  @Test
  void findById(){
    String apiKey = apiKeyRepository.findById(0L);
    assertThat(apiKey).isEqualTo("eyJraWQiOiI2YWFmYzEzZi1hMGJjLTQ1YjYtYTUyMS00YTAyMGUzMTljYWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOiI3MERFOTg3NS01OEZCLTQ0RTYtOEQ2MS0yNTMxQTU4REUzQTIifQ.aYZQcKmLGAW0-y-XE6xnOAy0q77w5MQUWPDCpErsHz8P8neb6VagevXAyke9quon7MqTpa5qufjIn8zJl2POGuBx-epQ2qKz-nBixSYyuxExOr8RnFJVROYHOoJ2X9xWsIkIrFi0O3dESSZvWOxEXi2KvFnoBAoKoqf7XA3CGBEjkCsHytbPOilwypE0AXvhaasglzUiYVzeUDyTKdn7h9SedVq-jmnvdzsOs-tCIlUvKesKLg1kFVy7_inipXWHuQrTtAtSVkN4-O2RtG_Pocl2wMHYBrOawxPbttS8ac35kMxzPPp7MkxsW6Krz6SVfGzsHJ0CCBIgHElyDzPIeQ");
  }
}
