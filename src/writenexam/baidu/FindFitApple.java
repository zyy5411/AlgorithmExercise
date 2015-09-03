package writenexam.baidu;

/**
 * 小杨拉来一车苹果进行包装，
 * 如果3个包一袋剩下2个， * 5个包一袋剩下3个，7个包一袋剩下2个，
 * 设计算法，求出N个符合条件的苹果个数。
 * */
public class FindFitApple {

	void printFitApples(int n){
		//设有N个苹果，A=N-2
		int A = 0;
		while(n >= 0){
			A += 21;
			if((A-1) % 10 == 0 || (A-1) % 10 == 5){
				System.out.println(A+2);
				n--;
			}
		}
	}
	
	//暴力算法
	void force(int n){
		int i = 10;
		while(n >= 0){
			if(i%3 == 2 && i%5 == 3 && i%7 == 2){
				System.out.println(i);
				n--;
			}
			i++;
		}
	}
	
	public static void main(String[] args) {
		new FindFitApple().printFitApples(15);
		new FindFitApple().force(15);
	}

}