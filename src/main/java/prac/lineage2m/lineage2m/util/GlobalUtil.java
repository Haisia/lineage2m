package prac.lineage2m.lineage2m.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;

public class GlobalUtil {
  public static String keyMaker(String key) {
    return "Bearer " + key;
  }


}
