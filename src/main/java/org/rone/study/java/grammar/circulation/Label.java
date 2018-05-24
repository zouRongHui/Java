package org.rone.study.java.grammar.circulation;

/**
 * 通过一个label来指定break/continue的范围，一般用在多重循环的转跳上。
 * 当需要转跳多重循环时使用 break label/continue label
 * label:
 * while() {
 *     while() {
 *         ...
 *         break label;//continue label;
 *         ...
 *     }
 * }
 * 其实一重循环的转跳也是如此
 */
public class Label {

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			label:
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					System.out.println(i + "," + j + "," + k);
					if (k == 1) {
						break label;
//						continue second;
					}
				}
			}
		}

	}

}
