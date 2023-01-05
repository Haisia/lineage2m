package prac.lineage2m.lineage2m.util;

import java.lang.reflect.Field;

public class GlobalUtil {
  public static String keyMaker(String key) {
    return "Bearer " + key;
  }

  /**
   * source 객체를 result 객체로 변환해주는 유틸.
   * 사용조건
   * 1. source 와 result 의 모든 필드의 타입과 필드명이 동일해야 한다.
   * 2. result 의 모든 필드가 source 에 포함되어 있어야 한다. (아닐 시 해당 값 null)
   * @return 변환된 객체
   */
  public static <S, R> R convertObjectBySameField(S source, R result) {
    Field[] sourceFields = source.getClass().getDeclaredFields();
    Class<?> resultClass = result.getClass();

    for (Field sourceField : sourceFields) {
      Field resultField = null;
      try {
        resultField = resultClass.getDeclaredField(sourceField.getName());
      } catch (NoSuchFieldException | SecurityException e) {
        continue;
      }
      sourceField.setAccessible(true);
      resultField.setAccessible(true);
      try {
        resultField.set(result, sourceField.get(source));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
    return result;
  }

}
