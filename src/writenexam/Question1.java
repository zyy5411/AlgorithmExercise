package writenexam;

import java.util.Arrays;

import util.Tools;

/**
 * 一堆数字，找出三个数，使得两个数之和=第三个数。
 */
public class Question1 {

	int count = 0;

	//找到a<b<c && a+b=c
	void printIt(int[] arr) {
		int len = arr.length;
		if (len < 3)
			return;
		Arrays.sort(arr);
		int a, b, c;
		for (c = 2; c < len; c++) {
			a = 0;
			b = c - 1;
			while (a < b) {
				count++;
				if (arr[a] + arr[b] > arr[c]) {
					b--;
				} else if (arr[a] + arr[b] < arr[c]) {
					a++;
				} else {
					System.out.printf("%d+%d=%d\n", arr[a], arr[b], arr[c]);
					break;
				}
			}
		}
		Tools.println("totalCount:" + count);
		//		while (true) {

		//			if (arr[a] + arr[b] > arr[c]) {
		//				c++;
		//			} else if (arr[a] + arr[b] < arr[c]) {
		//				if (a < b - 1)
		//					a++;
		//				else if (b < c - 1)
		//					b++;
		//				else
		//					c++;
		//			} else {
		//				System.out.printf("%d+%d=%d", a, b, c);
		//				c++;
		//			}
		//			if (c >= len)
		//				break;
		//		}

	}

	public static void main(String[] args) {
		int[] arr = Tools.getRandomNumbersFromN(1, 10011000, 400);
		//		int[] arr = { 1, 3, 4, 6, 7, 8, 10, 12, 13, 15, 16 };
		new Question1().printIt(arr);
	}

}
