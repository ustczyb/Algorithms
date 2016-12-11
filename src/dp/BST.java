package dp;

import java.util.Scanner;
import java.util.Arrays;

public class BST{
   static int max=Integer.MAX_VALUE;
   public static  void main(String args[]){
      int num;
      Scanner in=new Scanner(System.in);
     
     //所有数据均从键盘获取，第一行一个数据表示节点个数；从第二行各个数据开始表示各个节点的概率
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
      //创建主表；
       double c[][]=new double[num+2][num+1];
         for(int i=0;i< c[i].length;i++)
           Arrays.fill(c[i],0);
      //创建根节点表；
       int r[][]=new int[num+2][num+1];
            for(int i=0;i< r[i].length;i++)
           Arrays.fill(r[i],0);
     //动态规划实现最优二叉查找树的期望代价求解。。
      OptimalBST(num,p,c,r);
      System.out.printf("该最优二叉查找树的期望代价为：%f \n",c[1][num]);
     
    
      //给出最优二叉查找树的中序遍历结果；
      System.out.printf("构造成的最优二叉查找树的前序遍历结果为：");
     OptimalBSTPrint(1,num,r);
 
   }
  
   public static void OptimalBST(int num,double[] p,double[][]c,int[][] r) {
     int j=0;
     int kmin=0;
     double temp=0.0;
     double sum=0.0;
     for(int i=1;i< num+1;i++)//主表和根表元素的初始化
     {
     
         c[i][i-1]=0;
         c[i][i]=p[i];
         r[i][i]=i;
     }
     c[num+1][num]=0;
     for(int d=1;d<=num-1;d++)//加入节点序列
     {
         for(int i=1;i<=num-d;i++)
         {
             j=i+d;
             temp=max;
             for(int k=i;k<=j;k++)//找最优根
             {
                 if(c[i][k-1]+c[k+1][j]< temp)
                 {
                     temp=c[i][k-1]+c[k+1][j];
                     kmin=k;
                 }
             }
             r[i][j]=kmin;//记录最优根
             sum=p[i];
             for(int s=i+1;s<=j;s++)
                 sum+=p[s];
             c[i][j]=temp+sum;
         }
     }
 }
 //采用递归方式实现最优根的输出，最优根都是保存在r[i][j]中的。
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