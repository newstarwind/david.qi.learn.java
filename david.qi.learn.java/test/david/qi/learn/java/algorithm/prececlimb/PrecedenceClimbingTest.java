package david.qi.learn.java.algorithm.prececlimb;

import org.junit.Test;
import static org.junit.Assert.*;
import david.qi.learn.java.algorithm.prececlimb.PrecedenceClimbing.Tokenizer;
import static david.qi.learn.java.algorithm.prececlimb.PrecedenceClimbing.parse;
import static david.qi.learn.java.algorithm.prececlimb.PrecedenceClimbing.compute_expr;

public class PrecedenceClimbingTest {

	@Test
	public void testCompute_expr() {
		String expr = "1-(2+3)*4/5^6";
		Tokenizer t = parse(expr);
		int result = compute_expr(t, 1);
		assertEquals(result, 1);
	}
}
