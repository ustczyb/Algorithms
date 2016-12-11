package dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graphviz.GraphViz;


public class OptimalBST {
	
	static int max=Integer.MAX_VALUE;
	
	public int n;
	public int[][] root;				
	public double[][] e, w;			
	
	public static void main(String[] args) throws FileNotFoundException {
		//文件录入
		Scanner keyin = new Scanner(System.in);
		System.out.println("请输入数据规模:");
		int n = keyin.nextInt();
		keyin.close();
		Scanner input = new Scanner(new File("input_double.txt"));
		double[] data = new double[2*n+1];
		for(int i = 0; i < data.length; i++)
			data[i] = input.nextDouble();
		input.close();
		
		//数据归一化处理
		double sum = 0;
		for(double i:data)
			sum+=i;
		for(int i = 0; i < data.length; i++)
			data[i] = data[i] / sum;
		double[] p = new double[n];
		double[] q = new double[n+1];
		for(int i = 0; i < n; i++){
			p[i] = data[i];
			q[i] = data[i+n];
		}
		q[n] = data[2*n];
		
		OptimalBST test = new OptimalBST(p, q, n);
		long start,end;
		start = System.nanoTime();
		Node r = test.makeTree();
		end = System.nanoTime();
		System.out.println("time = "+(end - start));
		Node.printTree(r);
		
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.addln("node [shape=record]");
		Node.printTree(gv,r);
		gv.addln(gv.end_graph());
	    System.out.println(gv.getDotSource());
	    String type = "gif";
	    File out = new File("F:\\code\\graph\\OptimalBST." + type);
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
	}
	
	public OptimalBST(double[] p, double[] q, int n){
		this.n = n;
		e = new double[n+1][n+1];		//e[i][j]表示包含第i+1至j+1个元素树的期望代价
		w = new double[n+1][n+1];		//w[i][j]表示包含第i+1至j+1个元素树的概率之和
		root = new int[n][n];			//root[i][j]表示包含第i+1至j+1个元素树的根
		
		for(int i = 0; i <= n; i++){
			w[i][i] = q[i];
			e[i][i] = q[i];
		}
		
		for(int l = 1; l <= n; l++)
			for(int i = 1; i <= n-l+1; i++){
				int j = i + l - 1;
				e[i-1][j] = max;
				w[i-1][j] = w[i-1][j-1] + p[j-1] + q[j];
				for(int r = i; r <= j; r++){
					double t = e[i-1][r-1] + e[r][j] + w[i-1][j];
					if(t < e[i-1][j]){
						e[i-1][j] = t;
						root[i-1][j-1] = r-1;
					}
				}
			}
		
	}
	public Node makeTree(){
		Node node = makeTree(0,n-1);
		return node;
	}
	private Node makeTree(int m, int n){
		if(m > n)
			return null;
		if(m == n){
			int r = root[m][n];
			Node node = new Node(r, w[r][r]);
			node.lchild = null;
			node.rchild = null;
			return node;
		}
		int r = root[m][n];
		Node node = new Node(r, w[r][r]);
		node.lchild = makeTree(m, r-1);
		node.rchild = makeTree(r+1, n);
		return node;
	}
	
}
class Node{
	public int num;
	public double weight;
	public Node lchild;
	public Node rchild;
	
	public Node(int num, double weight){
		this.num = num;
		this.weight = weight;
		this.lchild = null;
		this.rchild = null;
	}
	public static void printTree(Node x){
		if(x == null)
			return;
		System.out.print(x.num + "{");
		printTree(x.lchild);
		printTree(x.rchild);
		System.out.print("}");
	}
	public static void printTree(GraphViz gv, Node p){
		if(p.lchild == null && p.rchild == null){
			gv.addln(p.num+"->"+"d"+p.num+"[shape = box]");
			gv.addln(p.num+"->"+"d"+(p.num+1)+"[shape = box]");
			return;
		}
		if(p.lchild == null){
			gv.addln(p.num+"->"+"d"+p.num+"[shape = box]");
			gv.addln(p.num+":se"+"->"+p.rchild.num);
			printTree(gv,p.rchild);
		}
		else if(p.rchild == null){
			gv.addln(p.num+":sw"+"->"+p.lchild.num);
			gv.addln(p.num+"->"+"d"+(p.num+1)+"[shape = box]");
			printTree(gv,p.lchild);
		}
		else{
			gv.addln(p.num+":sw"+"->"+p.lchild.num);
			gv.addln(p.num+":se"+"->"+p.rchild.num);
			printTree(gv,p.lchild);
			printTree(gv,p.rchild);
		}
	}
}