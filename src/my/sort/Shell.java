package my.sort;

public class Shell extends Sortable{

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Integer[] test = {18,29,17,5,34,3,15,27};
		sort(test);
		System.out.println(isSorted(test));
		show(test);
	}
	
	public static void sort(Comparable[] a){
		int num = a.length;
		int h = num/3+1;
		while(h >=1){
			for(int k = 0; k < h; k++)
				for(int i = h+k; i < num; i+=h)
					for(int j = i-h; j >= 0 && less(a[j+h], a[j]); j-=h)
						swap(a,j,j+h);
			h/=3;
		}
		
	}
}
