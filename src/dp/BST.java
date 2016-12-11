package dp;

import java.util.Scanner;
import java.util.Arrays;

public class BST{
   static int max=Integer.MAX_VALUE;
   public static  void main(String args[]){
      int num;
      Scanner in=new Scanner(System.in);
     
     //�������ݾ��Ӽ��̻�ȡ����һ��һ�����ݱ�ʾ�ڵ�������ӵڶ��и������ݿ�ʼ��ʾ�����ڵ�ĸ���
     /*
      5
      0.15 0.10 0.05 0.10 0.20
     */
      /*
       6
       0.3 0.15 0.05 0.3 0.15 0.05
      */
       num=in.nextInt();
       double p[]=new double[num+1];
       for(int i=1;i< num+1;i++)
         p[i]=in.nextDouble();
      //��������
       double c[][]=new double[num+2][num+1];
         for(int i=0;i< c[i].length;i++)
           Arrays.fill(c[i],0);
      //�������ڵ��
       int r[][]=new int[num+2][num+1];
            for(int i=0;i< r[i].length;i++)
           Arrays.fill(r[i],0);
     //��̬�滮ʵ�����Ŷ��������������������⡣��
      OptimalBST(num,p,c,r);
      System.out.printf("�����Ŷ������������������Ϊ��%f \n",c[1][num]);
     
    
      //�������Ŷ����������������������
      System.out.printf("����ɵ����Ŷ����������ǰ��������Ϊ��");
     OptimalBSTPrint(1,num,r);
 
   }
  
   public static void OptimalBST(int num,double[] p,double[][]c,int[][] r) {
     int j=0;
     int kmin=0;
     double temp=0.0;
     double sum=0.0;
     for(int i=1;i< num+1;i++)//����͸���Ԫ�صĳ�ʼ��
     {
     
         c[i][i-1]=0;
         c[i][i]=p[i];
         r[i][i]=i;
     }
     c[num+1][num]=0;
     for(int d=1;d<=num-1;d++)//����ڵ�����
     {
         for(int i=1;i<=num-d;i++)
         {
             j=i+d;
             temp=max;
             for(int k=i;k<=j;k++)//�����Ÿ�
             {
                 if(c[i][k-1]+c[k+1][j]< temp)
                 {
                     temp=c[i][k-1]+c[k+1][j];
                     kmin=k;
                 }
             }
             r[i][j]=kmin;//��¼���Ÿ�
             sum=p[i];
             for(int s=i+1;s<=j;s++)
                 sum+=p[s];
             c[i][j]=temp+sum;
         }
     }
 }
 //���õݹ鷽ʽʵ�����Ÿ�����������Ÿ����Ǳ�����r[i][j]�еġ�
 public static  void OptimalBSTPrint(int first,int last,int[][] r)
 {
 
     int k;
     if(first<=last)
     {
         k=r[first][last];
         System.out.printf("%d  ",k);
         OptimalBSTPrint(first,k-1,r);
         OptimalBSTPrint(k+1,last,r);
     }
 }
}