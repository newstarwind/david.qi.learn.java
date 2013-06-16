package david.qi.learn.java.effectivejava.enumstudy;

public class EnumStudy {
	
	interface Operation{
		int apply(int x, int y);
	}
	enum Associativity{LEFT, RIGHT} ;
	enum Precedence{
		ONE(1), 
		TWO(2), 
		THREE(3);
		final int value;
		private Precedence(int prece) {
			this.value = prece;
		}
	};
	enum OldOperation implements Operation {
		PLUS		(Associativity.LEFT, Precedence.ONE)			{public int apply(int x, int y) {return x + y;}},
		MINUS	(Associativity.LEFT, Precedence.ONE) 			{public int apply(int x, int y) {return x - y;}},
		TIMES		(Associativity.LEFT, Precedence.TWO) 		{public int apply(int x, int y) {return x * y;}},
		DIVID		(Associativity.LEFT, Precedence.TWO) 		{public int apply(int x, int y) {return x / y;}};

		final Associativity asso;
		final Precedence precedence;
		OldOperation(Associativity asso, Precedence prec){
			this.asso = asso;
			this.precedence = prec;
		}
	}
	
	enum ExtendedOperation implements Operation{
		EXP		(Associativity.RIGHT,Precedence.THREE)			{
			public int apply(int x, int y) {
				return (int)Math.pow(x,y);
			}
		},
		REMAINDER	(Associativity.RIGHT,Precedence.THREE){
			public int apply(int x, int y) {
				return x % y;				
			}	
		};
		final Associativity asso;
		final Precedence precedence;
		ExtendedOperation(Associativity asso, Precedence prec){
			this.asso = asso;
			this.precedence = prec;
		}
	}
	
	public static void main(String[] args) {
	    for (OldOperation o : OldOperation.values()) {
	    	System.out.println(o.precedence.value);
	    }
	    for(ExtendedOperation o : ExtendedOperation.values()) {
	    	System.out.println(o.precedence.value);
	    }
    }
}
