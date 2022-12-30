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

    // 아무조건 안넣으면 모든 아이템이 검색대상이 됨.
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

    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","핸드");
        put("sale","");
        put("from_enchant_level","");
        put("to_enchant_level","");
        put("server_id","");
        put("page","");
        put("size","");
      }
    });

    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","이런이름의아이템은없습니다");
        put("sale","");
        put("from_enchant_level","");
        put("to_enchant_level","");
        put("server_id","");
        put("page","");
        put("size","");
      }
    });

    // 5강 이상인 모든아이템
    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","발라카스");
        put("sale","");
        put("from_enchant_level","5");
        put("to_enchant_level","");
        put("server_id","");
        put("page","");
        put("size","");
      }
    });

    // 3강인 모든아이템
    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","안타라스");
        put("sale","");
        put("from_enchant_level","3");
        put("to_enchant_level","3");
        put("server_id","");
        put("page","");
        put("size","");
      }
    });

    // 아이템 이름에 '핸드' 가 포함되고 2강인 아이템
    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","핸드");
        put("sale","");
        put("from_enchant_level","2");
        put("to_enchant_level","2");
        put("server_id","");
        put("page","");
        put("size","");
      }
    });

    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","");
        put("sale","true");
        put("from_enchant_level","8");
        put("to_enchant_level","");
        put("server_id","15");
        put("page","");
        put("size","");
      }
    });

    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","");
        put("sale","");
        put("from_enchant_level","");
        put("to_enchant_level","");
        put("server_id","5");
        put("page","");
        put("size","");
      }
    });

    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","");
        put("sale","");
        put("from_enchant_level","");
        put("to_enchant_level","");
        put("server_id","125");
        put("page","");
        put("size","");
      }
    });

    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","");
        put("sale","");
        put("from_enchant_level","");
        put("to_enchant_level","");
        put("server_id","135");
        put("page","");
        put("size","");
      }
    });

    list.add(new HashMap<String,String>(){
      {
        put("search_keyword","");
        put("sale","");
        put("from_enchant_level","15");
        put("to_enchant_level","");
        put("server_id","");
        put("page","");
        put("size","");
      }
    });

    return list;
  }
}





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