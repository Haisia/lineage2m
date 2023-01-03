package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListResultDto;
import prac.lineage2m.lineage2m.repository.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServerListServiceImpl implements ServerListService{
  private final NCApiRepository ncApiRepository;
  private final ApiKeyRepository apiKeyRepository;


  public List<ServerListResultDto> getServerListToObject() {
    List<String> keyList = apiKeyRepository.findAll();
    String key = GlobalUtil.keyMaker(keyList.get(0));
    String newBaseUrl = "https://dev-api.plaync.com/l2m/v1.0/market/servers";

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl", newBaseUrl);
        put("Authorization", key);
      }
    };
    return ncApiRepository.getServerListToObject(options);
  }
}
