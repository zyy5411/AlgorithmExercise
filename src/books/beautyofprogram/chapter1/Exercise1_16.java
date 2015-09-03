package books.beautyofprogram.chapter1;

import java.util.List;

public class Exercise1_16 {

	Union[] unions;

	void printExp(int[] arr) {

		for (int i = 0; i <= 3; i++) {
			unions[i * 4] = new Union(arr[i]);
		}
		for (int i = 0; i < 16; i++) {
			calculate(i);
		}
	}

	int calculate(int i) {
		// int j = 15 ^ i;
		if (null != unions[i])
			return i;
		unions[i] = new Union();

		for (int j = 1; j < i; j++) {
			if ((j & i) == j) {
				folk(calculate(i - j), calculate(j));
			}
		}

		return i;
	}

	// 将两个量所有的值进行计算
	void folk(int i1, int i2) {
		Union u1 = unions[i1], u2 = unions[i2];
		for (Expression es1 : u1.exps) {
			for (Expression es2 : u2.exps) {
				// 计算表达式的值，存入总单元中
				unions[i1 + i2].cal(es1, es2);
			}
		}

	}

	public static void main(String[] args) {
		int[] arr = { 11, 8, 3, 5 };
		new Exercise1_16().printExp(arr);

	}

	class Union {
		int id;
		// Expression[] exps;
		List<Expression> exps;

		public Union() {

		}

		public Union(int i) {
			// exp = String.format("%d", i);
		}

		void cal(Expression e1, Expression e2) {
			Expression e = new Expression();
			e.add(e1, e2);
			e = new Expression();
			e = new Expression();
			e.sub(e1, e2);
		}

		int getResult() {
			return 0;
		}

		// String getExpresionString() {
		// return null;
		// }
	}

	class Expression {
		int value;
		String expStr;

		void add(Expression e1, Expression e2) {
			expStr = e1.expStr + "+" + e2.expStr;

		}

		void sub(Expression e1, Expression e2) {
			expStr = e1.expStr + "-" + e2.expStr;

		}
	}
}
