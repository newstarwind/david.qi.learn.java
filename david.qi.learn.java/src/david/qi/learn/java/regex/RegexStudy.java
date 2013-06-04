package david.qi.learn.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStudy {
	public static void main(String[] args) {
	    Pattern p = Pattern.compile("\\s*(\\d+)|(\\S)");
	    Matcher m = p.matcher("  21 + 3 * ( 5^2 + 6  )");
	    while(m.find()) {
//	    	System.out.println(m.group());
	    	System.out.println(m.group(1)+m.group(2));
	    }
	    
    }
}
