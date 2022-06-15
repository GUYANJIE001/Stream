package com.example.stream.demo;

import java.util.ArrayList;
import java.util.List;

public class Demo1List {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("殷离");
        list.add("张三");
        list.add("张三丰");

        // 得到字符串以张开头并且字符长度等于3的字符串
        List<String> listA = new ArrayList<>();
        for (String s : list) {
            // 判断字符串是否是以张开头的
            if (s.startsWith("张")) {
                listA.add(s);
            }
        }

        List<String> listB = new ArrayList<>();
        for (String s : listA) {
            // 判断字符串的长度是否==3
            if (s.length() == 3) {
                listB.add(s);
            }
        }
        for (String s : listB) {
            System.out.println(s);
        }
        System.out.println(listA);
        System.out.println(listB);
    }
}
