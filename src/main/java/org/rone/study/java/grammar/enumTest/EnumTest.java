package org.rone.study.java.grammar.enumTest;

/**
 * 枚举类
 */
public class EnumTest {
	public static void main(String[] args) {
		Color.RED.test();
		Color.BLACK.test();
		for (Color c : Color.values()) {
			c.test();
		}
	}
}

enum Color {
	//在首行就必须列出所有的可能实例
	RED("red", "like fire"),
	BLACK("black", "like death");
	private String name;
	private String message;
	//构造器只能private
	private Color(String name, String message) {
		this.setName(name);
		this.setMessage(message);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void test() {
		System.out.println("name: " + this.name + ", message: " + this.message);
	}
}
