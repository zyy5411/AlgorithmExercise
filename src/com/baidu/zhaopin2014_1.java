package com.baidu;

import util.Tools;

/**
 * 2013/9/28 百度，研发工程师
 * @author Administrator
 *
 */
public class zhaopin2014_1 {

	int getNextNoRepeat(int n) {
		int len = 0;
		int[] nums = new int[20];
		int tmp = n;
		while (0 != tmp) {
			nums[len++] = tmp % 10;
			tmp /= 10;
		}
		nums[len] = 0;
		boolean hasSame = false;
		int preNum = -1;
		for (int i = len - 1; i >= 0; i--) {
			if (!hasSame && preNum == nums[i]) {
				if (9 == preNum) {
					i += 2;
					nums[i] = 1;
					len = i == len ? len + 1 : len;
				} else {
					nums[i]++;
				}
				hasSame = true;
			} else if (hasSame) {
				nums[i] = nums[i + 1] > 0 ? 0 : 1;
			}
			preNum = nums[i];
		}
		Tools.print(nums);
		tmp = 0;
		for (int i = len - 1; i >= 0; i--) {
			tmp *= 10;
			tmp += nums[i];
		}

		return tmp;
	}

	public static void main(String[] args) {
		zhaopin2014_1 z = new zhaopin2014_1();
		System.out.println(z.getNextNoRepeat(100000));
	}

}
