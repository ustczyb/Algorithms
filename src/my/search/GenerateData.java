package my.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateData {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		Integer[] data = getData(100);
		
		//将输入数据写入input文件
		BufferedWriter input = new BufferedWriter(new FileWriter("in.txt"));
		for(Integer i:data){
			input.write(i.toString());
			input.newLine();
			input.flush();
		}
		input.close();
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
