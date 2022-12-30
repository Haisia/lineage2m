package prac.lineage2m.lineage2m.util;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtile {

  public static RequestBuilder requestBuilderMakerByMap(String uri, Map<String,String> param){
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(uri);
    for (String key : param.keySet()) {
      builder = builder.param(key,param.get(key));
    }
    return builder;
  }

  public static List<Map<String,String>> itemMaker(){
    List<Map<String,String>> list = new ArrayList<>();
    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","");
        put("sale","");
        put("from_enchant_level","");
        put("to_enchant_level","");
        put("server_id","");
        put("page","");
        put("size","");
      }
    });

//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });
//
//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });
//
//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });
//
//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });
//
//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });
//
//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });
//
//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });
//
//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });
//
//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });
//
//    list.add(new HashMap<String,String>(){
//      {
//        put("search_keyword","");
//        put("sale","");
//        put("from_enchant_level","");
//        put("to_enchant_level","");
//        put("server_id","");
//        put("page","");
//        put("size","");
//      }
//    });

    return list;
  }
}
