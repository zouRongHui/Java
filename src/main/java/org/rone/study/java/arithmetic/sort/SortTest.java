package org.rone.study.java.arithmetic.sort;

import java.util.Arrays;

/**
 * 排序算法
 */
public class SortTest {

	public static void main(String[] args) {
		int[] nums = {22,43,54,62,21,66,32,78,36,76,39};
//		int[] nums = {1,2,3,4,5,66,32,78,36,88,99};
//		nums = test0(nums);
//		nums = test1(nums);
//		nums = test2(nums);
		nums = test3(nums);
		for (int i : nums) {
			System.out.print(i + ",");
		}

	}

	/**
	 * 冒泡排序，一次比较自己和右边的数，将较大的数置于右边，第一次可将最大的数找出并置于数组最右边
	 */
	public static int[] test0(int[] nums) {
		int count = 0;
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < nums.length-i; j++) {
				if (nums[j] > nums[j+1]) {
					int n = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = n;
					count++;
				}
			}
		}
		System.out.println("冒泡排序交换数据次数：" + count);
		return nums;
	}

	/**
	 * 选择排序，一次比较左边的第一个数和右边的所有数，将最小的数移位到最左边，然后下一轮比较
	 */
	public static int[] test1(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length-1; i++) {
			int min_index = i;
			for (int j = i+1; j < nums.length; j++) {
				if (nums[min_index] > nums[j]) {
					min_index = j;
				}
			}
			if (nums[i] > nums[min_index]) {
				int n = nums[i];
				nums[i] = nums[min_index];
				nums[min_index] = n;
				count++;
			}
		}
		System.out.println("选择排序交换数据次数：" + count);
		return nums;
	}

	/**
	 * 插入排序，选中一个数，他的左边已经有序(已实现),依次和左边项去比较排序
	 */
	public static int[] test2(int[] nums) {
		int count = 0;
		for (int i = 1; i < nums.length; i++) {
			for (int j = i; j > 0; j--) {
				if (nums[j] > nums[j-1]) {
					break;
				}
				int n = nums[j];
				nums[j] = nums[j-1];
				nums[j-1] = n;
				count++;
			}
		}
		System.out.println("插入排序交换数据次数：" + count);
		return nums;
	}

	/**
	 * 希尔排序，插入排序的改进版，优先比较距离较远的元素，又叫缩小增量排序
	 * @param nums
	 * @return
	 */
	public static int[] test3(int[] nums) {
		int gap = 1;
		while (gap < nums.length/3) {
			gap = gap*3 + 1;
		}
		System.out.println("gap: " + gap);
		for (; gap > 0; gap = (int) Math.floor(gap/3)) {
			for (int i = gap; i < nums.length; i++) {
				int temp = nums[i];
				for (int j = i-gap; (j>=0) && (nums[j]>temp); j -= gap) {
					nums[j+gap] = nums[j];
					nums[j] = temp;
				}
			}
		}
		return nums;
	}

	public static void mergesort(int[] nums) {
		if (nums.length < 2) {
			return;
		}
		//移位运算符，右移一位表示除以2
		int halfIndex = nums.length >> 1;
		mergesort(Arrays.copyOfRange(nums, 0, halfIndex));
		mergesort(Arrays.copyOfRange(nums, halfIndex, nums.length));



	}

}