package david.qi.learn.java.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

import david.qi.learn.java.algorithm.prececlimb.PrecedenceClimbing;
import david.qi.learn.java.algorithm.prececlimb.PrecedenceClimbing.Operation;

public class Point21 {

	private static final int CARD_NUM = 4;
	private static final int GOAL = 24;
	private static final String[] CARDS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
	
	public static void main(String[] args) {
				
		List<List<String>> numList = new ArrayList<List<String>>();
		List<String> elements = Arrays.asList(Arrays.copyOf(CARDS, CARDS.length));
		List<String> perms = new ArrayList<String>();
		List<String> opts = getOperations();
		
		sort(elements, perms, numList);
		List<String> results = calculateFormulas(numList, opts);
		for(String formula : results) {
			System.out.println(formula + " = " + GOAL);
		}
	}

	private static List<String> calculateFormulas(List<List<String>> numList, List<String> opts) {
		List<String> results = new ArrayList<String>();
	    for(List<String> nums : numList) {
			List<String> formulas = generateFormula(nums, opts);
			for(String formula : formulas) {
				int result = PrecedenceClimbing.compute_expr(PrecedenceClimbing.parse(formula),1);
				if(result == GOAL)
					results.add(formula);
			}
		}
	    return results;
    }

	private static List<String> getOperations() {
	    Operation[] operations = Operation.values();
		List<String> opts = new ArrayList<String>();
		for(Operation opt : operations) {
			opts.add(opt.value);
		}
	    return opts;
    }
	
	private static List<String> generateFormula(List<String> nums, List<String> opts){
		List<List<String>> all = new ArrayList<List<String>>();
		for(String s : nums) {
			List<String> section = mixNumAndOpt(s , opts);
			all.add(section);
		}		
		return trim(mixLists(all));		
	}
	
	
	private static List<String> mixNumAndOpt(String num, List<String> opts){
		List<String> results = new ArrayList<String>()	;
		for(String opt : opts) {
			results.add(new StringBuilder(num).append(opt).toString());
		}
		return results;
	}
	
	private static List<String> mix2List(List<String> left, List<String> right){
		List<String> result = new ArrayList<String>();
		for(String s : left) {
			for(String t : right) {
				StringBuilder sb = new StringBuilder(s);
				result.add(sb.append(t).toString());
			}
		}
		return result;
	}
	
	private static List<String> mixLists(List<List<String>> all){
		List<String> result = all.get(0);
		for(int i = 1; i < all.size(); i++) {
			List<String> rhs = all.get(i);
			result = mix2List(result, rhs);			
		}
		return result;
	}
	
	/**
	 * Remove 去掉算式结尾的多余字符和重复的字符串
	 * @param sections
	 * @return
	 */
	private static List<String> trim(List<String> sections){
		List<String> result = new ArrayList<String>();
		String temp = null;
		for(String s : sections) {
			s = s.substring(0, s.length()-1);
			if(!s.equals(temp)) {
				result.add(s);
			}
			temp = s;
		}
		return result;
	}

	/**
	 * 递归算法：将数据分为源和目的部分，递归将数据从左侧移右侧实现全排列
	 * @param elements: possible elements for selection
	 * @param permutation
	 */
	public static void sort(List<String> elements, List<String> perm, List<List<String>> output) {
		if (perm.size() == CARD_NUM) {
			output.add(perm);
			return;
		}
		for (int i = 0; i < elements.size(); i++) {
			List<String> newElements = new ArrayList<String>(elements);
			List<String> newPerm = new ArrayList<String>(perm);
			newPerm.add(newElements.get(i));
			newElements.remove(i);
			sort(newElements, newPerm, output);
		}
	}	
}
