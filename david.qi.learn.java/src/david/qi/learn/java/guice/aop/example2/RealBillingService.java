package david.qi.learn.java.guice.aop.example2;

public class RealBillingService implements BillingService {

	@NotOnWeekends
	@Override
	public String chargeOrder(java.lang.String order, java.lang.String creditCard) {
		return "Charged!";
	};
}
