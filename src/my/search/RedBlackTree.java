package my.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import my.sort.Quick;

public class RedBlackTree {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO 自动生成的方法存根
		Scanner keyboardin = new Scanner(System.in);
		System.out.println("请输入数据规模：");
		int size = keyboardin.nextInt();
		
		RBT<Integer> test = new RBT<Integer>();
		Integer[] data = new Integer[size];
		
		//从文件中读取数据存储到data数组中
		File input = new File("in.txt");
		Scanner in = new Scanner(input);
		for(int i = 0; i < data.length; i++)
			data[i] = in.nextInt();
		in.close();
		
		long[] runtime = new long[2];					//计算程序运行时间
		
		for(int i = 0; i < data.length; i++){
			
	/*		if(i%10 == 0)
				runtime[0] = System.nanoTime();*/
			test.Insert(data[i]);
	/*		if(i%10 == 9){
				runtime[1] = System.nanoTime();
				System.out.println(runtime[1] - runtime[0]);
			}*/
		/*	runtime[0] = System.nanoTime();
			for(int j = 0; j <= 9; j++)
				test.Insert(data[i*10+j]);
			runtime[1] = System.nanoTime();
				System.out.println(runtime[1] - runtime[0]);*/
		}
		
		test.size();
		
		//打印红黑树
		test.printBiTree();
		
		//前中后序的遍历结果
/*		System.out.println("先序遍历结果");
		test.preoreder();
		System.out.println("中序遍历结果");
		test.inoreder();
		System.out.println("后序遍历结果");
		test.postoreder();*/
		
		//判断红黑树选取的待删除元素是否正确
/*		int res = test.select(data.length*2/3);
		int res1 = (Integer)Quick.random_select(data, data.length*2/3);
		System.out.println(res+" "+res1);
		int res2 = test.select(data.length/3);
		int res3 = (Integer)Quick.random_select(data, data.length/3);
		System.out.println(res2+" "+res3);*/
		
		//计算删除元素的时间
	/*	runtime[0] = System.nanoTime();
		test.delete(test.select(data.length*2/3));
		runtime[1] = System.nanoTime();
		System.out.println(runtime[1] - runtime[0]);
		
		runtime[0] = System.nanoTime();
		test.delete(test.select(data.length/3));
		runtime[1] = System.nanoTime();
		System.out.println(runtime[1] - runtime[0]);*/
	}
}
class RBT<T extends Comparable>{		//红黑树
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	public static int floor = 0;
	
	private Node root;
	
	private class Node{
		T data;
		Node lchild,rchild;
		boolean color;
		int size;
		
		
		public Node(T p, boolean color){
			this.data = p;
			this.color = color;
			this.lchild = this.rchild = null;
			this.size = 1;
		}
		public void setLeft(Node p){
			this.lchild = p;
		}
		public void setRight(Node p){
			this.rchild = p;
		}
		
		@Override
		public String toString() {
			return "Node [data=" + data + ", color=" + color + ", size=" + size + "]";
		}
		public void flipcolor(){			
			this.color = !this.color;
			this.lchild.color = !this.lchild.color ;
			this.rchild.color = !this.rchild.color ;
		}
	}
	
	public boolean isRed(Node x){
		if(x == null)
			return false;
		return x.color == RED;
	}
	public boolean isEmpty(){
		return root == null;
	}
	private int size(Node x){
		if(x == null)
			return 0;
		else
			x.size = size(x.lchild) + size(x.rchild) + 1;
		return x.size;
	}
	public int size(){
		return size(root);
	}
	private Node min(Node x) { 			//返回以x为根节点的树上的最小结点
	    // assert x != null;
	    if (x.lchild == null) 
	    	return x; 
	    else                
	    	return min(x.lchild); 
	} 
	//红黑树的基本操作
	public Node leftRotate(Node h){
		Node x = h.rchild;
		h.rchild = x.lchild;
		x.lchild = h;
		x.color = h.color;
		h.color = RED;
		return x;
		}
	public Node rightRotate(Node h){
		Node x = h.lchild;
		h.lchild = x.rchild;
		x.rchild = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	private Node moveRedLeft(Node h){		//h为红色，h.left和h.left.left都是黑色，将二者之一变红（使得左孩子不是2-结点）
		h.flipcolor();
		if(isRed(h.rchild.lchild)){			//约定红链接只在左边，因此这是h的右孩子是3-结点的情况，从右孩子中拿出一个到左孩子
			h.rchild = rightRotate(h.rchild);
			h = leftRotate(h);
			h.flipcolor();
		}
		return h;
	}
	private Node moveRedRight(Node h){		//h的右孩子是2-结点，将其变为3-结点
		h.flipcolor();
		if(isRed(h.lchild.lchild)){
			h = rightRotate(h);
			h.flipcolor(); 
		}
		return h;
	}
	private Node balance(Node r){			//调整使得保持红黑树的性质
		if(isRed(r.rchild))				//有红色的右链接，左旋使成为红色的左链接
			r = leftRotate(r);
		if(isRed(r.rchild)&&!isRed(r.lchild))
			r=leftRotate(r);
		if(isRed(r.lchild)&&isRed(r.lchild.lchild))
			r=rightRotate(r);
		if(isRed(r.rchild)&&isRed(r.lchild))
			r.flipcolor();
		return r;
	}
	private Node select(Node x ,int i){		//寻找以x为根的子树中包含第i小关键字的节点
		int r ;
		if(x.lchild == null)
			r = 1;
		else
			r = x.lchild.size + 1;
		
		if(i == r)
			return x;
		else if(i < r)
			return select(x.lchild,i);
		else 
			return select(x.rchild,i-r);
	}
	public T select(int i){
		return select(root, i).data;
	}
	//打印红黑树
	public void preoreder(){				//前序遍历
		preoreder(root);
	}
	private void preoreder(Node x){
		if(x == null)
			return;
		System.out.println(x);
		preoreder(x.lchild);
		preoreder(x.rchild);
	}
	public void inoreder(){					//中序遍历
		inoreder(root);
	}
	private void inoreder(Node x){
		if(x == null)
			return;
		inoreder(x.lchild);
		System.out.println(x);
		inoreder(x.rchild);
	}
	public void postoreder(){				//后序遍历
		postoreder(root);
	}
	private void postoreder(Node x){
		if(x == null)
			return;
		postoreder(x.lchild);
		postoreder(x.rchild);
		System.out.println(x);
	}
	public void printBiTree(){
		printBiTree(root);
	}
	private void printBiTree(Node p){
		for(int i = 0; i < floor; i++){
			System.out.print("\t");
		}
		if(p == null){
			System.out.print("#");
			System.out.print("\n");
		}
		else{
			System.out.print(p.data +" "+ p.color + " " + p.size + "\n");
			floor++;
			printBiTree(p.lchild);
			printBiTree(p.rchild);
			floor--;
		}
	}
	//红黑树的插入操作
	public void Insert(T element){
		root = Insert(root, element);
		root.color = BLACK;
	}
	private Node Insert(Node r,T element){
		if(r == null)
			return new Node(element,RED);
		
		int cmp = element.compareTo(r.data);
		
		if(cmp > 0){			//插入元素大于比较元素，往右子树插入
			r.rchild = Insert(r.rchild, element);
		}
		if(cmp < 0){			//插入元素小于比较元素，往左子树插入
			r.lchild = Insert(r.lchild,element);
		}
		
		if(isRed(r.rchild)&&!isRed(r.lchild))
				r=leftRotate(r);
		if(isRed(r.lchild)&&isRed(r.lchild.lchild))
				r=rightRotate(r);
		if(isRed(r.rchild)&&isRed(r.lchild))
				r.flipcolor();
		return r;
	}
	//红黑树的删除操作
	private Node deleteMin(Node h){			//删除以h为根节点的树上的最小结点
		if(h.lchild == null)			//最小节点是根节点
			return null;
		if(!isRed(h.lchild)&&!isRed(h.lchild.lchild))		//h的左孩子是2-结点
			h = moveRedLeft(h);
		h.lchild = deleteMin(h.lchild);
		return balance(h);
	}
	public void deleteMin(){
		if(!isRed(root.lchild)&&!isRed(root.rchild))
			root.color = RED;
		root = deleteMin(root);
		if(!isEmpty())
			root.color = BLACK;
	}
	private Node delete(Node h, T key){				//使用后继结点来代替删除节点
		if(key.compareTo(h.data) < 0){
			if(!isRed(h.lchild)&&!isRed(h.lchild.lchild))		//h的左孩子是2-结点
				h = moveRedLeft(h);
			h.lchild = delete(h.lchild, key);
		}
		else{
			if(isRed(h.lchild))									//当前结点是3-结点
				h = rightRotate(h);
			if(key.compareTo(h.data) == 0 && h.rchild == null)	//找到待删除结点在3-结点上，直接删除
				return null;
			if(!isRed(h.rchild) && !isRed(h.rchild.lchild))		//待删除结点在h的右子树上且h的右孩子是2-结点
				h = moveRedRight(h);
			if(key.compareTo(h.data) == 0){			//找到待删除结点
				h.data = min(h.rchild).data;		//用h后继结点的数据代替h
				h.rchild = deleteMin(h.rchild);		//删除h的后继结点
			}
			else
				h.rchild = delete(h.rchild, key);
		}
		return balance(h);
	}
	public void delete(T key){
		if(!isRed(root.lchild)&&!isRed(root.rchild))
			root.color = RED;
		root = delete(root, key);
		if(!isEmpty())
			root.color = BLACK;
	}
}