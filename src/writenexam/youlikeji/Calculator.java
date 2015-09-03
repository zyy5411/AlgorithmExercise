package writenexam.youlikeji;

import java.util.Stack;

import util.Tools;

/**
 * http://blog.csdn.net/yangzhanghui/article/details/6478871
(1):设置两个栈，一个运算符栈，一个操作数栈。初始化后将"#"压入操作符栈中。
(2):顺序扫描，当输入为操作数时就将其压入操作数栈。
(3):当输入为运算符时，则比较输入运算符和运算符栈的栈顶运算符的优先级的大小。若输入运算符的优先级高于运算符栈栈顶运算符的优先级时，则将其输入到运算符栈；若运算符栈栈顶运算符的优先级高于输入运算符的优先级，则将栈中的运算符弹出并从操作数栈中弹出两个操作数施以运算，将运算结果作为操作数输出到操作数栈；然后重新比较输入运算符和更新后的栈顶运算符的优先级的大小。
(4):当输入运算符为"("时，将"("直接入运算符栈。
(5):当输入运算符为")"时，将运算符栈栈顶运算符弹出并从操作数栈中弹出两个操作数施以运算，将运算结果作为操作数输出到操作数栈；重复此操作直至运算符栈的栈顶元素为"("，并将"("弹出并抛弃。
(6):当扫描到"#"时，说明算术表达式已经扫描完毕，运算符栈依次出栈并从操作数栈中弹出两个操作数施以运算，将运算结果作为新的操作数输出到操作数栈，直至运算符栈栈顶为"#"时结束。
(7):最后操作数栈的栈顶元素即为运算结果，将其输出到屏幕。
对于优先级的比较，如果是同种运算符，那么在栈内的运算符的优先级就比在栈外的运算符的优先级大一；不同级的运算符它们的优先级大小按原来的大小排列
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
				//'('则不断计算栈顶层2元素，直到遇到'('
				if(c == ')'){
					while(symbolStack.peek().charValue() != '('){
						calculateTopTwoNumber();
					}
					symbolStack.pop();
				}else if(c == '('){
					//')'则直接入栈
					symbolStack.push(c);
				}else {
					//遇到符号，则根据comparePrior，不断计算顶层元素，直到栈空或者遇到'('
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
