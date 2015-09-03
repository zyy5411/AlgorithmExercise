package zyy.codedojo;

/**
 * 题目：JAVA陶师傅贴瓷砖 故事：小明家最近在装修，到了贴墙砖的阶段。
 * 一日他观察许久，发现请来的泥水工陶师傅贴砖的手法有点儿意思，每面墙基本都是以下固定的顺序： 1 2 6 3 5 7 4 8 9
 * 假设瓷砖是边长为1的正方形，陶师傅要贴一面边长为3的正方形墙，效果即如上图所示。
 * 要求：假设墙面是边长为10的正方形,瓷砖是长为1的正方形,试问陶师傅贴第n块砖时,他会贴在哪个位置 输入：砖块号n 输出：i,j
 * (第i行,第j列,以左上角为0行0列) 例： 输入： 12 输出： 3,1 （根据逻辑展开后，以左上角为0行0列，12即在第3行，第1列的位置上 1，
 * 2， 6， 7，15, ... 3， 5， 8， 14,... 4， 9， 13，... 10，12，... 11，... ...）
 * 
 * @author zyy
 *
 */
public class CalcPorcelain {
	static final int WIDTH = 8;// 墙面的边长，从1开始

	// 时间复杂度为O(1)的算法
	public static void algorithm1(int n) {
		int TOTAL_AMT = WIDTH * WIDTH;
		if (n > TOTAL_AMT || n <= 0) {
			System.out.println("invalid input!");
			return;
		}
		// 若n大于总数量的一半（n对应的位置在右下方三角区域），使用maxNum - n替代计算
		int fixedN = (TOTAL_AMT < n * 2) && WIDTH > 1 ? TOTAL_AMT + 1 - n : n;
		// 先计算出属于哪一斜列
		int order = (int) (Math.sqrt(2 * fixedN - 1.75) - 0.5);
		// 计算出该斜列的最小值
		int min = order * (order + 1) / 2 + 1;
		// 根据该列的奇偶性判断该斜列最小值在左侧列还是上侧行
		int isOdd = order & 0x0001;
		int dValue = fixedN - min;
		int[] position = null;
		// 最小值在左侧
		if (isOdd == 0) {
			position = new int[] { order - dValue, dValue };
		} else {
			position = new int[] { dValue, order - dValue };
		}
		// n对应的位置在右下方三角区
		if (n > TOTAL_AMT / 2) {
			position[0] = WIDTH - position[0] - 1;
			position[1] = WIDTH - position[1] - 1;
		}
		System.out.println(position[0] + "," + position[1]);
	}

	// 递归

	public static void main(String[] args) {
		CalcPorcelain.algorithm1(64);
	}

}
