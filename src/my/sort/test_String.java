package my.sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class test_String {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		
		
		
		//调用排序算法
		
		Scanner keyinput = new Scanner(System.in);
		System.out.println("input data size:");
		int size = keyinput.nextInt();
		String[] data = new String[size];
		//从文件中读取数据存储到data数组中
		File input = new File("input_String.txt");
		Scanner in = new Scanner(input);
		for(int i = 0; i < data.length; i++)
			data[i] = in.next();
		in.close();
		
		System.out.println("1.Shell 2.Quick 3.Merge 4.Heap 5.Bubble");
		int choice = keyinput.nextInt();
		
		long startTime=System.nanoTime();   //获取开始时间
		switch (choice) {
		case 1:
			Shell.sort(data);
			break;
		case 2:
			Quick.sort(data);
			break;
		case 3:
			Merge.sort(data);
			break;
		case 4:
			HeapSort.sort(data);
			break;
		case 5:
			BubbleSort.sort(data);
			break;
			
		}
		long endTime=System.nanoTime(); //获取结束时间

		System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
		
		
		//将输出结果写入output文件
		BufferedWriter output = new BufferedWriter(new FileWriter("output_String.txt"));
		for(String i:data){
			output.write(i);
			output.newLine();
			output.flush();
		}
		output.close();
		
	}

}
