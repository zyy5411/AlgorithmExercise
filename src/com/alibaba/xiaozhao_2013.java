package com.alibaba;

import java.util.ArrayList;
import java.util.List;

import util.Tools;

/**
 * 给定一个升序的数组A，元素值两两不等，设计一高效算法找出所有的A[i]=i的下标。
 * @author Administrator
 *
 */
public class xiaozhao_2013 {

	int[] find(int[] arr) {
		int l = 0, u = arr.length - 1;
		List<Integer> list = new ArrayList<Integer>();
		while (l <= u) {
			int mid = (l + u) / 2;
			if (arr[mid] == mid) {
				int index = mid;
				while (index >= l && arr[index] == index) {
					list.add(index--);
				}
				index = mid + 1;
				while (index <= u && arr[index] == index) {
					list.add(index++);
				}
				break;
			} else if (arr[mid] < mid) {
				l = mid + 1;
			} else {
				u = mid - 1;
			}
		}

		int arrtmp[] = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arrtmp[i] = list.get(i);
		}
		return arrtmp;
	}

	public static void main(String[] args) {
		int[] arr = { -17, -16, -15, -14, -13, -12, -1, 0, 1, 9, 10, 15, 17,
				18, 19, 10, 11, 12, 13, 14, 16, 17, 18 };
		xiaozhao_2013 x = new xiaozhao_2013();
		Tools.print(x.find(arr));
	}

}
