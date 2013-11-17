package david.qi.learn.java.algorithm.sort;

import java.util.Arrays;

public class MyFastSort {
	
	public static void main(String[] args) {
	    int[] a = {2, 113, 88, 23, 12,3,103,5,13,7,998};
	    sort(a, 0, a.length);
	    System.out.println(Arrays.toString(a));
    }
	
	/**
	 * 深度优先，先左后右
	 * 缺点：使用零时数组，很容易下标出错，花了一个下午调试
	 */
	private static void sort(int[] a, int start, int end) {
		if (end - start < 2) return;
		int middle = (end - start) /2 + start;
		int base = a[middle];
		
		System.out.println("base is : " + base);
		
		int[] tmpLeft = new int[end - start];
		int leftPos = 0;
		int[] tmpRight = new int[end - start];
		int rightPos = 0;
		
		for(int i = start; i < end; i++) {
			if(a[i] < base) {
				tmpLeft[leftPos++] = a[i]; 		//items smaller than base
			}else if(a[i] > base){
				tmpRight[rightPos++] = a[i]; 	//items bigger than base
			}				
		}
		
		
		int j = 0;
		for(int i = start; i < start + leftPos; i++,j++ ) {
			a[i] = tmpLeft[j];
		}
		a[start + leftPos]=base;
		j = 0;
		for(int i = start + leftPos+1; i <start + leftPos +rightPos+1; i++,j++) {
			a[i] = tmpRight[j];
		}
		
		System.out.println(Arrays.toString(a));		
		System.out.println("sort left : start = " + start + " end = " + (start +leftPos));
		sort(a, start, start +leftPos);
		System.out.println("sort right : start = " + (start+leftPos+1) + " end = " + (start +leftPos + rightPos+1));
		sort(a, start + leftPos+1, start + leftPos + rightPos+1);						
	}
	
	private static void swap(int[] a, int fromPos, int toPos) {
		int tmp = a[toPos];
		a[toPos] = a[fromPos];
		a[fromPos] = tmp;
	}
}
