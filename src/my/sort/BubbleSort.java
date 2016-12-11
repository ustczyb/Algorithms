package my.sort;

public class BubbleSort extends Sortable{

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Integer[] test = {48,29,17,5,34,3,15,27};
		sort(test);
		for(Integer i:test)
			System.out.println(i);
	}
	public static void sort(Comparable[] data){
		for(int i = 1; i < data.length; i++){
			for(int j = data.length - 1; j >= i; j--){
				if(less(data[j], data[j-1]))
					swap(data,j,j-1);
			}
		}
	}
}
