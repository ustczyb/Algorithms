package my.sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InsertionSort extends Sortable{

	public static void main(String[] args) throws FileNotFoundException {
		// TODO 自动生成的方法存根
		Integer[] test = new Integer[11];
		int i = 0;
		File file = new File("in.txt");
		Scanner in = new Scanner(file);
		while(in.hasNextInt())
			test[i++] = in.nextInt();
		in.close();
		for(Integer m:test)
			System.out.println(m);
		System.out.println(test.length);
/*		long startTime=System.nanoTime();   //获取开始时间  
		InsertionSort.sort1(test);
		long endTime=System.nanoTime(); //获取结束时间  
		show(test);
		System.out.println("Runtime: "+(endTime-startTime)+"ns");  */
	}
	
	public static void sort(Comparable[] a){
		int num = a.length;
		for(int i = 1; i < num; i++)
			for(int j = i-1; j >= 0 && less(a[j+1], a[j]); j--)
				swap(a,j,j+1);
	}
	
	public static void sort1(Comparable[] a){
		Comparable temp;
		int num = a.length;
		for(int i = 1, j = i-1; i < num; i++){
			temp = a[i];
			for(j = i-1; j >= 0 && less(temp, a[j]); j--)
				a[j+1] = a[j];
			a[j+1] = temp;
		}
	}
}
