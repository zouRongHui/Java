package org.rone.study.java.grammar.string;

/**
 * String类型数据的操作
 */
public class StringTest {
    public static void main(String[] args) {
        splitTest();
    }

    /**
     * String拆分成数组
     */
    public static void splitTest() {
        String str = "a.b.4x5.e";
//        str = "a b c d e f g";
        //这里的\\s是正则表达式，代表空白字符
        String[] strs = str.split("\\s");
        for (String temp : strs) {
            System.out.print(temp + "_");
        }
        System.out.println("************");
        //这里\\..表示.字符+任意字符
        strs = str.split("\\..");
        for (String temp : strs) {
            System.out.print(temp + "_");
        }
    }
}
