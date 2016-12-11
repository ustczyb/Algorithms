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
	private boolean search(BSTNode<T> r, T element){	//p存储距离元素最近的节点，如果查找成功就是该节点
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
		else if(cmp > 0){			//插入元素大于比较元素，往右子树插入
			if(r.rchild == null){
				BSTNode<T> p = new BSTNode<T>(element);
				r.setRight(p);
			}
			else
				Insert(r.rchild,element);
		}
		else if(cmp < 0){			//插入元素小于比较元素，往左子树插入
			if(r.lchild == null){
				BSTNode<T> p = new BSTNode<T>(element);
				r.setLeft(p);
			}
			else
				Insert(r.lchild,element);
		}
	}
	
	public void Delete(T element){
		root = Delete(root,element);		//实际上这才是真正的删除操作，而不是调用Delete方法
	}
	private BSTNode Delete(BSTNode<T> r, T element){		//在以r为根节点的树中删除element,返回删除后r的新位置（只有r所在位置的节点被删除时才发生变化）
		if(r == null)
			return null;
		int cmp = element.compareTo(r.data);
		if(cmp < 0)
			r.lchild = Delete(r.lchild, element);
		else if(cmp > 0)
			r.rchild = Delete(r.rchild, element);
		else{			//r被删除
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
	private BSTNode deleteMin(BSTNode r){				//删除以r为根节点的树种的最小值,返回删除后r节点的位置
		if(r.lchild == null)		//r为被删除的节点
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
	public boolean isLeaf(){		//是否为叶子节点
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
 * @author 作者 MarcoSpring:
 * @version 创建时间：2012-8-3 上午10:14:51 二叉搜索树

public class Tree {
	public TreeNode root;// 根节点

	// 查找节点
	public TreeNode search(int Key) {
		TreeNode node = root;
		// 首先定义一个节点让其指向根，在下面的循环中
		// 只要节点值不等于要查找的节点值就进入循环如果没有找到则返回null
		while (node.keyValue != Key) {
			if (Key < node.keyValue) { // 如果要查找的值小于节点值则指向左节点
				node = node.leftNode;
			} else { // 否则指向右节点
				node = node.rightNode;
			}
			if (node == null) { // 如果节点为空了则返回null
				return null;
			}
		}
		return node;
	}

	// 添加节点
	public void insert(int Key) {
		TreeNode node = new TreeNode(Key);
		// 添加节点之前首先要找到要添加的位置，这样就要记住要添加节点的父节点
		// 让父节点的左右指向要添加的节点
		if (root == null) { // 如果根结点为空，则根节点指向新节点
			root = node;
		} else {
			TreeNode currentNode = root;// 定义当前节点并指向根节点
			TreeNode parentNode;
			while (true) { // 寻找节点添加的位置
				parentNode = currentNode;
				if (Key < currentNode.keyValue) {
					currentNode = currentNode.leftNode;
					if (currentNode == null) { // 当找到空节点的时候，父节点的左节点指向新节点
						parentNode.leftNode = node;
						return;
					}
				} else {
					currentNode = currentNode.rightNode;
					if (currentNode == null) { // 当找到空节点的时候，父节点的右节点指向新节点
						parentNode.rightNode = node;
						return;
					}
				}
			}
		}
	}

	// 遍历树
	public void display(TreeNode node) {
		if (node != null) {
			display(node.leftNode);
			System.out.println(node.keyValue + ",");
			display(node.rightNode);
		}
	}

	// 最大值
	public int max() {
		TreeNode node = root;
		TreeNode parent = null;
		while (node != null) {
			parent = node;
			node = node.rightNode;
		}
		return parent.keyValue;
	}

	// 最小值
	public int min() {
		TreeNode node = root;
		TreeNode parent = null;
		while (node != null) {
			parent = node;
			node = node.leftNode;
		}
		return parent.keyValue;
	}

	// 删除节点分三种方式删除节点
	// 1、删除没有子节点的节点，直接让该节点的父节点的左节点或右节点指向空
	// 2、删除有一个子节点的节点，直接让该节点的父节点指向被删除节点的剩余节点
	// 3、删除有三个节点的子节点，找到要删除节点的后继节点， 用该节点替代删除的节点
	public boolean delete(int Key) {
		// 首先查找节点，并记录该节点的父节点引用
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
			System.out.println("没有找到要删除的节点！");
			return false;
		}
		// 下面分三种情况删除节点
		if (current.leftNode == null && current.rightNode == null) {  //要删除的节点没有子节点
			if (current == root) { // 根节点就删除整棵树
				root = null;
			} else if (isLeftNode) { // 如果是左节点，做节点指向空
				parent.leftNode = null;
			} else { // 如果是右节点，右节点指向空
				parent.rightNode = null;
			}
		} else if (current.leftNode == null) {                         //要删除的节点只有右节点
			if (current == root) { 
				root = current.rightNode;
			} else if (isLeftNode) {
				parent.leftNode = current.rightNode;
			} else {
				parent.rightNode = current.rightNode;
			}
		} else if (current.rightNode == null) {                         //要删除的节点只有左节点
			if (current == root) { 
				root = current.leftNode;
			} else if (isLeftNode) {
				parent.leftNode = current.leftNode;
			} else {
				parent.rightNode = current.leftNode;
			}
		} else {                                                         //要删除的节点有两个节点
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
