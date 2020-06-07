package com;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Map<String, String> map= new HashMap<>();
        map.put("1", "1");
        map.put("2", "1");
        map.put("1", "1");
        map.put("2", "12");
        map.put("10", "3");
        map.put(null, null);

        System.out.println(map);
    }
}
