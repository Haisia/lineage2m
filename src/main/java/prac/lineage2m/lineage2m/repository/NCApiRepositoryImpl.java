package prac.lineage2m.lineage2m.repository;

import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ParamDtoForRepository;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ParamDto;

import java.io.BufferedReader;
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
public class NCApiRepositoryImpl implements NCApiRepository {
  public ResultDto getItemStocksToObject(ParamDto paramDto, Map<String, String> options) {
    String json = apiCallOfGetToJsonString(paramDto, options);
    return jsonToObjectMapping(json, new ResultDto());
  }

  public prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ResultDto getItemPriceStatsToObject(ParamDtoForRepository paramDto, Map<String, String> options) {
    String json = apiCallOfGetToJsonString(paramDto, options);
    return jsonToObjectMapping(json, new prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.ResultDto());
  }

  /**
   * 파라미터를 참조하여 외부 API 를 Get 방식으로 호출하고
   * 그 결과를 Json 방식의 String 으로 리턴합니다.
   * @param paramDto API 를 호출할 때 사용할 파라미터
   * @param options baseUrl 와 Authorization 는 필수로 포함되어야 함.
   * @return API 호출 결과. Json 형태의 String 타입.
   */
  public <T> String apiCallOfGetToJsonString(T paramDto, Map<String, String> options) {
    StringBuffer response = null;
    try {
      HttpURLConnection conn = connectionMakerForGET(paramDto, options);

      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
      response = new StringBuffer();

      String readLine;
      while ((readLine = in.readLine()) != null) {
        response.append(readLine);
      }
      in.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return response.toString();
  }

  /**
   * 매개변수를 기반으로 외부 API 를 GET 방식으로 호출 함.
   * @param paramDto API 를 호출할 때 사용할 파라미터
   * @param options baseUrl 와 Authorization 는 필수로 포함되어야 함.
   * @return API 호출 결과
   */
  public <T> HttpURLConnection connectionMakerForGET(T paramDto, Map<String, String> options) {
    HttpURLConnection conn;
    try {
      String baseUrl = options.remove("baseUrl");
      String uri = getUriFromDto(paramDto);
      String completedUrl = baseUrl + uri;

      URL url = new URL(completedUrl);
      conn = (HttpURLConnection) url.openConnection();

      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/json");
      for (String mapKey : options.keySet()) {
        conn.setRequestProperty(mapKey, options.get(mapKey));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return conn;
  }
}