package my.sort;

import java.util.Arrays;

public class RadixSort {

	
		public static void main(String[] args) {
			
			Integer[] array = {3,2,3,2,5,333,45566,2345678,78,990,12,432,56};
			sort(array,10,7);
			for (int i = 0; i < array.length; i++) {
				System.out.print("  " + array[i]);
			}
		}

	

		//基于计数排序的基数排序算法
		public static void sort(Integer[] array,int radix, int distance) {
			//array为待排序数组
			//radix，代表基数
			//代表排序元素的位数
			
			int length = array.length;
			Integer[] temp = new Integer[length];//用于暂存元素
			int[] count = new int[radix];//用于计数排序
			int divide = 1;
			
			for (int i = 0; i < distance; i++) {
				System.arraycopy(array, 0,temp, 0, length);
				Arrays.fill(count, 0);				//初始化
				for (int j = 0; j < length; j++) {
					int tempKey = (temp[j]/divide)%radix;
					count[tempKey]++;
				}
				
				for (int j = 1; j < radix; j++) {
					count [j] = count[j] + count[j-1];
				}
				
				for (int j = length - 1; j >= 0; j--) {
					int tempKey = (temp[j]/divide)%radix;
					count[tempKey]--;
					array[count[tempKey]] = temp[j];
				}
				divide = divide * radix;				
			}	
		}
	}


