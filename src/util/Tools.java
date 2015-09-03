package util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/***
 * @data 9月8号
 * @author zyy
 */
public class Tools {
	public static void println(String s) {
		System.out.println(s);
	}

	public static void print(String s) {
		System.out.print(s);
	}

	public static void print(Object[] os) {
		for (Object o : os)
			System.out.println(o.toString());
	}

	public static void print(int[] os) {
		for (int o : os)
			System.out.print(" " + o);
		System.out.print("\n");
	}

	public static void print(int[][] os) {
		for (int[] arr : os) {
			for (int o : arr) {
				System.out.print(" " + o);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	public static void print(char[] os) {
		for (char o : os)
			System.out.printf("%c ", o);
		System.out.print("\n");
	}

	public static void print(int[] os, int start, int end) {
		for (int i = start; i < end; i++)
			System.out.print(" " + os[i]);
		System.out.print("\n");
	}

	public static void print(List<?> os, String split) {
		for (Object o : os)
			System.out.print(o.toString() + split);
		System.out.print("\n");
	}

	public static void print(List<?> os) {
		for (Object o : os)
			System.out.print(o.toString() + " ");
		System.out.print("\n");
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static int[] getRandomNumber(int n) {
		Random r = new Random();
		int[] arr = new int[n];
		while (--n >= 0) {
			arr[n] = r.nextInt();
		}
		return arr;
	}

	/**
	 * 从M-N中，取出k个不重复的随机数
	 * @param m,n 取值范围为[m,n]
	 * @return
	 */
	public static int[] getRandomNumbersFromN(int m, int n, int k) {
		int arr[] = new int[k];
		Random random = new Random();
		Set<Integer> set = new HashSet<Integer>();
		for (int i = n - k + 1; i <= n; i++) {
			int r = random.nextInt(i - m + 1) + m;
			if (set.contains(r))
				set.add(i);
			else
				set.add(r);
		}
		int index = 0;
		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {
			arr[index++] = iterator.next();
		}
		if (set.size() != k)
			System.err.println("k not enough!");
		return arr;
	}

	public static long getMilionTime() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		Tools.print(Tools.getRandomNumbersFromN(10, 100, 20));
	}
}
