package my.sort;


public class Merge extends Sortable{

	private static Comparable[] temp  ;
	public static void main(String[] args) {
		Integer[] test = {17,18,29,5,15,27,34};
		sort(test);
		System.out.println(isSorted(test));
		show(test);
	}
	public static void sort(Comparable[] a){
		temp= new Comparable[a.length];
		sort(a,0,a.length-1);
	}
	private static void sort(Comparable[] a, int lo, int high){
		if(lo >= high)
			return;
		int mid = lo + (high - lo)/2;
		sort(a,lo,mid);
		sort(a,mid+1,high);
		merge(a,lo,mid,high);
	}
	public static void merge(Comparable[] a, int lo, int mid, int high){
		
		for(int i = lo; i <= high; i++)
			temp[i] = a[i];
		for(int i = lo,j = lo,k = mid+1;i <= high;i++){
			if(j > mid)
				a[i] = temp[k++];
			else if(k > high)
				a[i] = temp[j++];
			else if(less(temp[j],temp[k]))
				a[i] = temp[j++];
			else
				a[i] = temp[k++];
		}
	}
}
