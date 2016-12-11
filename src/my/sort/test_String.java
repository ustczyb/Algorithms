package my.sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class test_String {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		
		
		
		//���������㷨
		
		Scanner keyinput = new Scanner(System.in);
		System.out.println("input data size:");
		int size = keyinput.nextInt();
		String[] data = new String[size];
		//���ļ��ж�ȡ���ݴ洢��data������
		File input = new File("input_String.txt");
		Scanner in = new Scanner(input);
		for(int i = 0; i < data.length; i++)
			data[i] = in.next();
		in.close();
		
		System.out.println("1.Shell 2.Quick 3.Merge 4.Heap 5.Bubble");
		int choice = keyinput.nextInt();
		
		long startTime=System.nanoTime();   //��ȡ��ʼʱ��
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
		long endTime=System.nanoTime(); //��ȡ����ʱ��

		System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ns");
		
		
		//��������д��output�ļ�
		BufferedWriter output = new BufferedWriter(new FileWriter("output_String.txt"));
		for(String i:data){
			output.write(i);
			output.newLine();
			output.flush();
		}
		output.close();
		
	}

}
