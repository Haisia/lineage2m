package prac.lineage2m.lineage2m.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.NoParamDto;
import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListResultDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamForRepositoryDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoResultDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceParamForRepositoryDto;
import prac.lineage2m.lineage2m.dto.itemPriceStatsSearch.PriceResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockResultDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.StockParamDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
  public StockResultDto getItemStocksToObject(StockParamDto stockParamDto, Map<String, String> options) {
    String json = apiCallOfGetToJsonString(stockParamDto, options);
    return jsonToObjectMapping(json, new StockResultDto());
  }

  public PriceResultDto getItemPriceStatsToObject(PriceParamForRepositoryDto priceParamDto, Map<String, String> options) {
    String json = apiCallOfGetToJsonString(priceParamDto, options);
    return jsonToObjectMapping(json, new PriceResultDto());
  }

  public InfoResultDto getItemInfoToObject(InfoParamForRepositoryDto infoParamDto, Map<String, String> options) {
    String json = apiCallOfGetToJsonString(infoParamDto, options);
    return jsonToObjectMapping(json, new InfoResultDto());
  }

  public List<ServerListResultDto> getServerListToObject(Map<String, String> options) {
    String json = apiCallOfGetToJsonString(new NoParamDto(), options);
    ObjectMapper mapper = new ObjectMapper();
    List<ServerListResultDto> list = mapper.convertValue(jsonToObjectMapping(json, new ArrayList<>()), new TypeReference<List<ServerListResultDto>>() {
    });
    return list;
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
      HttpURLConnection conn = connectionMakerForGet(paramDto, options);

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
  public <T> HttpURLConnection connectionMakerForGet(T paramDto, Map<String, String> options) {
    HttpURLConnection conn;
    try {
      String completedUrl = completedUrlMaker(paramDto, options);

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

  public <T> String completedUrlMaker(T paramDto, Map<String, String> options) throws IllegalAccessException {
    String baseUrl = options.remove("baseUrl");
    String uri = getUriFromDto(paramDto);
    return baseUrl + uri;
  }

  /**
   * String 타입 Json 형식의 문자열 arg1 을 arg2 객체에 매핑시켜 주는 메소드.
   * @param json   json of String type
   * @param target new className()
   * @return Mapped Object
   */
  public static <T> T jsonToObjectMapping(String json, T target) {
    ObjectMapper mapper = new ObjectMapper();
    T result = null;
    try {
      result = (T) mapper.readValue(json, target.getClass());
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  /**
   * DTO 를 파싱해서 쿼리스트링 형태의 문자열로 만들어주는 메소드.
   * @param dto
   * @param <T>
   * @return dto의 파라미터로 부터 파싱한 queryString 형태의 String 타입
   * @throws IllegalAccessException
   */
  public static <T> String getUriFromDto(T dto) throws IllegalAccessException {
    Class<?> clazz = dto.getClass();
    Field[] declaredFields = clazz.getDeclaredFields();
    StringBuilder uri = new StringBuilder();
    boolean isFirstField = true;

    for (Field declaredField : declaredFields) {
      declaredField.setAccessible(true);
      if (declaredField.get(dto) != null && isFirstField) {
        uri.append(declaredField.getName()).append("=").append(declaredField.get(dto));
        isFirstField=false;
      } else if (declaredField.get(dto) != null) {
        uri.append("&").append(declaredField.getName()).append("=").append(declaredField.get(dto));
      }
    }
    return uri.toString();
  }
}