package spring.desai.common.utils;

import java.util.Arrays;

public class Utils {
	
	
	public static void ascSort(int[] array){
		for (int i = 1; i < array.length; i++) {
			int value = array[i];
			int j = i;
			while(j > 0 && value < array[j-1]){
				array[j] = array[j-1];
				j--;
			}
			array[j] = value;
		}
	}
	
	public static void main(String[] args) {
		int[] array = { 56, 234, 2, 124, 453, 123, 56, 54, 34, 5, 345, 2, 234, 234, 10, 6, 8, 7, 9, 34, 3, 312 };
		sort(array);
		System.out.println(Arrays.toString(array));
	}
	
	
	
	
	public static void sort(int[] array){
		for (int i = 1; i < array.length; i++) {
			int value = array[i];
			int j = i;
			while (j > 0 && value < array[j-1]){
				array[j] = array[j-1];
				j--;
			}
			array[j] = value;
		}
	}
	
	
	public static int search(int[] array, int valueToSearch){
		int pos = -1;
		
		if(valueToSearch == array[array.length/2]){
			return array.length/2;
		} else if (valueToSearch < array[array.length/2]){
			
		} else if (valueToSearch > array[array.length/2]){
			
		}
		
		return pos;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	public static void sort(int[] arr) {
//		for (int i = 1; i < arr.length; i++) {
//			int valueToSort = arr[i];
//			int j = i;
//			while (j > 0 && arr[j - 1] > valueToSort) {
//				arr[j] = arr[j - 1];
//				j--;
//			}
//			arr[j] = valueToSort;
//		}
//
////		return arr;
//	}
//
//	public static void main(String[] args) {
//		int[] array = { 56, 234, 2, 124, 453, 123, 56, 54, 34, 5, 345, 2, 234, 234, 10, 6, 8, 7, 9, 34, 3, 312 };
//		sort(array);
//		System.out.println(Arrays.toString(array));
//	}

}
