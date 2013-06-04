package david.qi.learn.java.effectivejava.enumstudy;

public class EnumTest {
	enum Color {
		Red, Blue, Green;
		public String toString() {
			switch (this) {
			case Red:
				return "Color.Red";
			case Blue:
				return "Color.Blue";
			case Green:
				return "Color.Green";
			default:
				return "unknown Color";
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		example1();		
		example2();
		example3();
		example4();
	}


	private static void example1() {
	    for (Color c : Color.values())
			System.out.println(c);
    }

	private static void example2() {
	    for(Planet p : Planet.values()) {
			System.out.println(p.surfaceWeight(70d));
		}
    }
	private static void example3() {
		for(Operation o : Operation.values()) {
			System.out.println(o.apply(2d, 5d));
		}
	}
	
	//Example for enum with method and constractor
	enum Planet{
		MERUCRY	(3.304e+23, 	2.439e6),
		VENUS	 	(4.869e+24,	6.052e6),
		EARTH			(5.975e+24,		6.378e6);
		
		private final double mass;
		private final double radius;
		private final double surfaceGravity;
		private static final double G = 6.67300E-11;
		
		Planet(double mass, double radius){
			this.mass = mass;
			this.radius = radius;
			this.surfaceGravity = G * mass / (radius * radius);
		}
		
		double mass() {return mass;}
		double radius() {return radius;}
		double surfaceGravity() {return surfaceGravity;}
		double surfaceWeight(double mass) {
			return mass * surfaceGravity;
		}		
	}
	
	//Example for enum: constant-specific class body  
	//	有时候你需要将本质上不同的行为和每个枚举关联起来, 成为特定于常量的方法实现
	enum Operation{
		PLUS 		{double apply(double x, double y) {return x + y;}},
		MINUS	{double apply(double x, double y) {return x - y;}},
		TIMES		{double apply(double x, double y) {return x * y;}},
		DIVDE		{double apply(double x, double y)	 {return x / y;}};
		
		abstract double apply(double x, double y);
	}
	
	public static void example4(){
		for(PayrollDay day : PayrollDay.values()) {
			System.out.println(day.pay(9d, 10d));
		}
	}
	
	//Strategy enum 策略枚举, 如果有多个枚举常量同时共享某些行为, 使用策略枚举, 将这些共享行为放入策略枚举中
	enum PayrollDay{
		MONDAY(PayType.WEEKDAY), 
		TUESDAY(PayType.WEEKDAY),
		SUNDAY(PayType.WEEKEND);
		
		private final PayType payType;
		PayrollDay(PayType payType){
			this.payType = payType;
		}
		
		double pay(double hoursWorked, double payRate) {
			return payType.pay(hoursWorked, payRate);
		}
		
		//strategy enum
		private enum PayType{
			WEEKDAY{
				double overtimePay(double hours, double payRate) {
					return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
				}
			},
			WEEKEND{
				double overtimePay(double hours, double payRate) {
					return hours * payRate / 2;
				}
			};
			private static final int HOURS_PER_SHIFT = 8;
			abstract double overtimePay(double hours, double payRate);
			double pay(double hoursWorked, double payRate) {
				double basePay = hoursWorked * payRate;
				return basePay + overtimePay(hoursWorked, payRate);
			}
		}
	}
}
