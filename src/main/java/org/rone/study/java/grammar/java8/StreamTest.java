package org.rone.study.java.grammar.java8;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by zouRongHui on 2018/6/13.
 * java8 的Stream API
 */
public class StreamTest {
    public static void main(String[] args) throws InterruptedException {
//        demoTest();
//        testOptional();
//        testLimitAndSkill();
//        testMap();
//        testFilter();
//        testForEach();
//        testFindFirst();
//        testReduce();
//        testLimitAndSkip();
//        testSort();
//        testMaxAndMinAndDistinct();
        testMatch();
    }

    public static void test() {
        //构造流的几种常见方法
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        //流转换为其它数据结构
        // 1. Array
        String[] strArray1 = (String[]) stream.toArray(String[]::new);
        // 2. Collection
        List<String> list1 = (List<String>) stream.collect(Collectors.toList());
        List<String> list2 = (List<String>) stream.collect(Collectors.toCollection(ArrayList::new));
        Set set1 = (Set) stream.collect(Collectors.toSet());
        Stack stack1 = (Stack) stream.collect(Collectors.toCollection(Stack::new));
        // 3. String
        String str = stream.collect(Collectors.joining()).toString();
    }

    public static void testMap() {
        //转成大写
        List<String> wordList = Arrays.asList("Java", "C", "C++", "JavaScript", "Python");
        List<String> output = wordList.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());
        System.out.println(output);

        //平方数
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());
        System.out.println(squareNums);

        //flatMap,一对多
        //flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，
        // 最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        System.out.println(outputStream.collect(Collectors.toList()));
    }

    public static void testFilter() {
        //过滤掉非偶数
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        Arrays.stream(evens).forEach(System.out::print);
    }

    public static void testForEach() {
        //打印内容
        Stream.of("one", "two", "three", "four").forEach(System.out::println);

        //peek 操作也具有相似功能，只是 peek 是 Intermediate 操作，而 forEach 是 Terminal 操作
        List<String> list = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    public static void testFindFirst() {
        System.out.println(Stream.of("one", "two", "three", "four").findFirst());
    }

    public static void testReduce() {
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("concat = " + concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("minValue = " + minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println("sumValue = " + sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("sumValue = " + sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println("concat = " + concat);
    }

    public static void testLimitAndSkip() {
        System.out.println(Stream.of("one", "two", "three", "four", "five").limit(3).collect(Collectors.toList()));
        System.out.println(Stream.of("one", "two", "three", "four", "five").skip(3).collect(Collectors.toList()));
    }

    public static void testSort() {
        System.out.println(Stream.of("one", "two", "three", "four").sorted().collect(Collectors.toList()));
        System.out.println(Stream.of(35, 43, 2, 103).sorted((p1, p2) -> p1.compareTo(p2)).collect(Collectors.toList()));

        List<Person> persons = new ArrayList();
        persons.add(new Person(4, "java"));
        persons.add(new Person(3, "c"));
        persons.add(new Person(6, "php"));
        persons.add(new Person(0, "c++"));
        persons.add(new Person(1, "object-c"));
        persons.add(new Person(9, "python"));

        persons = persons.stream().sorted((p1, p2) -> p1.getNo().compareTo(p2.getNo())).collect(Collectors.toList());
        System.out.println(persons);
        persons = persons.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
        System.out.println(persons);
    }

    public static void testMaxAndMinAndDistinct() {
        System.out.println(IntStream.of(12, 3, 4, 32, 98, 3).max().getAsInt());
        System.out.println(IntStream.of(12, 3, 4, 32, 98, 3).min().getAsInt());
        System.out.println(Stream.of("java", "c", "php", "java", "c++", "c#").distinct().collect(Collectors.toList()));
    }

    public static void testMatch() {
        System.out.println(Stream.of("java", "c", "c++").allMatch(p -> p.length() > 1));
        System.out.println(Stream.of("java", "c", "c++").anyMatch(p -> p.equals("c")));
        System.out.println(Stream.of("java", "c", "c++").noneMatch(p -> p.equals("php")));
    }

    public static class Person {
        public Integer no;
        private String name;
        public Person (Integer no, String name) {
            this.no = no;
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public Integer getNo() {
            return no;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
