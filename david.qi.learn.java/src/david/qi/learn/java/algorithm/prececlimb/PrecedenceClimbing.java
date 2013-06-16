package david.qi.learn.java.algorithm.prececlimb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrecedenceClimbing {
	enum TokenType {BINOP, NUMBER, LEFTPAR, RIGHTPAR}
	enum Associativity {LEFT, RIGHT}
	enum Precedence{ONE(1), TWO(2), THREE(3);
		final int value;
		private Precedence(int prece) {
			this.value = prece;
		}
	};
	enum Operation {
		PLUS		("+",		Associativity.LEFT, Precedence.ONE)			{int apply(int x, int y) {return x + y;}},
		MINUS	("-", 		Associativity.LEFT, Precedence.ONE) 			{int apply(int x, int y) {return x - y;}},
		TIMES		("*", 		Associativity.LEFT, Precedence.TWO) 			{int apply(int x, int y) {return x * y;}},
		DIVIDE	("/", 		Associativity.LEFT, Precedence.TWO) 			{int apply(int x, int y) {return x / y;}},
		POWER	("^",		Associativity.RIGHT,Precedence.THREE)		{int apply(int x, int y){return (int)Math.pow(x, y);}};	

		final String value;
		final Associativity asso;
		final Precedence precedence;
		Operation(String value, Associativity asso, Precedence prec){
			this.value = value;
			this.asso = asso;
			this.precedence = prec;
		}
		abstract int apply(int x, int y);		
	}
	static final String PATTERN = "\\s*(\\d+)|(\\S)";

	public static void main(String[] args) {
		String expr = "2 + 3 ^ 4";
		Tokenizer t = parse(expr);
		System.out.println(compute_expr(t, 1));
		
	}

	private static Tokenizer parse(String expr) {
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(expr);
		List<Token> tokens = new ArrayList<Token>();
		while (m.find()) {
			String number = m.group(1);
			String item = m.group(2);
			if (number != null) {
				Token n = new Token(number, TokenType.NUMBER);
				tokens.add(n);
			} else if (item != null && item.equals("(")) {
				Token n = new Token(item, TokenType.LEFTPAR);
				tokens.add(n);
			} else if (item != null && item.equals(")")) {
				Token n = new Token(item, TokenType.RIGHTPAR);
				tokens.add(n);
			} else if (item != null) {
				Token n = new Token(item, TokenType.BINOP);
				if(item.equals(Operation.PLUS.value)) {
					n.operation = Operation.PLUS;
				}else if(item.equals(Operation.MINUS.value)) {
					n.operation = Operation.MINUS;
				}else if(item.equals(Operation.TIMES.value)) {
					n.operation = Operation.TIMES;
				}else if(item.equals(Operation.DIVIDE.value)) {
					n.operation = Operation.DIVIDE;
				}else if(item.equals(Operation.POWER.value)) {
					n.operation = Operation.POWER;
				}else {
					throw new RuntimeException("Unexcepted item :  " + item);
				}
				tokens.add(n);
			}else {
				throw new IllegalArgumentException("Wrong expr argument: " + expr);
			}
		}
		return new Tokenizer(tokens);
	}
	
	private static int compute_atom(Tokenizer t) {
		String value = t.getCurrent().value;
		if(value == null) throw new IllegalArgumentException("Unexcepted end of expr!"); 
		return Integer.valueOf(value);
	}
	
	private static int compute_expr(Tokenizer t, int minPrece) {
		int result = compute_atom(t);
		t.next();
		while(true) {
			Token curToken = t.getCurrent();
			if(curToken ==null || curToken.type != TokenType.BINOP || curToken.operation.precedence.value < minPrece) {
				break;
			}else {
				Operation oper = curToken.operation;
				int prece = oper.precedence.value;
				int nextPrece = oper.asso == Associativity.LEFT? prece + 1 : prece;
				t.next();
				int rhs = compute_expr(t, nextPrece);
				result = oper.apply(result, rhs);
			}
		}
		return result;
		
	}

	private static class Tokenizer{
		private final List<Token> tokens;
		private Token curToken;
		private int index = 0;
		Tokenizer(List<Token> tokens){
			this.tokens = tokens;
			this.curToken = tokens.get(index);
		}
		Token getCurrent() {
			return curToken;
		}
		void next() {
			if(index >= tokens.size()-1) {
				this.curToken = null; //End of the expression
			}else {
				this.curToken = tokens.get(++index);
			}
		}
	}
	
	private static class Token {
		final String value;
		final TokenType type;
		Operation operation;

		Token(String value, TokenType type) {
			this.value = value;
			this.type = type;
		}
		
		public String toString() {
			return this.type + " : " + this.value;
		}
	}
	
	
	
}
