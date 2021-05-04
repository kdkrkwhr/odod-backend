package com.odod.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class JsonUtil {

  public JsonUtil() {}

  @SuppressWarnings("unchecked")
  public static Map<String, Object> JsonToMap(String param) {
    Gson gson = new Gson();

    return gson.fromJson(param, new HashMap<String, Object>().getClass());
  }

  @SuppressWarnings("unchecked")
  public static List<Map<String, Object>> JsonToList(String param) {
    Gson gson = new Gson();

    return gson.fromJson(param, new ArrayList<Map<String, Object>>().getClass());
  }

  @SuppressWarnings("unchecked")
  public static LinkedHashMap<String, Object> JsonToLinkedHashMap(String param) {
    Gson gson = new Gson();

    return gson.fromJson(param, new LinkedHashMap<String, Object>().getClass());
  }

  public static String ListToJson(List<?> jqGridList) {
    Gson gson = new Gson();

    return gson.toJson(jqGridList);
  }

  public static String OneStringToJson(String sData) {
    Gson gson = new Gson();

    return gson.toJson(sData);
  }

  public static String HashMapToJson(HashMap<String, Object> map) {
    Gson gson = new Gson();

    return gson.toJson(map);
  }

  public static String MapToJson(Map<String, Object> map) {
    Gson gson = new Gson();

    return gson.toJson(map);
  }
}
