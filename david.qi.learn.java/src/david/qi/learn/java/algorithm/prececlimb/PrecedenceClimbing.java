package david.qi.learn.java.algorithm.prececlimb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrecedenceClimbing {
	public enum TokenType 		{BINOP, NUMBER, LEFTPAR, RIGHTPAR}
	public enum Associativity 	{LEFT, RIGHT}
	public enum Precedence		{ONE(1), TWO(2), THREE(3);
		final int value;
		private Precedence(int prece) {
			this.value = prece;
		}
	};
	public enum Operation {
		PLUS		("+",		Associativity.LEFT, Precedence.ONE)			{int apply(int x, int y) {return x + y;}},
		MINUS	("-", 		Associativity.LEFT, Precedence.ONE) 			{int apply(int x, int y) {return x - y;}},
		TIMES		("*", 		Associativity.LEFT, Precedence.TWO) 			{int apply(int x, int y) {return x * y;}},
		DIVIDE	("/", 		Associativity.LEFT, Precedence.TWO) 			{int apply(int x, int y) {return x / y;}},
		POWER	("^",		Associativity.RIGHT,Precedence.THREE)		{int apply(int x, int y){return (int)Math.pow(x, y);}};	

		public final String value;
		public final Associativity asso;
		public final Precedence precedence;
		Operation(String value, Associativity asso, Precedence prec){
			this.value = value;
			this.asso = asso;
			this.precedence = prec;
		}
		abstract int apply(int x, int y);		
	}
	static final String PATTERN = "\\s*(\\d+)|(\\S)";

	public static void main(String[] args) {
		String expr = "1-(2+3)*4/5^6";
		System.out.println(compute_expr(parse(expr), 1));		
	}

	/**
	 * @param expr: the expression of formula by string
	 * @return Tokenizer: a list of parsed tokens of the formula, with a index to current token
	 */
	public static Tokenizer parse(String expr) {
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(expr);
		List<Token> tokens = new ArrayList<Token>();
		while (m.find()) {
			String number = m.group(1);
			String item = m.group(2);
			Token n;
			if (number != null) {
				n = new Token(number, TokenType.NUMBER, null);
			} else if (item != null && item.equals("(")) {
				n = new Token(item, TokenType.LEFTPAR,null);
			} else if (item != null && item.equals(")")) {
				n = new Token(item, TokenType.RIGHTPAR,null);
			} else if (item != null) {
				if(item.equals(Operation.PLUS.value)) {
					n = new Token(item, TokenType.BINOP, Operation.PLUS);
				}else if(item.equals(Operation.MINUS.value)) {
					n = new Token(item, TokenType.BINOP, Operation.MINUS);
				}else if(item.equals(Operation.TIMES.value)) {
					n = new Token(item, TokenType.BINOP, Operation.TIMES);
				}else if(item.equals(Operation.DIVIDE.value)) {
					n = new Token(item, TokenType.BINOP, Operation.DIVIDE);
				}else if(item.equals(Operation.POWER.value)) {
					n = new Token(item, TokenType.BINOP, Operation.POWER);
				}else {
					throw new RuntimeException("Unexcepted operation :  " + item);
				}
			}else {
				throw new IllegalArgumentException("Wrong expr argument: " + expr);
			}
			tokens.add(n);
		}
		return new Tokenizer(tokens);
	}
	
	/**
	 * Compute the value of atom, atom means number or a sub-expression covered by "(" and ")"
	 * @param t: the tokens of the formula
	 * @return int: result
	 */
	private static int compute_atom(Tokenizer t) {
		TokenType type = t.getCurrent().type;
		if(type == TokenType.NUMBER) {
			String value = t.getCurrent().value;
			if(value == null) throw new IllegalArgumentException("Unexcepted end of expr!" + t);
			return Integer.valueOf(value);
		}else if(type == TokenType.LEFTPAR) {
			t.next();			
			int result = compute_expr(t,1);
			if(t.getCurrent().type != TokenType.RIGHTPAR) throw new IllegalArgumentException("unmatched \"(\" :  " + t);
			return result;
		}else {
			throw new IllegalArgumentException("Except an atom, but an operation : " + t);			
		}
	}
	
	/**
	 * Calculate the value of expression
	 * @param t: the tokens of the formula
	 * @param minPrece: the minimal precedence of current token
	 * @return int: the computing result of the expression
	 */
	public static int compute_expr(Tokenizer t, int minPrece) {
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

	public static class Tokenizer{
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
		
		@Override public String toString() {
			StringBuilder sb = new StringBuilder();
			for(Token t : tokens) {
				sb.append(" ").append(t.toString());
			}
			return sb.toString();
		}
	}
	
	public static class Token {
		final String value;
		final TokenType type;
		final Operation operation;

		public Token(String value, TokenType type, Operation oper) {
			this.value = value;
			this.type = type;
			this.operation = oper;
		}
		
		@Override public String toString() {
			return this.value;
		}
	}	
}
