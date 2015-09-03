package writenexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Tools;

public class Question4 {

	int[] numbers;
	Set<Integer> set = new HashSet<Integer>();

	//找出一个序列，使得每一个数，都不为其他任何数之和
	int getTheNumbers(int n) {
		List<Integer> result = null;
		List<Integer> sums = null;
		for (int start = 1; start <= 10; start++) {
			//将不可能取的值置为false
			boolean[] arr = new boolean[10000];
			//存储获取的结果值
			result = new ArrayList<Integer>();
			//存储已有的所有数的和
			sums = new ArrayList<Integer>();
			Arrays.fill(arr, true);
			System.out.println("====from ====" + start);
			arr[start] = true;
			result.add(start);
			sums.add(start);
			while (result.size() < n) {
				int index = result.get(result.size() - 1);
				if (index > 500)
					break;
				while (true) {
					++index;
					if (arr[index]) {
						boolean ok = true;
						for (int s : sums) {
							if (!arr[s + index]) {
								ok = false;
								break;
							}
						}
						if (ok)
							break;
					}
				}
				result.add(index);
				sums.add(index);
				int size = sums.size() - 1;
				for (int i = 0; i < size; i++) {
					int s = sums.get(i);
					sums.add(s + index);
					arr[s + index] = false;
				}
				//				Tools.print(result, ",");
			}
			if (result.size() >= n && result.get(n - 1) <= 500)
				break;
		}
		Tools.print(result, ",");
		return 0;
	}

	void testIsLegal(int n, int[] arr) {
		numbers = arr;
		boolean[] weight = new boolean[n];
		Arrays.fill(weight, true);
		try {
			allsort(weight, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void allsort(boolean[] arr, int index) throws Exception {
		if (arr.length == index) {
			printSum(arr);
			return;
		}
		allsort(arr, index + 1);
		arr[index] = !arr[index];
		allsort(arr, index + 1);
		arr[index] = !arr[index];
	}

	void printSum(boolean[] arr) throws Exception {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] ? 1 : 0);
			sum += numbers[i] * (arr[i] ? 10 : 9);
		}
		System.out.println("  " + sum);
		if (set.contains(sum))
			throw new Exception("重复了");
		set.add(sum);
	}

	public static void main(String[] args) {
		int n = 10;
		//		int[] arr = { 3, 5, 6, 7 };
		int[] arr = { 261, 262, 263, 265, 268, 274, 285, 305, 345, 422 };
		//		int[] arr = { 1, 2, 4, 7 };
		Question4 c = new Question4();
		//		c.testIsLegal(n, arr);
		c.getTheNumbers(10);
	}

}
