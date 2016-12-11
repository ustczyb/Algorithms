package my.sort;

import java.util.Random;

public class Quick extends Sortable{

	public static void main(String[] args) {
		Integer[] test = {48,29,17,5,34,3,15,27};
	/*	sort(test);
		System.out.println(isSorted(test));
		show(test);*/
		Integer res = (Integer)random_select(test, 7);
		System.out.println(res);
	}
	public static void sort(Comparable[] a){
		sort(a, 0, a.length-1);
	}
	private static void sort(Comparable[] a, int lo, int high){
		if(lo >= high)
			return;
		int j = partition(a,lo,high);
		sort(a,lo,j);
		sort(a,j+1,high);
	}
	public static int partition(Comparable[] a, int lo, int hi){	
		Comparable temp = a[lo];
		int i = lo;
		for(int j = lo+1; j <= hi; j++){
			if(less(a[j],temp))
				swap(a,++i,j);
		}
		swap(a,i,lo);
		return i;
	}
	public static int random_partition(Comparable[] a, int p, int r){
		Random random = new Random();
		int i = random.nextInt(r-p)+p;
		swap(a,r,i);
		return partition(a,p,r);
	}
	public static Comparable random_select(Comparable[] a, int p, int r, int i){
		if(p == r)
			return a[p];
		int q = random_partition(a, p, r);
		int k = q - p + 1;
		if(i == k)
			return a[q];
		else if(i < k)
			return random_select(a, p, q-1, i);
		else
			return random_select(a, q+1, r, i-k);
	}
	public static Comparable random_select(Comparable[] a, int i){
		return random_select(a,0,a.length-1,i);
	}
/*	private static int partition(Comparable[] a, int lo, int high){
		Comparable temp = a[lo];
		int i = lo,j = high+1;
		while(true){
			while(less(a[++i],temp))
				if(i == high)	
					break;
			while(less(temp,a[--j]))
				if(j == lo)
					break;
			if(i>=j)
				break;
			swap(a,i,j);
		}
		swap(a,lo,j);
		return j;
	}*/
}
