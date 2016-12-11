package my.sort;

public class SelectionSort extends Sortable{

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Integer[] test = {18,29,17,5,34,3,15,27};
		SelectionSort.sort(test);
		show(test);
	}
	
	public static void sort(Comparable[] a){
		int num = a.length;
		for(int i = 0; i < num; i++){
			int min = i;
			for(int j = i+1; j < num; j++){
				if(less(a[j], a[min]))
					min = j;
			}
			swap(a,i,min);
		}
	}
}
