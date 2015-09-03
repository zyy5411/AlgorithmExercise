package zyy.codedojo;

/**
 * ��Ŀ��JAVA��ʦ������ש ���£�С���������װ�ޣ�������ǽש�Ľ׶Ρ�
 * һ�����۲���ã�������������ˮ����ʦ����ש���ַ��е����˼��ÿ��ǽ�����������¹̶���˳�� 1 2 6 3 5 7 4 8 9
 * �����ש�Ǳ߳�Ϊ1�������Σ���ʦ��Ҫ��һ��߳�Ϊ3��������ǽ��Ч��������ͼ��ʾ��
 * Ҫ�󣺼���ǽ���Ǳ߳�Ϊ10��������,��ש�ǳ�Ϊ1��������,������ʦ������n��שʱ,���������ĸ�λ�� ���룺ש���n �����i,j
 * (��i��,��j��,�����Ͻ�Ϊ0��0��) ���� ���룺 12 ����� 3,1 �������߼�չ���������Ͻ�Ϊ0��0�У�12���ڵ�3�У���1�е�λ���� 1��
 * 2�� 6�� 7��15, ... 3�� 5�� 8�� 14,... 4�� 9�� 13��... 10��12��... 11��... ...��
 * 
 * @author zyy
 *
 */
public class CalcPorcelain {
	static final int WIDTH = 8;// ǽ��ı߳�����1��ʼ

	// ʱ�临�Ӷ�ΪO(1)���㷨
	public static void algorithm1(int n) {
		int TOTAL_AMT = WIDTH * WIDTH;
		if (n > TOTAL_AMT || n <= 0) {
			System.out.println("invalid input!");
			return;
		}
		// ��n������������һ�루n��Ӧ��λ�������·��������򣩣�ʹ��maxNum - n�������
		int fixedN = (TOTAL_AMT < n * 2) && WIDTH > 1 ? TOTAL_AMT + 1 - n : n;
		// �ȼ����������һб��
		int order = (int) (Math.sqrt(2 * fixedN - 1.75) - 0.5);
		// �������б�е���Сֵ
		int min = order * (order + 1) / 2 + 1;
		// ���ݸ��е���ż���жϸ�б����Сֵ������л����ϲ���
		int isOdd = order & 0x0001;
		int dValue = fixedN - min;
		int[] position = null;
		// ��Сֵ�����
		if (isOdd == 0) {
			position = new int[] { order - dValue, dValue };
		} else {
			position = new int[] { dValue, order - dValue };
		}
		// n��Ӧ��λ�������·�������
		if (n > TOTAL_AMT / 2) {
			position[0] = WIDTH - position[0] - 1;
			position[1] = WIDTH - position[1] - 1;
		}
		System.out.println(position[0] + "," + position[1]);
	}

	// �ݹ�

	public static void main(String[] args) {
		CalcPorcelain.algorithm1(64);
	}

}
