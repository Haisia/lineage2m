package prac.lineage2m.lineage2m.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import prac.lineage2m.lineage2m.repository.apikey.ApiKeyRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class GlobalConfig {
  private final ApiKeyRepository apiKeyRepository;

  @Bean(name = "apiKey")
  public String apiKey(){
    List<String> keyList = apiKeyRepository.findAll();
    return GlobalUtil.keyMaker(keyList.get(0));
  }
}
