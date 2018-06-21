package org.rone.study.java.grammar.java8;

/**
 * Created by zouRongHui on 2018/6/15.
 * java8 lambda表达式
 */
public class LambdaTest {
    public static void main(String[] args) {
        String publicParam = "lambda";
        test(str -> System.out.println(str + publicParam));

        test((num, count) -> System.out.println(num + " , " + count));

        test((num, count) -> {
            num = num + 1;
            System.out.println(num * count);
        });


//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello thread...");
//            }
//        });
        Thread thread = new Thread(() -> System.out.println("hello lambda..."));
        thread.start();
    }

    public static void test(Demoable demo) {
        demo.doAction(3);
    }

    public static void test(Snowable snow) {
        snow.doAction(3, 5);
    }

    public interface Demoable {
        void doAction(Integer num);
    }

    public interface Snowable {
        void doAction(Integer num, Integer count);
    }
}
