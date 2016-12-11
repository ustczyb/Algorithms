package my.sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class test_Integer {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
/*		Integer[] data = getData(4096);
		
		//将输入数据写入input文件
		BufferedWriter input = new BufferedWriter(new FileWriter("input_int.txt"));
		for(Integer i:data){
			input.write(i.toString());
			input.newLine();
			input.flush();
		}r
		input.close();*/
		
		Integer[] data = new Integer[8192];
		//从文件中读取数据存储到data数组中
		File input = new File("input_int.txt");
		Scanner in = new Scanner(input);
		for(int i = 0; i < data.length; i++)
			data[i] = in.nextInt();
		in.close();
		
		//调用排序算法
		long startTime, endTime;
		
		startTime=System.nanoTime();   //获取开始时间
		//Integer[] res= CountingSort.sort(data,65535);
		Quick.sort(data);
		endTime=System.nanoTime(); //获取结束时间
		System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
		
		//将输出结果写入output文件
		BufferedWriter output = new BufferedWriter(new FileWriter("output_int.txt"));
		for(Integer i:data){
			output.write(i.toString());
			output.newLine();
			output.flush();
		}
		output.close();
		
	}
	public static Integer[] getData(int length){
		Integer[] data = new Integer[length];
		Random random = new Random();    
        for(int i = 0; i < data.length; i++){
        	data[i] = random.nextInt(65534)+1;
        }
		return data;
	}
}
