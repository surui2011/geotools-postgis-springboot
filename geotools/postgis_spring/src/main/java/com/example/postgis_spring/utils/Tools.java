package com.example.postgis_spring.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Tools
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public class Tools {

    /**
     * @Description 对象转map，作用于public描述的字段
     * @Author SR
     * @Param
     */
    public static HashMap<String, Object> object2Map(Object object) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(object);
                map.put(fieldName, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("对象转map失败：object2Map");
            map = null;
        }
        return map;
    }

    public static Map<String, List> object2MapEmptyList(Object object) {
        Map<String, List> map = new HashMap<>();
        try {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                map.put(fieldName, new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("对象转map失败：object2MapEmptyList");
            map = null;
        }
        return map;
    }

    public static Map<String, List> object2MapList(Object object) {
        Map<String, List> map = new HashMap<>();
        try {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                List<String> value = new ArrayList<>();
                value.add(field.get(object).toString());
                map.put(fieldName, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("对象转map失败：object2MapEmptyList");
            map = null;
        }
        return map;
    }

    public static HashMap objectList2MapList(List list) {
        HashMap<String, List> map = new HashMap<>();
        try {
            if (list == null || list.size() == 0) {
                throw new Exception("list不能为空");
            }
            for (Object object : list) {
                Class<?> clazz = object.getClass();
                for (Field field : clazz.getFields()) {
                    field.setAccessible(true);
                    List value;
                    String fieldName = field.getName();
                    if (map.keySet().contains(fieldName)) {
                        value = map.get(fieldName);
                    } else {
                        value = new ArrayList<>();
                    }
                    value.add(Tools.toString(field.get(object)));
                    map.put(fieldName, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("对象转map失败：objectList2MapList");
            map = null;
        }
        return map;
    }

    public static String toString(Object object) {
        String result = object == null ? "" : object.toString();
        return result;
    }
}
