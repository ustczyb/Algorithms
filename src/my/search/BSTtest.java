package my.search;

public class BSTtest{
	public static void main(String[] args){
		BST<Integer> test = new BST<Integer>();
		test.root = new BSTNode<Integer>(5);
		Integer[] data = {2,3,7,4,6,8,1};
		for(int i = 0; i < data.length; i++){
			test.Insert(data[i]);
		}
		test.Delete(7);
		BSTNode.printBiTree(test.root);
	}
}

class BST<T extends Comparable> {

	public BSTNode<T> root;
	
	public boolean search(T element){
		return search(root, element);
	}
	private boolean search(BSTNode<T> r, T element){	//p�洢����Ԫ������Ľڵ㣬������ҳɹ����Ǹýڵ�
		if(r == null)
			return false;
		else{
			int cmp = element.compareTo(r.data);
			if(cmp == 0){
				return true;
			}	
			if(cmp < 0)
				return search(r.lchild, element);
			if(cmp > 0)
				return search(r.rchild, element);
			
		}
		return false;
	}
	
	public void Insert(T element){
		Insert(root, element);
	}
	private void Insert(BSTNode<T> r,T element){

		int cmp = element.compareTo(r.data);
		if(cmp == 0)
			return;
		else if(cmp > 0){			//����Ԫ�ش��ڱȽ�Ԫ�أ�������������
			if(r.rchild == null){
				BSTNode<T> p = new BSTNode<T>(element);
				r.setRight(p);
			}
			else
				Insert(r.rchild,element);
		}
		else if(cmp < 0){			//����Ԫ��С�ڱȽ�Ԫ�أ�������������
			if(r.lchild == null){
				BSTNode<T> p = new BSTNode<T>(element);
				r.setLeft(p);
			}
			else
				Insert(r.lchild,element);
		}
	}
	
	public void Delete(T element){
		root = Delete(root,element);		//ʵ���������������ɾ�������������ǵ���Delete����
	}
	private BSTNode Delete(BSTNode<T> r, T element){		//����rΪ���ڵ������ɾ��element,����ɾ����r����λ�ã�ֻ��r����λ�õĽڵ㱻ɾ��ʱ�ŷ����仯��
		if(r == null)
			return null;
		int cmp = element.compareTo(r.data);
		if(cmp < 0)
			r.lchild = Delete(r.lchild, element);
		else if(cmp > 0)
			r.rchild = Delete(r.rchild, element);
		else{			//r��ɾ��
			if(r.lchild == null)
				return r.rchild;
			if(r.rchild == null)
				return r.lchild;
			BSTNode t = r;
			r = t.nextNode();
			r.rchild = deleteMin(t.rchild);
			r.lchild = t.lchild;
		}
		return r;
	}
	private BSTNode deleteMin(BSTNode r){				//ɾ����rΪ���ڵ�����ֵ���Сֵ,����ɾ����r�ڵ��λ��
		if(r.lchild == null)		//rΪ��ɾ���Ľڵ�
			return r.rchild;
		r.lchild = deleteMin(r.lchild);
		return r;
	}
}
class BSTNode<T>{
	public T data;
	public BSTNode lchild, rchild;
	public static int floor = 0;
	
	public void setLeft(BSTNode<T> p){
		this.lchild = p;
	}
	public void setRight(BSTNode<T> p){
		this.rchild = p;
	}

	public BSTNode(){
		this.data = null;
		this.lchild = this.rchild = null;
	}
	public BSTNode(T data){
		this.data = data;
		this.lchild = this.rchild = null;
	}
	
	public BSTNode<T> preNode(){
		if(this.lchild == null)
			return null;
		BSTNode<T> p = this.lchild;
		while(p.rchild!=null)
			p = p.rchild;
		return p;
	}
	public BSTNode nextNode(){
		if(this.rchild == null)
			return null;
		BSTNode p = this.rchild;
		while(p.lchild!=null)
			p = p.lchild;
		return p;
	}
	public boolean isLeaf(){		//�Ƿ�ΪҶ�ӽڵ�
		return lchild == null && rchild == null;
	}
	public static void printBiTree(BSTNode p){
		for(int i = 0; i < floor; i++){
			System.out.print("\t");
		}
		if(p == null){
			System.out.print("#");
			System.out.print("\n");
		}
		else{
			System.out.print(p.data+"\n");
			floor++;
			printBiTree(p.lchild);
			printBiTree(p.rchild);
			floor--;
		}
	}
}

/**
 * @author ���� MarcoSpring:
 * @version ����ʱ�䣺2012-8-3 ����10:14:51 ����������

public class Tree {
	public TreeNode root;// ���ڵ�

	// ���ҽڵ�
	public TreeNode search(int Key) {
		TreeNode node = root;
		// ���ȶ���һ���ڵ�����ָ������������ѭ����
		// ֻҪ�ڵ�ֵ������Ҫ���ҵĽڵ�ֵ�ͽ���ѭ�����û���ҵ��򷵻�null
		while (node.keyValue != Key) {
			if (Key < node.keyValue) { // ���Ҫ���ҵ�ֵС�ڽڵ�ֵ��ָ����ڵ�
				node = node.leftNode;
			} else { // ����ָ���ҽڵ�
				node = node.rightNode;
			}
			if (node == null) { // ����ڵ�Ϊ�����򷵻�null
				return null;
			}
		}
		return node;
	}

	// ��ӽڵ�
	public void insert(int Key) {
		TreeNode node = new TreeNode(Key);
		// ��ӽڵ�֮ǰ����Ҫ�ҵ�Ҫ��ӵ�λ�ã�������Ҫ��סҪ��ӽڵ�ĸ��ڵ�
		// �ø��ڵ������ָ��Ҫ��ӵĽڵ�
		if (root == null) { // ��������Ϊ�գ�����ڵ�ָ���½ڵ�
			root = node;
		} else {
			TreeNode currentNode = root;// ���嵱ǰ�ڵ㲢ָ����ڵ�
			TreeNode parentNode;
			while (true) { // Ѱ�ҽڵ���ӵ�λ��
				parentNode = currentNode;
				if (Key < currentNode.keyValue) {
					currentNode = currentNode.leftNode;
					if (currentNode == null) { // ���ҵ��սڵ��ʱ�򣬸��ڵ����ڵ�ָ���½ڵ�
						parentNode.leftNode = node;
						return;
					}
				} else {
					currentNode = currentNode.rightNode;
					if (currentNode == null) { // ���ҵ��սڵ��ʱ�򣬸��ڵ���ҽڵ�ָ���½ڵ�
						parentNode.rightNode = node;
						return;
					}
				}
			}
		}
	}

	// ������
	public void display(TreeNode node) {
		if (node != null) {
			display(node.leftNode);
			System.out.println(node.keyValue + ",");
			display(node.rightNode);
		}
	}

	// ���ֵ
	public int max() {
		TreeNode node = root;
		TreeNode parent = null;
		while (node != null) {
			parent = node;
			node = node.rightNode;
		}
		return parent.keyValue;
	}

	// ��Сֵ
	public int min() {
		TreeNode node = root;
		TreeNode parent = null;
		while (node != null) {
			parent = node;
			node = node.leftNode;
		}
		return parent.keyValue;
	}

	// ɾ���ڵ�����ַ�ʽɾ���ڵ�
	// 1��ɾ��û���ӽڵ�Ľڵ㣬ֱ���øýڵ�ĸ��ڵ����ڵ���ҽڵ�ָ���
	// 2��ɾ����һ���ӽڵ�Ľڵ㣬ֱ���øýڵ�ĸ��ڵ�ָ��ɾ���ڵ��ʣ��ڵ�
	// 3��ɾ���������ڵ���ӽڵ㣬�ҵ�Ҫɾ���ڵ�ĺ�̽ڵ㣬 �øýڵ����ɾ���Ľڵ�
	public boolean delete(int Key) {
		// ���Ȳ��ҽڵ㣬����¼�ýڵ�ĸ��ڵ�����
		TreeNode current = root;
		TreeNode parent = root;
		boolean isLeftNode = true;
		while (current.keyValue != Key) {
			parent = current;
			if (Key < current.keyValue) {
				isLeftNode = true;
				current = current.leftNode;
			} else {
				isLeftNode = false;
				current = current.rightNode;
			}
		}
		if (current == null) {
			System.out.println("û���ҵ�Ҫɾ���Ľڵ㣡");
			return false;
		}
		// ������������ɾ���ڵ�
		if (current.leftNode == null && current.rightNode == null) {  //Ҫɾ���Ľڵ�û���ӽڵ�
			if (current == root) { // ���ڵ��ɾ��������
				root = null;
			} else if (isLeftNode) { // �������ڵ㣬���ڵ�ָ���
				parent.leftNode = null;
			} else { // ������ҽڵ㣬�ҽڵ�ָ���
				parent.rightNode = null;
			}
		} else if (current.leftNode == null) {                         //Ҫɾ���Ľڵ�ֻ���ҽڵ�
			if (current == root) { 
				root = current.rightNode;
			} else if (isLeftNode) {
				parent.leftNode = current.rightNode;
			} else {
				parent.rightNode = current.rightNode;
			}
		} else if (current.rightNode == null) {                         //Ҫɾ���Ľڵ�ֻ����ڵ�
			if (current == root) { 
				root = current.leftNode;
			} else if (isLeftNode) {
				parent.leftNode = current.leftNode;
			} else {
				parent.rightNode = current.leftNode;
			}
		} else {                                                         //Ҫɾ���Ľڵ��������ڵ�
			TreeNode successor = findSuccessor(current);
			if(current == root){
				root = successor;
			}else if(isLeftNode){
				parent.leftNode = successor;
			}else{
				parent.rightNode = successor;
			}
			successor.leftNode = current.leftNode;
		}
		return true;
	}
	
	private TreeNode findSuccessor(TreeNode delNode){
		TreeNode parent = delNode;
		TreeNode successor = delNode;
		TreeNode current = delNode.rightNode;
		while(current != null){
			parent = successor;
			successor = current;
			current = current.leftNode;
		}
		
		if(successor != delNode.rightNode){
			parent.leftNode = successor.rightNode;
			successor.rightNode = delNode.rightNode;
		}
		return successor;
	}
}

 * */
