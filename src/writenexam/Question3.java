package writenexam;

import util.Tools;

/**
 * ����������У�LIS����ʾ��һ�������У����ֵ�����������У����磺
	��2,1,4,2,3,7,4,6����LIS�ǣ�1,2,3,4,6������LIS��5��
	����һ����N��Ԫ�ص����У�������LIS��
 */
public class Question3 {

	//�Ե�K������β�����飬LIS�ĳ���
	int[] maxLens;
	//	int[] maxNums;
	int maxLen, maxNum;

	public void lis(float[] L) {
		int n = L.length;
		int[] f = new int[n];//���ڴ��f(i)ֵ��
		f[0] = 1;//�Ե�a1ΪĩԪ�ص�����������г���Ϊ1��
		for (int i = 1; i < n; i++)//ѭ��n-1��
		{
			f[i] = 1;//f[i]����СֵΪ1��
			for (int j = 0; j < i; j++)//ѭ��i ��
			{
				if (L[j] < L[i] && f[j] > f[i] - 1)
					f[i] = f[j] + 1;//����f[i]��ֵ��
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

	//��[0��k]�У��ҵ���β��nС�������������
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
