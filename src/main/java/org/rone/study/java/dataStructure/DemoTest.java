package org.rone.study.java.dataStructure;

/**
 * 数据结构相关知识
 */
public class DemoTest {

    public static void main(String[] args) {
        test0();
    }

    /**
     * 判断单向链表是否存在环
     */
    public static void test0() {
        MyNote note1 = new MyNote("1");
        MyNote note2 = new MyNote("2");
        MyNote note3 = new MyNote("3");
        MyNote note4 = new MyNote("4");
        MyNote note5 = new MyNote("5");
        MyNote note6 = new MyNote("6");
        MyNote note7 = new MyNote("7");
        MyNote note8 = new MyNote("8");
        MyNote note9 = new MyNote("9");
        MyNote note10 = new MyNote("10");
        MyNote note11 = new MyNote("11");
        MyNote note12 = new MyNote("12");

        note1.next = note2;
        note2.next = note3;
        note3.next = note4;
        note4.next = note5;
        note5.next = note6;
        note6.next = note7;
        note7.next = note8;
        note8.next = note9;
        note9.next = note10;
        note10.next = note11;
        note11.next = note12;
        note12.next = note2;

        MyNote slow = note1;
        MyNote fast = note1;
        while (fast !=null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                System.out.println(slow);
                break;
            }//p与q相等，单链表有环
        }
        System.out.println("*********");
        if (fast != null && fast.next != null) {
            slow = note1;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            System.out.println(slow);
        }
    }
}

class MyNote {
    public String name;
    public MyNote next;

    public MyNote(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyNote{" +
                "name='" + name + '\'' +
                '}';
    }
}
