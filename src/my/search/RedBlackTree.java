package my.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import my.sort.Quick;

public class RedBlackTree {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO �Զ����ɵķ������
		Scanner keyboardin = new Scanner(System.in);
		System.out.println("���������ݹ�ģ��");
		int size = keyboardin.nextInt();
		
		RBT<Integer> test = new RBT<Integer>();
		Integer[] data = new Integer[size];
		
		//���ļ��ж�ȡ���ݴ洢��data������
		File input = new File("in.txt");
		Scanner in = new Scanner(input);
		for(int i = 0; i < data.length; i++)
			data[i] = in.nextInt();
		in.close();
		
		long[] runtime = new long[2];					//�����������ʱ��
		
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
		
		//��ӡ�����
		test.printBiTree();
		
		//ǰ�к���ı������
/*		System.out.println("����������");
		test.preoreder();
		System.out.println("����������");
		test.inoreder();
		System.out.println("����������");
		test.postoreder();*/
		
		//�жϺ����ѡȡ�Ĵ�ɾ��Ԫ���Ƿ���ȷ
/*		int res = test.select(data.length*2/3);
		int res1 = (Integer)Quick.random_select(data, data.length*2/3);
		System.out.println(res+" "+res1);
		int res2 = test.select(data.length/3);
		int res3 = (Integer)Quick.random_select(data, data.length/3);
		System.out.println(res2+" "+res3);*/
		
		//����ɾ��Ԫ�ص�ʱ��
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
class RBT<T extends Comparable>{		//�����
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
	private Node min(Node x) { 			//������xΪ���ڵ�����ϵ���С���
	    // assert x != null;
	    if (x.lchild == null) 
	    	return x; 
	    else                
	    	return min(x.lchild); 
	} 
	//������Ļ�������
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
	
	private Node moveRedLeft(Node h){		//hΪ��ɫ��h.left��h.left.left���Ǻ�ɫ��������֮һ��죨ʹ�����Ӳ���2-��㣩
		h.flipcolor();
		if(isRed(h.rchild.lchild)){			//Լ��������ֻ����ߣ��������h���Һ�����3-������������Һ������ó�һ��������
			h.rchild = rightRotate(h.rchild);
			h = leftRotate(h);
			h.flipcolor();
		}
		return h;
	}
	private Node moveRedRight(Node h){		//h���Һ�����2-��㣬�����Ϊ3-���
		h.flipcolor();
		if(isRed(h.lchild.lchild)){
			h = rightRotate(h);
			h.flipcolor(); 
		}
		return h;
	}
	private Node balance(Node r){			//����ʹ�ñ��ֺ����������
		if(isRed(r.rchild))				//�к�ɫ�������ӣ�����ʹ��Ϊ��ɫ��������
			r = leftRotate(r);
		if(isRed(r.rchild)&&!isRed(r.lchild))
			r=leftRotate(r);
		if(isRed(r.lchild)&&isRed(r.lchild.lchild))
			r=rightRotate(r);
		if(isRed(r.rchild)&&isRed(r.lchild))
			r.flipcolor();
		return r;
	}
	private Node select(Node x ,int i){		//Ѱ����xΪ���������а�����iС�ؼ��ֵĽڵ�
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
	//��ӡ�����
	public void preoreder(){				//ǰ�����
		preoreder(root);
	}
	private void preoreder(Node x){
		if(x == null)
			return;
		System.out.println(x);
		preoreder(x.lchild);
		preoreder(x.rchild);
	}
	public void inoreder(){					//�������
		inoreder(root);
	}
	private void inoreder(Node x){
		if(x == null)
			return;
		inoreder(x.lchild);
		System.out.println(x);
		inoreder(x.rchild);
	}
	public void postoreder(){				//�������
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
	//������Ĳ������
	public void Insert(T element){
		root = Insert(root, element);
		root.color = BLACK;
	}
	private Node Insert(Node r,T element){
		if(r == null)
			return new Node(element,RED);
		
		int cmp = element.compareTo(r.data);
		
		if(cmp > 0){			//����Ԫ�ش��ڱȽ�Ԫ�أ�������������
			r.rchild = Insert(r.rchild, element);
		}
		if(cmp < 0){			//����Ԫ��С�ڱȽ�Ԫ�أ�������������
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
	//�������ɾ������
	private Node deleteMin(Node h){			//ɾ����hΪ���ڵ�����ϵ���С���
		if(h.lchild == null)			//��С�ڵ��Ǹ��ڵ�
			return null;
		if(!isRed(h.lchild)&&!isRed(h.lchild.lchild))		//h��������2-���
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
	private Node delete(Node h, T key){				//ʹ�ú�̽��������ɾ���ڵ�
		if(key.compareTo(h.data) < 0){
			if(!isRed(h.lchild)&&!isRed(h.lchild.lchild))		//h��������2-���
				h = moveRedLeft(h);
			h.lchild = delete(h.lchild, key);
		}
		else{
			if(isRed(h.lchild))									//��ǰ�����3-���
				h = rightRotate(h);
			if(key.compareTo(h.data) == 0 && h.rchild == null)	//�ҵ���ɾ�������3-����ϣ�ֱ��ɾ��
				return null;
			if(!isRed(h.rchild) && !isRed(h.rchild.lchild))		//��ɾ�������h������������h���Һ�����2-���
				h = moveRedRight(h);
			if(key.compareTo(h.data) == 0){			//�ҵ���ɾ�����
				h.data = min(h.rchild).data;		//��h��̽������ݴ���h
				h.rchild = deleteMin(h.rchild);		//ɾ��h�ĺ�̽��
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