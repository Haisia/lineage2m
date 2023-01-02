package prac.lineage2m.lineage2m.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;

public class GlobalUtil {
  public static String keyMaker(String key) {
    return "Bearer " + key;
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
    for (Field declaredField : declaredFields) {
      declaredField.setAccessible(true);
      if (declaredField.get(dto) != null) {
        uri.append("&").append(declaredField.getName()).append("=").append(declaredField.get(dto));
      }
    }
    return uri.toString();
  }
}
