package david.qi.learn.java.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class Merge {
	
	public static void main(String[] args) {
		int[] array = {1,3,2,9,12,5,4};
	    mergeSort(array);
	    System.out.println(Arrays.toString(array));
    }
	
	private static void mergeSort(int[] a, int[] tmpArray, int left, int right) {
		if(left < right) {
			int center = (left + right)/2;
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center +1, right);
			merge(a, tmpArray, left, center +1, right);
		}
	}
	public static void mergeSort(int[] a) {
		int[] tmpArray =  new int[a.length];
		mergeSort(a, tmpArray, 0, a.length-1);
	}
	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * @param a an array of Comparable items
	 * @param tmpArray an array to place the merged result.
	 * @param leftPos the left-most index of the subarray.
	 * @param rightPos the index of the start of the second half.
	 * @param rightEnd the right-most index of the subarray.
	 */
	private static void merge(int[] a, int[] tmpArray, int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos -1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;
		//Main Loop
		while(leftPos <= leftEnd && rightPos <= rightEnd)
			if (a[leftPos] <= a[rightPos])
				tmpArray[tmpPos++] = a[leftPos++];
			else
				tmpArray[tmpPos++] = a[rightPos ++];
		while(leftPos <= leftEnd) //Copy rest of first half
			tmpArray[tmpPos++] = a[leftPos++];
		while(rightPos <=rightEnd) //copy rest of right half
			tmpArray[tmpPos++] = a[rightPos++];
		//Copy tmpArray back
		for(int i = 0; i <numElements; i++, rightEnd--)
			a[rightEnd] = tmpArray[rightEnd];
	}
	
	
}
