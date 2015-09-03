package com.baidu;

import util.Tools;

/**
 * 2013/9/28 百度，研发工程师
 * @author Administrator
 *
 */
public class zhaopin2014_3 {

	int maxOverlapPoint(int[] a, int len) {
		for (int i = a.length - 1; i > 0; i--) {
			a[i] -= a[i - 1];
		}
		int start = 1, end = 1;
		int max = 0, sum = 0, count = 0;
		int time = 0;
		while (end < a.length && start <= end) {
			if (sum < len) {
				sum += a[end++];
				count++;
				max = count > max ? count : max;
			} else {
				sum -= a[start++];
				count--;
			}
			time++;
		}
		Tools.println("time:" + time);
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 5, 7, 8, 14, 17, 20 };
		zhaopin2014_3 z = new zhaopin2014_3();
		System.out.println(z.maxOverlapPoint(a, 8));
	}

}
