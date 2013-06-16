package david.qi.learn.java.effectivejava.enumstudy;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyEnumMap {
	static class Herb {
		enum Type{ ANNUAL, PERENNIAL, BIENNIAL}
		
		private final String name;
		private final Type type;
		
		Herb(String name, Type type){
			this.name = name;
			this.type = type;
		}
		@Override public String toString() {
			return name;
		}
	}
	
	/**
	 * 有一种非常快速的Map专门用于枚举键, java.util.EnumMap, 注意EnumMap的构造器使用键类型的class对象.
	 * @param args
	 */
	public static void main(String[] args) {
	    Herb[] garden = new Herb[]{
	                             new Herb("glass", Herb.Type.ANNUAL), 
	                             new Herb("flower", Herb.Type. PERENNIAL), 
	                             new Herb("tree", Herb.Type.BIENNIAL),
	                             new Herb("wood", Herb.Type.PERENNIAL)};
		//Using an EnumMap to accessiate date with an enum
	    Map<Herb.Type, Set<Herb>> herbsByType= 
	    			new EnumMap<Herb.Type, Set<Herb>>(Herb.Type.class);
	    for(Herb.Type t : Herb.Type.values()) 
	    	herbsByType.put(t, new HashSet<Herb>());
	    for(Herb h : garden)
	    	herbsByType.get(h.type).add(h);
	    
	    System.out.println(herbsByType);
    }

}
