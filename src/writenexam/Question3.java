package writenexam;

import util.Tools;

/**
 * 最长递增子序列（LIS）表示在一个序列中，保持递增的最长子序列，比如：
	（2,1,4,2,3,7,4,6）的LIS是（1,2,3,4,6），则LIS是5，
	对于一个有N个元素的序列，求它的LIS。
 */
public class Question3 {

	//以第K个数结尾的数组，LIS的长度
	int[] maxLens;
	//	int[] maxNums;
	int maxLen, maxNum;

	public void lis(float[] L) {
		int n = L.length;
		int[] f = new int[n];//用于存放f(i)值；
		f[0] = 1;//以第a1为末元素的最长递增子序列长度为1；
		for (int i = 1; i < n; i++)//循环n-1次
		{
			f[i] = 1;//f[i]的最小值为1；
			for (int j = 0; j < i; j++)//循环i 次
			{
				if (L[j] < L[i] && f[j] > f[i] - 1)
					f[i] = f[j] + 1;//更新f[i]的值。
			}
			Tools.print(f);
		}
		System.out.println(f[n - 1]);

	}

	int getLIS(int[] a) {
		maxLens = new int[a.length];
		//		maxNums = new int[a.length];
		maxLens[0] = 1;
		//		maxNum = maxNums[0] = a[1];
		//		maxNum = a[0];
		//		Arrays.fill(paramArrayOfBoolean, paramBoolean)
		//		return LIS(a, 0, a.length - 1);
		for (int k = 1; k < a.length; k++) {
			maxLens[k] = 1;
			for (int j = 0; j < k; j++) {
				if (a[j] < a[k] && maxLens[j] >= maxLens[k]) {
					maxLens[k] = maxLens[j] + 1;
				}
			}

			//			if (a[k] < maxNum) {
			//				int maxLenLowerThanN = maxLenLowerThanN(a, k - 1, a[k]);
			//				maxLens[k] = maxLenLowerThanN + 1;
			//				if (maxLens[k] == maxLen && a[k] < maxNum) {
			//					maxNum = a[k];
			//				}
			//			} else if (a[k] > maxNum) {
			//				maxNum = a[k];
			//				maxLens[k] = ++maxLen;
			//			} else {
			//				maxLens[k] = maxLen;
			//			}
			Tools.print(maxLens);
		}
		return maxLen;
	}

	//在[0，k]中，找到结尾比n小的最长递增子数组
	int maxLenLowerThanN(int[] a, int k, int n) {
		int maxL = 0;
		for (int i = k; i >= 0; i--) {
			if (maxLens[i] > maxL && a[i] < n) {
				maxL = maxLens[i];
			}
		}
		return maxL;
	}

	//	int LIS(int[] arr, int index, int end) {
	//
	//	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 4, 2, 3, 7, 4, 6 };
		int[] a2 = { 3, 2, 1, 2, 1, 0, 3, 2 };
		int[] a3 = { 1, 3, 5, 0, 2, 4, 5 };
		Question3 q = new Question3();
		System.out.println(q.getLIS(a3));
		q.getLIS(a3);
	}
}
