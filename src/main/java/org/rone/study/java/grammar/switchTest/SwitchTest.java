package org.rone.study.java.grammar.switchTest;

/**
 * switch的语法
 */
public class SwitchTest {

    public static void main(String[] args) {
        test0();
    }

    /**
     * 当case和default的顺序混乱时的运行情况
     */
    public static void test0() {
        String[] strs = new String[]{"x","y","c","z"};
        String word = "";
        for (String str : strs) {
            switch (str) {
                case "x" :
                    word += "x";
                case "y" :
                    word += "y";
                    break;
                default:
                    word += "#";
                case "z" :
                    word += "z";
            }
            System.out.println(word);
        }
        //输出 xyy#zz
        System.out.println(word);
    }
}
