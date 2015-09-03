package writenexam.youlikeji;

import java.util.Stack;

import util.Tools;

/**
 * http://blog.csdn.net/yangzhanghui/article/details/6478871
(1):��������ջ��һ�������ջ��һ��������ջ����ʼ����"#"ѹ�������ջ�С�
(2):˳��ɨ�裬������Ϊ������ʱ�ͽ���ѹ�������ջ��
(3):������Ϊ�����ʱ����Ƚ�����������������ջ��ջ������������ȼ��Ĵ�С������������������ȼ����������ջջ������������ȼ�ʱ���������뵽�����ջ���������ջջ������������ȼ�������������������ȼ�����ջ�е�������������Ӳ�����ջ�е�������������ʩ�����㣬����������Ϊ�����������������ջ��Ȼ�����±Ƚ�����������͸��º��ջ������������ȼ��Ĵ�С��
(4):�����������Ϊ"("ʱ����"("ֱ���������ջ��
(5):�����������Ϊ")"ʱ���������ջջ��������������Ӳ�����ջ�е�������������ʩ�����㣬����������Ϊ�����������������ջ���ظ��˲���ֱ�������ջ��ջ��Ԫ��Ϊ"("������"("������������
(6):��ɨ�赽"#"ʱ��˵���������ʽ�Ѿ�ɨ����ϣ������ջ���γ�ջ���Ӳ�����ջ�е�������������ʩ�����㣬����������Ϊ�µĲ����������������ջ��ֱ�������ջջ��Ϊ"#"ʱ������
(7):��������ջ��ջ��Ԫ�ؼ�Ϊ�������������������Ļ��
�������ȼ��ıȽϣ������ͬ�����������ô��ջ�ڵ�����������ȼ��ͱ���ջ�������������ȼ���һ����ͬ������������ǵ����ȼ���С��ԭ���Ĵ�С����
 * */
public class Calculator {

	String expresion;

	Stack<Integer> numberStack = new Stack<Integer>();

	Stack<Character> symbolStack = new Stack<Character>();

	public int getResult(String expresion){
		Parser parser = new Parser(expresion);
		while(parser.hasNext()){
			Token token = parser.getNext();
			if(token.isNumber()){
				numberStack.push(token.getInt());
			}else if(token.isSymbol()){
				char c = token.getChar();
				//'('�򲻶ϼ���ջ����2Ԫ�أ�ֱ������'('
				if(c == ')'){
					while(symbolStack.peek().charValue() != '('){
						calculateTopTwoNumber();
					}
					symbolStack.pop();
				}else if(c == '('){
					//')'��ֱ����ջ
					symbolStack.push(c);
				}else {
					//�������ţ������comparePrior�����ϼ��㶥��Ԫ�أ�ֱ��ջ�ջ�������'('
						while(!symbolStack.isEmpty() && 
								symbolStack.peek().charValue() != '(' ){
							if(comparePrior(c, symbolStack.peek().charValue()) <= 0)
								calculateTopTwoNumber();
							else
								break;
						}
					symbolStack.push(c);
				}
			}
		}
		while(!symbolStack.isEmpty())
			calculateTopTwoNumber();
		
		return numberStack.peek().intValue();
	}

	int calculateTopTwoNumber() {
		int n2 = numberStack.pop();
		int n1 = numberStack.pop();
		int n = 0;
		char ope = symbolStack.pop();
		switch (ope) {
		case '+':
			n = n1 + n2;
			break;
		case '-':
			n = n1 - n2;
			break;
		case '*':
			n = n1 * n2;
			break;
		case '/':
			n = n1 / n2;
			break;
		}
		numberStack.push(n);
		return n;
	}

	int comparePrior(char c1, char c2) {
		return getLevel(c1) - getLevel(c2);
	}

	int getLevel(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		default:
			return 2;
		}
	}

	class Parser {
		String exp;
		int index;

		public Parser(String exp) {
			this.exp = exp;
			this.index = 0;
		}

		public Token getNext() {
			if (!hasNext())
				return null;
			char c = exp.charAt(index);
			switch (c) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '(':
			case ')':
				index++;
				return new Symbol(c);
			}
			int number = 0;
			while (c >= '0' && c <= '9') {
				number = 10 * number + c - '0';
				index++;
				if (!hasNext())
					break;
				c = exp.charAt(index);
			}
			return new Number(number);
		}

		public boolean hasNext() {
			if (index >= exp.length())
				return false;
			return true;
		}
	}

	class Token {
		boolean isNumber() {
			return false;
		}

		boolean isSymbol() {
			return false;
		}

		int getInt() {
			return 0;
		}

		char getChar() {
			return '0';
		}

	}

	class Number extends Token {
		int number;

		public Number(int number) {
			this.number = number;
		}

		@Override
		boolean isNumber() {
			return true;
		}

		@Override
		int getInt() {
			return number;
		}

		@Override
		public String toString() {
			return " " + number;
		}
	}

	class Symbol extends Token {
		char symbol;

		public Symbol(char symbol) {
			this.symbol = symbol;
		}

		@Override
		boolean isSymbol() {
			return true;
		}

		@Override
		char getChar() {
			return symbol;
		}

		@Override
		public String toString() {
			return " " + symbol;
		}
	}

	public static void main(String[] args) {
		String expresion = "(3+2*(1+2))*4-(2+3)";
		System.out.println("result:"+new Calculator().getResult(expresion));

	}

}
