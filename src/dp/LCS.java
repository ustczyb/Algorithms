package dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LCS {

	public static void main(String[] args) throws FileNotFoundException {
		// ObjectODO 自动生成的方法存根
		Scanner keyin = new Scanner(System.in);
		System.out.println("请输入数据序号（1~12）:");
		int n = keyin.nextInt();
		keyin.close();
		Scanner input = new Scanner(new File("input_String.txt"));
		for(int i = 0; i < 2*(n-1); i++){
			input.next();
		}
		String X = input.next();
		String Y = input.next();
		input.close();
		char[] xtemp = X.toCharArray();
		char[] ytemp = Y.toCharArray();
		Character[] x = new Character[xtemp.length];
		Character[] y = new Character[ytemp.length];
		for(int i = 0; i < x.length; i++)
			x[i] = xtemp[i];
		for(int i = 0; i < y.length; i++)
			y[i] = ytemp[i];
		
		long start,end;
		start = System.nanoTime();
		Object[] res = LCSlength(x,y);
		end = System.nanoTime();
		System.out.println("time = "+(end - start));
	
		for(int i = 0; i < res.length; i++)
			System.out.print(res[i]+" ");
	}
	public static  Object[] LCSlength(Object[] x, Object[] y){
		int m = x.length , n = y.length;
		int[][] c = new int[m+1][n+1];
		int[][] b = new int[m+1][n+1];
		
		for(int i = 0; i <=m; i++)
			c[i][0] = 0;
		for(int i = 0; i <=n; i++)
			c[0][i] = 0;
		for(int i = 1; i <=m; i++)
			for(int j = 1; j <=n; j++){
				if(x[i-1].equals(y[j-1])){
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = 1;				//即“↖”
				}
				else if(c[i-1][j] >= c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = 0;				//即“↑”
				}
				else{
					c[i][j] = c[i][j-1];
					b[i][j] = -1;				//即“←”
				}
			}
		
		int len = c[m][n];
		Object[] lcs = new Object[len];
		for(int i = m, j = n, k = len-1; i>0 && j>0;){
			if(b[i][j] == 1){
				lcs[k--] = y[j-1];
				i--;
				j--;
				continue;
			}
			if(b[i][j] == 0){
				i--;
				continue;
			}
			if(b[i][j] == -1){
				j--;
				continue;
			}
		}
		return lcs;
	}
}
