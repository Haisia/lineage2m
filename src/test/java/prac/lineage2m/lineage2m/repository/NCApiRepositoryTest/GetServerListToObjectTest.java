package prac.lineage2m.lineage2m.repository.NCApiRepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListResultDto;
import prac.lineage2m.lineage2m.repository.apikey.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.ncapi.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class GetServerListToObjectTest {
  private final NCApiRepository ncApiRepository;
  private final ApiKeyRepository apiKeyRepository;
  String key;

  @BeforeEach
  void beforeEach(){
    key = GlobalUtil.keyMaker(apiKeyRepository.findAll().get(0));
  }

  @Test
  public void 서버목록_가져오기() throws Exception{
    //given
    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl","https://dev-api.plaync.com/l2m/v1.0/market/servers");
        put("Authorization",key);
      }
    };

    //when
    List<ServerListResultDto> resultList = ncApiRepository.getServerListToObject(options);

    //then
    ServerListResultDto serverListResultDto = resultList.get(0);
    System.out.println("serverListResultDto = " + serverListResultDto);
    assertThat(resultList.get(0).getWorld_id()).isEqualTo(1001);
    assertThat(resultList.get(0).getWorld_name()).isEqualTo("바츠");
    assertThat(resultList.get(0).getServers().get(0).getServer_name()).isEqualTo("바츠01");
    assertThat(resultList.get(0).getServers().get(9).getServer_name()).isEqualTo("바츠10");

    assertThat(resultList.get(14).getWorld_id()).isEqualTo(1141);
    assertThat(resultList.get(14).getWorld_name()).isEqualTo("안타라스");
    assertThat(resultList.get(14).getServers().get(9).getServer_name()).isEqualTo("안타라스10");
  }

  @Autowired
  public GetServerListToObjectTest(NCApiRepository ncApiRepository, ApiKeyRepository apiKeyRepository) {
    this.ncApiRepository = ncApiRepository;
    this.apiKeyRepository = apiKeyRepository;
  }
}
