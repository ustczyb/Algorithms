package my.sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class test_Integer {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
/*		Integer[] data = getData(4096);
		
		//����������д��input�ļ�
		BufferedWriter input = new BufferedWriter(new FileWriter("input_int.txt"));
		for(Integer i:data){
			input.write(i.toString());
			input.newLine();
			input.flush();
		}r
		input.close();*/
		
		Integer[] data = new Integer[8192];
		//���ļ��ж�ȡ���ݴ洢��data������
		File input = new File("input_int.txt");
		Scanner in = new Scanner(input);
		for(int i = 0; i < data.length; i++)
			data[i] = in.nextInt();
		in.close();
		
		//���������㷨
		long startTime, endTime;
		
		startTime=System.nanoTime();   //��ȡ��ʼʱ��
		//Integer[] res= CountingSort.sort(data,65535);
		Quick.sort(data);
		endTime=System.nanoTime(); //��ȡ����ʱ��
		System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ns");
		
		//��������д��output�ļ�
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
