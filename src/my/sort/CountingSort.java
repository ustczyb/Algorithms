package my.sort;

public class CountingSort {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Integer[] test = {2,5,3,0,2,3,0,3};
		Integer[] res = sort(test,5);
		for(int i:res)
			System.out.println(i);
	}
	
	public static Integer[] sort(Integer[] data, int k){	//k是数据的最大范围（0~k）
		
		Integer[] result = new Integer[data.length];	//存放结果的数组
		int[] temp = new int[k+1];				//排序用到的辅助数组
		
		for(int i = 0; i < data.length; i++){
			temp[data[i]]++;
		}
		for(int i = 1; i <= k; i++){
			temp[i] += temp[i-1];
		}
		for(int i = data.length-1; i >= 0; i--){
			result[temp[data[i]]-1] = data[i];
			temp[data[i]]--;
		}
		return result;
	}
	
}
