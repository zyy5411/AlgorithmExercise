package writenexam.baidu;

/**
 * С������һ��ƻ�����а�װ��
 * ���3����һ��ʣ��2���� * 5����һ��ʣ��3����7����һ��ʣ��2����
 * ����㷨�����N������������ƻ��������
 * */
public class FindFitApple {

	void printFitApples(int n){
		//����N��ƻ����A=N-2
		int A = 0;
		while(n >= 0){
			A += 21;
			if((A-1) % 10 == 0 || (A-1) % 10 == 5){
				System.out.println(A+2);
				n--;
			}
		}
	}
	
	//�����㷨
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