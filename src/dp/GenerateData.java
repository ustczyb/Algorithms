package dp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateData {

	public static void main(String[] args) throws IOException {
		//生成随机浮点数
		/*Random test = new Random();
		double[] data = new double[70];
		for(int i = 0; i < 70; i++)
			data[i] = test.nextDouble();
		
		BufferedWriter input = new BufferedWriter(new FileWriter("input_double.txt"));
		for(Double i:data){
			input.write(String.format("%.2f", i));
			input.newLine();
			input.flush();
		}
		input.close();*/
		
		//生成随机字符串
		String[] data = getString();
		
		//将输入数据写入input文件
		BufferedWriter input = new BufferedWriter(new FileWriter("input_String.txt"));
		for(String i:data){
			input.write(i);
			input.newLine();
			input.flush();
		}
		input.close();
	}
	public static String[] getString(){
		String[] data = new String[24];
		int[] len = {15,10,15,20,15,30,15,40,15,50,15,60,15,25,30,25,45,25,60,25,75,25,90,25};

		for(int i = 0; i < data.length; i++){
			data[i] = getRandomString(len[i]);
		}
		return data;
	}
	
	public static String getRandomString(int length){	//生成长度为length的随机字符串
		StringBuffer buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");   
	    StringBuffer sb = new StringBuffer();   
	    Random random = new Random();   
	    int range = buffer.length();   
	    int len = length;
	    for (int i = 0; i < len; i ++) {   
	        sb.append(buffer.charAt(random.nextInt(range)));   
	    }   
	    return sb.toString();   
	}
}
