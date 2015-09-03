package com.alibaba;

public class TestConstructOrder {

	public static int k = 0;
	public static int k1;
	public int k2;
	public static TestConstructOrder t1 = new TestConstructOrder("t1");
	public static TestConstructOrder t2 = new TestConstructOrder("t2");
	public static int i = print("i");
	public static int n = 99;
	public int j = print("j");
	{
		print("¹¹Ôì¿é");
	}
	static {
		print("¾²Ì¬¿é");
	}

	public TestConstructOrder(String str) {
		System.out.println((++k) + ":" + str + "	i=" + i + "	n=" + n);
		++i;
		++n;
	}

	public static int print(String str) {
		System.out.println((++k) + ":" + str + "	i=" + i + "	n=" + n);
		++n;
		return ++i;
	}

	/**
	 * 
	 */
	public static void main(String[] args) {
		TestConstructOrder t = new TestConstructOrder("init");
	}

}
