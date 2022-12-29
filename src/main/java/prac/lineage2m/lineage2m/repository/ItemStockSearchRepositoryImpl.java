package prac.lineage2m.lineage2m.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.itemStockSearch.ItemSearchDto;
import prac.lineage2m.lineage2m.dto.itemStockSearch.SearchParamDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * ref : https://gngsn.tistory.com/154
 * <p>
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
   * @param searchParamDto
   * @param key            "Bearer ~~~~"
   * @return
   * @throws IOException
   * @throws IllegalAccessException
   */
  public String getItemStocksToJsonString(SearchParamDto searchParamDto, String key) {
    StringBuffer response = null;

    try {
      String baseUrl = "https://dev-api.plaync.com/l2m/v1.0/market/items/search?";
      String uri = getUriFromDto(searchParamDto);
      String completedUrl = baseUrl + uri;

      URL url = new URL(completedUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/json");
      conn.setRequestProperty("Authorization", key);

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

  public ItemSearchDto getItemStocksToObject(SearchParamDto searchParamDto, String key) {
    String json = getItemStocksToJsonString(searchParamDto, key);
    return jsonToObjectMapping(json, new ItemSearchDto());
  }

  /**
   * String 타입 Json 형식의 문자열 arg1 을 arg2 객체에 매핑시켜 주는 메소드.
   *
   * @param json   json of String type
   * @param target new className()
   * @return Mapped Object
   */
  public <T> T jsonToObjectMapping(String json, T target) {
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
   *
   * @param dto
   * @param <T>
   * @return dto의 파라미터로 부터 파싱한 queryString 형태의 String 타입
   * @throws IllegalAccessException
   */
  public <T> String getUriFromDto(T dto) throws IllegalAccessException {
    Class<?> clazz = dto.getClass();
    Field[] declaredFields = clazz.getDeclaredFields();
    StringBuilder uri = new StringBuilder();
    for (Field declaredField : declaredFields) {
      declaredField.setAccessible(true);
      if (declaredField.get(dto) != null) {
        uri.append("&").append(declaredField.getName()).append("=").append(declaredField.get(dto));
      }
    }
    return uri.toString();
  }
}
