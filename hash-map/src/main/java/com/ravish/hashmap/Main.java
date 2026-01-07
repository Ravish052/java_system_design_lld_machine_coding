package com.ravish.hashmap;

public class Main {
    public static void main (String[] args){
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("a", 1);
        map.put("b", 2);

        System.out.println(map.get("a")); // 1
        map.remove("a");
        System.out.println(map.get("a")); // null
    }
}


