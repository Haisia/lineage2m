package prac.lineage2m.lineage2m.repository;

import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ParamDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static prac.lineage2m.lineage2m.util.GlobalUtil.getUriFromDto;
import static prac.lineage2m.lineage2m.util.GlobalUtil.jsonToObjectMapping;

/**
 * ref : https://gngsn.tistory.com/154
 * API 호출 예)
 * https://dev-api.plaync.com/
 * l2m/v1.0/market/items/search
 * ?search_keyword=%ED%95%B8%EB%93%9C%20%EC%98%A4%EB%B8%8C%20%EC%B9%B4%EB%B8%8C%EB%A6%AC%EC%98%A4
 * &sale=false
 * &page=1&size=10
 */
@Repository
public class ItemStockSearchRepositoryImpl implements ItemStockSearchRepository {

  /**
   * @param paramDto
   * @param
   * @return
   * @throws IOException
   * @throws IllegalAccessException
   */
  public String getItemStocksToJsonString(ParamDto paramDto, Map<String, String> options) {
    StringBuffer response = null;

    try {
      String baseUrl = options.remove("baseUrl");
      String uri = getUriFromDto(paramDto);
      String completedUrl = baseUrl + uri;

      URL url = new URL(completedUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/json");
      for (String mapKey : options.keySet()) {
        conn.setRequestProperty(mapKey,options.get(mapKey));
      }

      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
      response = new StringBuffer();

      String readLine;
      while ((readLine = in.readLine()) != null) {
        response.append(readLine);
      }
      in.close();
    } catch (IllegalAccessException | IOException e) {
      throw new RuntimeException(e);
    }

    return response.toString();
  }

  public ResultDto getItemStocksToObject(ParamDto paramDto, Map<String,String> options) {
    String json = getItemStocksToJsonString(paramDto, options);
    return jsonToObjectMapping(json, new ResultDto());
  }

}
