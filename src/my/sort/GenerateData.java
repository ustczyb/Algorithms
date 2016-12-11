package my.sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateData {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
/*		Integer[] data = getData(8192);
		
		//将输入数据写入input文件
		BufferedWriter input = new BufferedWriter(new FileWriter("input_int.txt"));
		for(Integer i:data){
			input.write(i.toString());
			input.newLine();
			input.flush();
		}
		input.close();*/
		
		String[] data = getString(4096);
		
		//将输入数据写入input文件
		BufferedWriter input = new BufferedWriter(new FileWriter("input_String.txt"));
		for(String i:data){
			input.write(i);
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


	public static String[] getString(int length){
		String[] data = new String[length];
		for(int i = 0; i < data.length; i++){
			data[i] = getRandomString(16);
		}
		return data;
	}
	
	public static String getRandomString(int length){	//生成长度为1~length的随机字符串
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");   
	    StringBuffer sb = new StringBuffer();   
	    Random random = new Random();   
	    int range = buffer.length();   
	    int len = random.nextInt(length-1)+1;
	    for (int i = 0; i < len; i ++) {   
	        sb.append(buffer.charAt(random.nextInt(range)));   
	    }   
	    return sb.toString();   
	}
	}
