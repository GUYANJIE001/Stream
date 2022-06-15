package com.example.stream;

import com.alibaba.fastjson.JSON;
import com.example.stream.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
class StreamApplicationTests {

    @Test
    void contextLoads() {

        List<String> list = new ArrayList<>();

        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("小昭");
        list.add("殷离");
        list.add("张三");
        list.add("张三丰");

        List<String> list1 = new ArrayList<>();
        for (String s : list) {
            if (s.startsWith("张")) {
                list1.add(s);
            }
        }
        System.out.println(list1);

        List<String> list2 = new ArrayList<>();
        for (String s : list1) {
            if (s.length() == 3) {
                list2.add(s);
            }
        }
        System.out.println(list2);

        for (String s : list2) {
            System.out.println(s);
        }
    }

    @Test
    void test1() {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("小昭");
        list.add("殷离");
        list.add("张三");
        list.add("张三丰");
        list.stream().filter(name -> name.startsWith("张"))
                .filter(name-> name.length() == 3)
                .forEach(name -> System.out.println(name));
    }

    // Stream可以通过集合数组创建
    // 通过 java.util.Collection.stream() 方法用集合创建流
    @Test
    void test2() {
        List<String> list = Arrays.asList("a","b","c");
        // 创建一个顺序流
        Stream<String> stream = list.stream();
        stream.forEach(name -> System.out.println(name));

        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEach(name -> System.out.println(name));
    }

    // 使用java。util.Arrays.stream(T[] array)方法用数组创建流
    @Test
    void test3() {
        int[] array = {1,3,5,7,9};
        IntStream stream = Arrays.stream(array);
        stream.forEach(name -> System.out.println(name));
    }

    // 使用Stream的静态方法：of(),interate(),generate()
    @Test
    void test4() {
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6);
        stream.forEach(name -> System.out.println(name));

        Stream<Integer> stream1 = Stream.iterate(0,(x) -> x + 3).limit(4);
        stream1.forEach(s -> System.out.println(s));

        // 返回带正号的 double 值，该值大于等于 0.0 且小于 1.0。
        Stream<Double> stream2 = Stream.generate(Math::random).limit(3);
        stream2.forEach(name -> System.out.println(name));
    }

    // 通过parallel()把顺序流转换成并行流
    @Test
    void test5() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().parallel().filter(x -> x > 1).findFirst();
        System.out.println(findFirst.get());
    }

    @Test
    void test6() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Stream<Integer> findFirst = list.stream().filter(x -> x > 1);
                findFirst.forEach(name -> System.out.println(name));
    }

    // Stream的使用 Optional类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象
    @Test
    void test7() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18,"male", "New York"));
        personList.add(new Person("Jack", 7000,18, "male", "Washington"));
        personList.add(new Person("Lily", 7800,18, "female", "Washington"));
        personList.add(new Person("Anni", 8200,18, "female", "New York"));
        personList.add(new Person("Owen", 9500,18, "male", "New York"));
        personList.add(new Person("Alisa", 7900,18, "female", "New York"));
        personList.stream().forEach(name -> System.out.println(name));
    }

    // 遍历/匹配(foreach,find,match)
    @Test
    void test8() {
        List<Integer> list = Arrays.asList(7,6,9,3,8,2,1);

        // 遍历输出符合条件的元素
        /*list.stream().filter(x -> x > 6).forEach(s -> System.out.println(s));*/
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        System.out.println("");
        // 匹配第一个 Optional类是一个可以为null容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回改对象
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        System.out.println(findFirst.get());
        System.out.println(findFirst.isPresent());
        System.out.println("");
        // 匹配任意(适用于并行流)
        Optional<Integer> finAny = list.parallelStream().filter(x -> x > 6).findAny();
        System.out.println(finAny.get());
        System.out.println(finAny.isPresent());
        System.out.println("");

        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x > 6);
        System.out.println(anyMatch);
    }

    // 筛选filter，筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作
    @Test
    void test9() {
        // 案例1：筛选出Integer集合中大于7的元素，并打印出来
        List<Integer> list = Arrays.asList(1,2,5,7,9,8,10);
        list.stream().filter(x -> x > 7).forEach(System.out::println);
     }

     // 案例二：筛选员工中工资高于8000的人，并形成新的集合，形成新集合依赖collect(收集)
    @Test
    void test10() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18,"male", "New York"));
        personList.add(new Person("Jack", 7000,18, "male", "Washington"));
        personList.add(new Person("Lily", 7800,18, "female", "Washington"));
        personList.add(new Person("Anni", 8200,18, "female", "New York"));
        personList.add(new Person("Owen", 9500,18, "male", "New York"));
        personList.add(new Person("Alisa", 7900,18, "female", "New York"));

        // filter筛选出大于8000的员工通过map进行映射出员工名称,collect形成新的集合,Collectors.toList()将流中的所有元素导出到一个列表(list)中
        List<String> fiterList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(fiterList);
        System.out.println("");
    }

    // 聚合 (max最大值,min最小值,count总数)
    @Test
    void test11() {
        // 案例1：获取String集合汇总最长的元素
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        // Comparator.comparing() 排序
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println(max.get());
    }

    // 案例2获取Integer集合中的最大值
    @Test
    void test12() {
        List<Integer> list = Arrays.asList(7,6,9,4,11,6);

        // 自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);

        // 自定义排序
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(max.get());
        System.out.println(max2.get());
    }

    // 案例三：获取员工工资最高的人
    @Test
    void test13() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println(max.get().getSalary());
    }

    // 计算Integer集合中大于6的元素的个数
    @Test
    void test14() {
        List<Integer> list = Arrays.asList(7,6,4,8,2,11,9);

        long count = list.stream().filter(x -> x > 6).count();
        System.out.println(count);
    }

    // 映射(map,flatMap) 映射可以将一个流的元素按照一定的映射规则映射到另一个流中，分别是map和flatMap
    // map: 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
    // flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
    @Test
    void test15() {
        // 英文字符串数组的元素全部改写为大写,整数数组每个元素+3
        String[] steArr = {"abcd","bcdd","defde","ftr"};
        // String中toUpperCase方法将字符串内容中的小写字符全部转化为大写字符，返回新字符串，原字符串不变
        List<String> strList = Arrays.stream(steArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(strList);

        List<Integer> integerList = Arrays.asList(1,3,5,7,9);
        List<Integer> intListNew = integerList.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println(intListNew);
    }

    // 案例二：将员工的薪资全部增加1000
    @Test
    void test16() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(),person.getSalary(),person.getAge(),person.getSex(),person.getArea());
            personNew.setSalary(person.getSalary() + 1000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前" + personList.get(0).getName() + "-->" + personList.get(0).getSalary() + personList.get(0).getName());
        personList.stream().forEach(x -> System.out.println(x.getSalary() + " " + x.getName() + x.getAge()));
        System.out.println("一次改动后" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary()+ personList.get(0).getName());
        personListNew.stream().forEach(x -> System.out.println(x.getSalary() + " " + x.getName()));

        // 改变原来员工集合的方式
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前" + personList.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println("二次改动后" + personListNew2.get(0).getName() + "-->" + personListNew2.get(0).getSalary());
    }

    // 案例三：将两个字符数组合并成一个新的字符数组
    @Test
    void test17() {
        List<String> list = Arrays.asList("m,k,l,a","1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] spilt = s.split(",");
            Arrays.stream(spilt).forEach(x -> System.out.println(x));
            Stream<String> s2 = Arrays.stream(spilt);
            return s2;
        }).collect(Collectors.toList());
        System.out.println("处理前的集合" + list);
        System.out.println("处理后的集合" + listNew);
    }

    // 规约(reduce) 规约也称缩减，是把一个流缩减成一个值，能实现对集合求和，求乘积和求最值操作
    // 求Integer集合的元素之和、乘积和最大值
    @Test
      void test18() {
        List<Integer> list = Arrays.asList(1,3,2,8,11,4);
        // 求和方式1
        Optional<Integer> sum = list.stream().reduce((x,y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream().reduce(0,Integer::sum);

        System.out.println("list求和" + sum.get() + "," + sum2.get() + "," + sum3);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x,y) -> x * y);
        System.out.println("list乘积" + product.get());

        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x,y) -> x > y ? x : y);

        // 求最大值写法2
        Integer max2 = list.stream().reduce(1,Integer::max);

        System.out.println("list求最大值" + max.get() + "," + max2);
    }

    // 案例二：求所有员工的工资之和和最高工资
    @Test
    void test19() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 求工资之和方式1
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        // 求工资之和方式2
        Integer sumSalary2 = personList.stream().reduce(0,(sum,p) -> sum += p.getSalary(),
                (sum1,sum2) -> sum1 + sum2);
        // 求工资之和方式3
        Integer sumSalary3 = personList.stream().reduce(0,(sum,p) -> sum += p.getSalary(),Integer::sum);
        System.out.println("工资之和" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);

        // 求最高工资方式1
        Integer maxSalary = personList.stream().reduce(0,(max,p) -> max > p.getSalary() ? max : p.getSalary(),Integer::max);
        // 求最高工资方式2
        Integer maxSalary2 = personList.stream().reduce(0,(max,p) -> max > p.getSalary() ? max : p.getSalary(),
                (max1,max2) -> max1 > max2 ? max1 : max2);
        System.out.println("求最高工资" + maxSalary + "," + maxSalary2);
    }

    // 收集(collect)收集就是把一个流收集起来，最终可以是收集成一个值也可以收集成一个新的集合
    // 归集(toList,toSet,toMap),因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据量重新归集到新的集合里。toList、toSet和toMap
    // 比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法
    @Test
    void test20() {
        List<Integer> list = Arrays.asList(1,6,3,4,6,7,9,6,20);
        // list可以重复
        List<Integer> listNew = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        // set不可以重复
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());
        System.out.println("list可以重复：" + listNew);
        System.out.println("set不可以重复：" + set);
        System.out.println(set);

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));

        Map<?,Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName,p -> p));
        System.out.println(map);
    }

    // 统计(count、averaging),collectors提供了一系列用于数据统计的静态方法
    // 计数：count
    // 平均值：averagingInt、averagingLong、averagingDouble
    // 最值：maxBy、minBy
    // 求和：summingInt、summingLong、summingDouble
    // 统计以上所有：summarizingInt、summarizingLong、summarizingDouble
    @Test
    void test21() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 求总数
        Long count = personList.stream().count();
        Long count1 = personList.stream().collect(Collectors.counting());
        System.out.println("求总数：" + count);
        System.out.println("求总数" + count1);
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println("求平均工资：" + average);

        // 求最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        Optional<Integer> max1 = personList.stream().map(Person::getSalary).max(Integer::compare);
        System.out.println("求最高工资：" + max.get());
        System.out.println("求最高工资：" + max1.get());

        // 求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        // 返回元素的和
        Integer sum1 = personList.stream().mapToInt(Person::getSalary).sum();
        System.out.println("求工资之和：" + sum);
        System.out.println("求工资之和：" + sum1);

        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("一次性统计所有信息：" + collect);
    }

    // 分组(partitioningBy、groupingBy)
    // 分区：将stream按条件分为两个map，比如员工按薪资是否高于8000分为两部分
    // 分组：将集合分为多个Map，比如员工按性别分组，有单级分组和多级分组
    @Test
    void test22() {
        // 案例：将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",  8900,18, "male", "New York"));
        personList.add(new Person("Jack", 7000,18, "male", "Washington"));
        personList.add(new Person("Lily",  7800,18, "female", "Washington"));
        personList.add(new Person("Anni",  8200,18, "female", "New York"));
        personList.add(new Person("Owen",  9500,18, "male", "New York"));
        personList.add(new Person("Alisa",  7900,18, "female", "New York"));

        // 按员工薪资是否高于8000分组

        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        System.out.println("员工按薪资是否大于8000分组情况：" + JSON.toJSONString(part));
        // 按员工性别分组
        Map<String,List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        System.out.println("员工按性别分组情况：" + JSON.toJSONString(group));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String,List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex,Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按性别、地区：" + JSON.toJSONString(group2));
    }

    // 接合(joining)可以将stream中的元素用特定的连接符(没有的话，则直接连接)连接成一个字符串
    @Test
    void test23() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A","B","C");
        String names2 = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + names2);
    }

    // 规约(reducing)，Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义规约的支持
    @Test
    void test24() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        // 每个员工减去起征点后的薪资之和(不严谨)
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        // 使用stream中reduce求员工扣税薪资总和
        Integer sum2 = personList.stream().map(Person::getSalary).reduce(0,(i,j) -> (i + j - 5000));
        System.out.println("员工扣税薪资总和：" + sum);
        System.out.println("员工扣税薪资总和：" + sum2);

        // 使用stream的reduce求员工的薪资总和
        Optional<Integer> sum3 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum3.get());
    }

    // 排序(sorted),有两种排序
    // sorted():自然排序，流中元素需实现Comparable接口
    // sorted(Comparator com):Comparator排序器自定义排序
    @Test
    void test25() {
        // 案例：将员工按工资由高到低(工资一样则按年龄由大到小)排序
        List<Person> personList = new ArrayList<Person>();

        personList.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList.add(new Person("Alisa", 9000, 26, "female", "New York"));

        // 按工资升序排序(自然排序)
        List<String> optionalPerson = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("按工资升序排序：" + optionalPerson);

        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资降序排序：" + newList2);

        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getSex)).map(Person::getName).collect(Collectors.toList());
        System.out.println("先按工资再按年龄升序排序：" + newList3);

        // 先按工资再按年龄自定义排序(降序)
        List<String> newList4 = personList.stream().sorted((p1,p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());

        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }
}
