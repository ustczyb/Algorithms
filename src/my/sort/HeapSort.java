package my.sort;

public class HeapSort extends Sortable{

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Integer[] test = {0,5,4,7,2,1,6};
		sort(test);
		for(int i: test)
		System.out.println(i);
	}
	
	private static int parent(int i){
		return (i-1)/2;
	}
	private static int lchild(int i){
		return 2*i+1;
	}
	private static int rchild(int i){
		return 2*i+2;
	}
	public static void sort(Comparable[] a){
		int N = a.length-1;
		for(int k = parent(N); k >=0; k--)			//建堆
			sink(a, k, N);
		while(N > 0){
			swap(a,0,N--);
			sink(a,0,N);
		}
	}
	public static void sink(Comparable[] a, int i, int size){		//堆大小为size,调整第i位的元素
		int max;
		if(lchild(i) > size)
			return;
		else if(rchild(i) <= size && less(a[lchild(i)],a[rchild(i)]))	//右孩子存在且大于左孩子
			max = rchild(i);
		else
			max = lchild(i);
		if(less(a[i],a[max]) ){
			swap(a,max,i);
			sink(a,max,size);
		}
	}
}
