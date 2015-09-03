package datastructure.sort;

import java.util.Arrays;
import java.util.Random;

public class SortAlgrithm {

	// 计数排序，只能是从0到某个数的值
	int[] countSort(int[] arr) {
		int len = arr.length;
		if (len == 0)
			return arr;
		int[] tmpArr = new int[len];
		int max = getMax(arr);
		int[] countArr = new int[max + 1];
		// 初始化为0
		Arrays.fill(countArr, 0);
		// 统计个数
		for (int n : arr) {
			countArr[n]++;
		}
		// 计算不大于该值的个数
		int count = 0;
		for (int i = 0; i < countArr.length; i++) {
			count += countArr[i];
			countArr[i] = count;
		}
		// 从后往前排序
		for (int i = len - 1; i >= 0; i--) {
			tmpArr[--countArr[arr[i]]] = arr[i];
		}

		return tmpArr;
	}

	int getMax(int[] arr) {
		if (arr == null || arr.length == 0) {
			return Integer.MIN_VALUE;
		}
		int max = arr[0];
		for (int n : arr) {
			if (max < n) {
				max = n;
			}
		}
		return max;
	}

	// 自己YY的冒泡排序
	public int[] bubbleSort(int[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		int len = arr.length;
		for (int i = 0; i < len - 1; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
		return arr;
	}

	public int[] bubbleSort1(int[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		int len = arr.length;
		int maxIndex;
		for (int i = 0; i < len - 1; i++) {
			maxIndex = 0;
			for (int j = 0; j < len - i; j++) {
				if (arr[j] > arr[maxIndex]) {
					maxIndex = j;
				}
			}
			swap(arr, len - i - 1, maxIndex);
		}
		return arr;
	}

	// 标准的冒泡排序
	int[] bubbleSortStandard(int[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		int len = arr.length;
		int lastSwapIndex = -1;
		int end = len - 1;
		while (lastSwapIndex != 0) {
			lastSwapIndex = 0;
			for (int j = 0; j < end; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
					lastSwapIndex = j;
				}
			}
			end = lastSwapIndex;
		}

		return arr;
	}

	// 鸡尾酒排序（双向的冒泡排序）
	int[] cockSort(int[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		int len = arr.length;
		int start = 0, end = len - 1;
		int swapIndex = 0;
		while (start < end) {
			for (int i = start; i < end; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
					swapIndex = i;
				}
			}
			end = swapIndex;
			for (int i = end; i > start; i--) {
				if (arr[i] < arr[i - 1]) {
					swap(arr, i, i - 1);
					swapIndex = i;
				}
			}
			start = swapIndex;
		}

		return arr;
	}

	int[] insertSort(int[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		int len = arr.length;

		for (int i = 1; i < len; i++) {
			int j = i;
			int tmp = arr[j];
			while (j > 0 && arr[j - 1] > tmp) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = tmp;
		}

		return arr;
	}

	int[] quickSort(int[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		int len = arr.length;
		quickSort(arr, 0, arr.length - 1);
		return arr;
	}

	void quickSort(int[] arr, int s, int e) {
		if (s >= e)
			return;
		int start = s, end = e;
		int tmp = arr[s];
		while (start < end) {
			while (start < end && arr[end] > tmp) {
				end--;
			}
			arr[start] = arr[end];
			while (start < end && arr[start] <= tmp) {
				start++;
			}
			arr[end] = arr[start];
		}
		arr[end] = tmp;
		quickSort(arr, s, end - 1);
		quickSort(arr, end + 1, e);
	}

	int[] heapSort(int[] arr) {
		// 数组后移一个
		int[] array = new int[arr.length + 1];
		array[0] = 0;
		for (int i = 1; i <= arr.length; i++) {
			array[i] = arr[i - 1];
		}
		// 建堆
		for (int i = (array.length - 1) / 2; i >= 1; i--) {
			makeBigDHeap(array, i, array.length - 1);
		}
		// 排序
		for (int i = array.length - 1; i > 1; i--) {
			swap(array, 1, i);
			makeBigDHeap(array, 1, i - 1);
		}
		return Arrays.copyOfRange(array, 1, array.length);
	}

	private void makeBigDHeap(int[] arr, int s, int e) {
		int tmp = arr[s];
		int larger = s * 2;
		while (larger <= e) {
			if (larger + 1 <= e && arr[larger] < arr[larger + 1])
				larger++;
			if (arr[larger] > tmp) {
				arr[s] = arr[larger];
				s = larger;
				larger = 2 * s;
			} else {
				break;
			}
		}
		arr[s] = tmp;
	}

	void swap(int[] arr, int i, int j) {
		if (i == j)
			return;
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	void print(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	void test(int[] arr) {
		print(arr);
		print(bubbleSort(arr));
		print(bubbleSort1(arr));
		print(insertSort(arr));
		print(quickSort(arr));
		print(countSort(arr));
		print(bubbleSortStandard(arr));
		print(cockSort(arr));

		// List<Integer> arrList = new ArrayList<Integer>();

	}

	void randomTest() {
		int[] array;
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			int n = r.nextInt(30);
			array = new int[n];
			while (n > 0) {
				array[array.length - n] = r.nextInt(100);
				n--;
			}
			System.out.println(Arrays.toString(array));
			isCorrect(bubbleSort(array));
			isCorrect(bubbleSort1(array));
			isCorrect(insertSort(array));
			isCorrect(quickSort(array));
			isCorrect(countSort(array));
			isCorrect(bubbleSortStandard(array));
			isCorrect(cockSort(array));
		}
		System.out.println("排序算法测试通过");
	}

	void isCorrect(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i - 1]) {
				try {
					throw new Exception("错误排序：" + Arrays.toString(arr));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		SortAlgrithm sortAlgrithm = new SortAlgrithm();
		int[] arr = { 6, 1, 3, 9, 5, 7, 4, 2, 6, 7, 3 };
		sortAlgrithm.test(arr);

		arr = new int[] { 1 };
		sortAlgrithm.test(arr);

		arr = new int[] {};
		sortAlgrithm.test(arr);

		sortAlgrithm.randomTest();

		arr = new int[] { 3, 5, 9, 7, 4, 6, 8, 10, 13 };
		sortAlgrithm.print(sortAlgrithm.heapSort(arr));

	}
}
