package datastructure.trees;

import java.util.ArrayList;
import java.util.List;

import util.Tools;

class Node {
	// int keyNum;
	List<Integer> keys;
	List<Node> childs;
	Node parent;
	final int LEASTKEYCOUNT = (BTree.MAX_NODE + 1) / 2 - 1;

	public Node(int key) {
		keys = new ArrayList<Integer>();
		childs = new ArrayList<Node>();
		keys.add(key);
		childs.add(null);
		childs.add(null);
	}

	public Node(List<Integer> ks, List<Node> cs) {
		keys = ks;
		childs = cs;
	}

	// 找到关键字对应的叶子节点，插入。
	public void insert(Node node) {
		// 如果为叶子节点
		if (null == childs.get(0)) {
			insertToNode(node);
			return;
		}
		int insertIndex = getInsertIndex(keys, node.keys.get(0));
		childs.get(insertIndex).insert(node);
	}

	/** 
	 *	将新节点插入节点中
	 *	先将新节点插入该节点中
	 *	再判断是否关键字超出阈值，超出则分裂 
	 */
	private void insertToNode(Node newnode) {
		int insertIndex = getInsertIndex(this.keys, newnode.keys.get(0));
		updateParent(newnode.childs, this);
		keys.add(insertIndex, newnode.keys.get(0));
		childs.add(insertIndex, newnode.childs.get(0));
		childs.set(insertIndex + 1, newnode.childs.get(1));
		if (keys.size() >= BTree.MAX_NODE) {
			split();
		}
	}

	/** 
	 * 当关键字过多时，需要分裂
	 * 将本节点作为分裂后的上升节点，node1和node2作为其两个孩子
	 */
	private void split() {
		int midIndex = BTree.MAX_NODE / 2;
		Node node1 = rebuildNode(this, 0, midIndex);
		Node node2 = rebuildNode(this, midIndex + 1, keys.size());
		int midkey = keys.get(midIndex);
		keys.clear();
		keys.add(midkey);
		childs.clear();
		childs.add(node1);
		childs.add(node2);
		// 当前为根节点
		if (null == parent) {
			//			System.out.println("root");
			return;
		}
		//若不是根节点，则将本上升节点插入父节点中
		parent.insertToNode(this);
	}

	//获得insertKey应该插入keys中的位置
	private int getInsertIndex(List<Integer> keys, int insertKey) {
		int insertIndex = keys.size();
		for (int i = 0; i < keys.size(); i++) {
			if (insertKey < keys.get(i)) {
				insertIndex = i;
				break;
			}
		}
		return insertIndex;
	}

	// 更新节点的父亲指针
	private void updateParent(List<Node> childs, Node parent) {
		if (null == childs.get(0))
			return;
		childs.get(0).parent = parent;
		if (2 <= childs.size()) {
			childs.get(1).parent = parent;
		}
	}

	/**
	 * 节点分裂时，重建节点
	 * @param start 关键字的开始索引
	 * @param end 关键字的结束索引（不包含）
	 * */
	private Node rebuildNode(Node node, int start, int end) {
		List<Node> cs = new ArrayList<Node>();
		List<Integer> ks = new ArrayList<Integer>();
		int i;
		for (i = start; i < end; i++) {
			ks.add(node.keys.get(i));
			cs.add(node.childs.get(i));
		}
		cs.add(node.childs.get(i));
		Node newNode = new Node(ks, cs);
		newNode.parent = node;
		updateParent(newNode.childs, newNode);

		return newNode;
	}

	/**
	 * 删除值为key的节点
	 * */
	public void delete(int key) {
		Result result = search(key);
		if (null == result.node) {
			return;
		}
		//case 1：删除的是叶子节点
		if (null == result.node.childs.get(0)) {
			deleteLeapKey(result);
			return;
		}
		//case 2：删除的是中间节点
		Result leastNode = getRightLeastLeapNode(result);
		//1、用该节点右子树的最小关键字节点来替代该节点
		result.node.keys.set(result.index, leastNode.getKey());
		//2、删除该最小关键字的叶子节点
		deleteLeapKey(leastNode);
	}

	//获取该节点右子树的最小关键字节点
	private Result getRightLeastLeapNode(Result re) {
		Node node = re.node.childs.get(re.index + 1);
		while (null != node.childs.get(0)) {
			node = node.childs.get(0);
		}
		return new Result(node, 0);
	}

	private boolean deleteLeapKey(Result re) {
		Node currentNode = re.node;
		int parentIndex = getInsertIndex(currentNode.parent.keys,
				currentNode.keys.get(0));
		//删除节点
		currentNode.keys.remove(re.index);
		currentNode.childs.remove(re.index);
		//如果删除节点后，关键字数量过少
		if (currentNode.keys.size() < LEASTKEYCOUNT) {
			balanceTheNode(currentNode, parentIndex);
		}
		return true;
	}

	/**
	 * @param node 删除的节点
	 * @param parentIndex 该节点在父节点的索引
	 * */
	private boolean balanceTheNode(Node node, int parentIndex) {
		//查找相邻节点中，是否有能够借的
		if (!moveFromNeighbourNode(node, parentIndex)) {
			//邻居节点无法借位，则与父节点合并
			return mergeWithParent(node, parentIndex);
		}
		return true;
	}

	//从邻居节点移入一个关键字
	private boolean moveFromNeighbourNode(Node node, int parentIndex) {
		Node paren = node.parent;
		//有左邻居
		if (parentIndex > 0) {
			Node leftNeighbour = paren.childs.get(parentIndex - 1);
			//左邻居有多余关键字
			if (leftNeighbour.keys.size() > LEASTKEYCOUNT) {
				//从左邻居处借来一个节点
				moveKey(leftNeighbour, leftNeighbour.keys.size() - 1,
						leftNeighbour.childs.size() - 1, node, 0, 0,
						parentIndex);
				return true;
			}
		}
		//有右邻居
		if (parentIndex < paren.childs.size() - 1) {
			Node rightNeighbour = paren.childs.get(parentIndex + 1);
			//右邻居有多余关键字
			if (rightNeighbour.keys.size() > LEASTKEYCOUNT) {
				int toKeyIndex = node.keys.size() == 0 ? 0
						: node.keys.size() - 1;
				int toChildIndex = node.childs.size() == 0 ? 0 : node.childs
						.size() - 1;
				moveKey(rightNeighbour, 0, 0, node, toKeyIndex, toChildIndex,
						parentIndex + 1);
				return true;
			}
		}

		return false;
	}

	/**
	 * 从邻居处移动获取关键字的方法
	 * 移动关键字及下面的节点
	 * @param nodeFrom 用于获取关键字的节点
	 * @param fromKeyIndex 获取关键字节点的索引
	 * @param fromChildIndex 获取关键字的节点的对应孩子节点
	 * */
	private void moveKey(Node nodeFrom, int fromKeyIndex, int fromChildIndex,
			Node nodeTo, int toKeyIndex, int toChildIndex, int parentIndex) {
		Node paren = nodeFrom.parent;
		//1、将父节点关键字添加到需要增加关键字的节点nodeTo
		//		Tools.println("===step1===");
		//		paren.print();
		nodeTo.keys.add(toKeyIndex, paren.keys.get(parentIndex - 1));
		//2、将邻居的最后一个孩子节点添加到node首孩子
		nodeTo.childs.add(toChildIndex, nodeFrom.childs.get(fromChildIndex));
		//3、将父节点关键字更新为from节点的关键字
		paren.keys.set(parentIndex - 1, nodeFrom.keys.get(fromKeyIndex));
		//4、删除邻居节点相关信息
		nodeFrom.keys.remove(fromKeyIndex);
		nodeFrom.childs.remove(fromChildIndex);
	}

	//将该节点与父节点合并
	//一律采用优先向左合并的方式
	boolean mergeWithParent(Node node, int parentIndex) {
		Node paren = node.parent;
		//有右邻居
		if (parentIndex < paren.childs.size() - 1) {
			return mergeWithNeighbour(node, parentIndex);
		} else if (parentIndex == paren.childs.size() - 1) {
			//若有左邻居，将该节点+Ki->左邻居 
			parentIndex -= 1;
			return mergeWithNeighbour(paren.childs.get(parentIndex),
					parentIndex);
		}
		return false;
	}

	//将node与邻居节点合并
	private boolean mergeWithNeighbour(Node node, int parentIndex) {
		Node paren = node.parent;
		//将邻居+Ki -> node
		//1、将父关键字加入node中
		Node neighbour = paren.childs.get(parentIndex + 1);
		int parentKey = paren.keys.get(parentIndex);
		node.keys.add(parentKey);
		//2、将兄弟节点加入node中
		mergeNode(node, neighbour);
		//3、删除父节点Ki
		//如果父节点为根节点则无需检测是否满足关键字条件
		if (isRoot(paren)) {
			//如果父节点为根节点且删除后为空节点，则将node复制为根节点
			if (paren.keys.size() == 1) {
				paren.keys = node.keys;
				paren.childs = node.childs;
				updateParent(paren.childs, paren);
				return true;
			}
			paren.keys.remove(parentIndex);
			paren.childs.remove(parentIndex + 1);
			return true;
		}
		int ppIndex = getInsertIndex(paren.parent.keys, parentKey);
		paren.keys.remove(parentIndex);
		paren.childs.remove(parentIndex + 1);
		//4、测试父节点是否满足条件
		balanceTheNode(paren, ppIndex);
		return true;
	}

	//该节点是否为根节点
	boolean isRoot(Node node) {
		return null == node.parent;
	}

	//将node2 添加到node1 末尾
	Node mergeNode(Node node1, Node node2) {
		node1.keys.addAll(node2.keys);
		node1.childs.addAll(node2.childs);
		updateParent(node2.childs, node1);
		return node1;
	}

	Result search(int key) {
		int insertIndex = keys.size();
		for (int i = 0; i < keys.size(); i++) {
			if (key <= keys.get(i)) {
				if (keys.get(i) == key) {
					return new Result(this, i);
				}
				insertIndex = i;
				break;
			}
		}
		if (null == childs.get(0))
			return new Result();
		return childs.get(insertIndex).search(key);
	}

	void print(String tab) {
		int i;
		System.out.print(tab);
		for (i = 0; i < keys.size(); i++) {
			System.out.print(keys.get(i) + ",");
		}
		System.out.print("\n");
		for (i = 0; i < childs.size(); i++) {
			if (null != childs.get(0) && (childs.size() >= i + 1))
				// System.out.println(childs.get(i));
				childs.get(i).print(tab + "\t");
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("keys:");
		for (int k : keys)
			sb.append(k + " ");
		sb.append(".childSize:");
		sb.append(childs.size());
		return sb.toString();
	}
}

class Result {
	Node node;
	int index;

	public Result(Node node, int index) {
		this.node = node;
		this.index = index;
	}

	public Result() {
	}

	int getKey() {
		return node.keys.get(index);
	}

	@Override
	public String toString() {
		if (null == node)
			return "none found!";
		return node.toString() + ".index=" + index;
	}
}

public class BTree {
	public static final int MAX_NODE = 3;
	Node root;

	void print() {
		root.print("");
	}

	void insertNode(Node node) {
		if (null == root) {
			root = new Node(node.keys.get(0));
			node.parent = root;
		} else {
			root.insert(node);
		}
	}

	void delete(int key) {
		root.delete(key);
	}

	Result search(int key) {
		return root.search(key);
	}

	public static void main(String[] args) {
		BTree tree = new BTree();
		tree.insertNode(new Node(8));
		tree.insertNode(new Node(5));
		tree.insertNode(new Node(2));
		tree.insertNode(new Node(3));
		tree.insertNode(new Node(4));
		tree.insertNode(new Node(1));
		tree.insertNode(new Node(0));
		tree.insertNode(new Node(10));
		//		tree.insertNode(new Node(11));
		Tools.println("==build tree==");
		tree.print();
		tree.delete(8);
		tree.delete(4);
		tree.delete(10);
		Tools.println("==after delete==");
		tree.print();
		tree.delete(1);
		Tools.println("==after delete==");
		tree.delete(2);
		tree.delete(3);
		tree.print();
	}
}
