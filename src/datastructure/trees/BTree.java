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

	// �ҵ��ؼ��ֶ�Ӧ��Ҷ�ӽڵ㣬���롣
	public void insert(Node node) {
		// ���ΪҶ�ӽڵ�
		if (null == childs.get(0)) {
			insertToNode(node);
			return;
		}
		int insertIndex = getInsertIndex(keys, node.keys.get(0));
		childs.get(insertIndex).insert(node);
	}

	/** 
	 *	���½ڵ����ڵ���
	 *	�Ƚ��½ڵ����ýڵ���
	 *	���ж��Ƿ�ؼ��ֳ�����ֵ����������� 
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
	 * ���ؼ��ֹ���ʱ����Ҫ����
	 * �����ڵ���Ϊ���Ѻ�������ڵ㣬node1��node2��Ϊ����������
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
		// ��ǰΪ���ڵ�
		if (null == parent) {
			//			System.out.println("root");
			return;
		}
		//�����Ǹ��ڵ㣬�򽫱������ڵ���븸�ڵ���
		parent.insertToNode(this);
	}

	//���insertKeyӦ�ò���keys�е�λ��
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

	// ���½ڵ�ĸ���ָ��
	private void updateParent(List<Node> childs, Node parent) {
		if (null == childs.get(0))
			return;
		childs.get(0).parent = parent;
		if (2 <= childs.size()) {
			childs.get(1).parent = parent;
		}
	}

	/**
	 * �ڵ����ʱ���ؽ��ڵ�
	 * @param start �ؼ��ֵĿ�ʼ����
	 * @param end �ؼ��ֵĽ�����������������
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
	 * ɾ��ֵΪkey�Ľڵ�
	 * */
	public void delete(int key) {
		Result result = search(key);
		if (null == result.node) {
			return;
		}
		//case 1��ɾ������Ҷ�ӽڵ�
		if (null == result.node.childs.get(0)) {
			deleteLeapKey(result);
			return;
		}
		//case 2��ɾ�������м�ڵ�
		Result leastNode = getRightLeastLeapNode(result);
		//1���øýڵ�����������С�ؼ��ֽڵ�������ýڵ�
		result.node.keys.set(result.index, leastNode.getKey());
		//2��ɾ������С�ؼ��ֵ�Ҷ�ӽڵ�
		deleteLeapKey(leastNode);
	}

	//��ȡ�ýڵ�����������С�ؼ��ֽڵ�
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
		//ɾ���ڵ�
		currentNode.keys.remove(re.index);
		currentNode.childs.remove(re.index);
		//���ɾ���ڵ�󣬹ؼ�����������
		if (currentNode.keys.size() < LEASTKEYCOUNT) {
			balanceTheNode(currentNode, parentIndex);
		}
		return true;
	}

	/**
	 * @param node ɾ���Ľڵ�
	 * @param parentIndex �ýڵ��ڸ��ڵ������
	 * */
	private boolean balanceTheNode(Node node, int parentIndex) {
		//�������ڽڵ��У��Ƿ����ܹ����
		if (!moveFromNeighbourNode(node, parentIndex)) {
			//�ھӽڵ��޷���λ�����븸�ڵ�ϲ�
			return mergeWithParent(node, parentIndex);
		}
		return true;
	}

	//���ھӽڵ�����һ���ؼ���
	private boolean moveFromNeighbourNode(Node node, int parentIndex) {
		Node paren = node.parent;
		//�����ھ�
		if (parentIndex > 0) {
			Node leftNeighbour = paren.childs.get(parentIndex - 1);
			//���ھ��ж���ؼ���
			if (leftNeighbour.keys.size() > LEASTKEYCOUNT) {
				//�����ھӴ�����һ���ڵ�
				moveKey(leftNeighbour, leftNeighbour.keys.size() - 1,
						leftNeighbour.childs.size() - 1, node, 0, 0,
						parentIndex);
				return true;
			}
		}
		//�����ھ�
		if (parentIndex < paren.childs.size() - 1) {
			Node rightNeighbour = paren.childs.get(parentIndex + 1);
			//���ھ��ж���ؼ���
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
	 * ���ھӴ��ƶ���ȡ�ؼ��ֵķ���
	 * �ƶ��ؼ��ּ�����Ľڵ�
	 * @param nodeFrom ���ڻ�ȡ�ؼ��ֵĽڵ�
	 * @param fromKeyIndex ��ȡ�ؼ��ֽڵ������
	 * @param fromChildIndex ��ȡ�ؼ��ֵĽڵ�Ķ�Ӧ���ӽڵ�
	 * */
	private void moveKey(Node nodeFrom, int fromKeyIndex, int fromChildIndex,
			Node nodeTo, int toKeyIndex, int toChildIndex, int parentIndex) {
		Node paren = nodeFrom.parent;
		//1�������ڵ�ؼ�����ӵ���Ҫ���ӹؼ��ֵĽڵ�nodeTo
		//		Tools.println("===step1===");
		//		paren.print();
		nodeTo.keys.add(toKeyIndex, paren.keys.get(parentIndex - 1));
		//2�����ھӵ����һ�����ӽڵ���ӵ�node�׺���
		nodeTo.childs.add(toChildIndex, nodeFrom.childs.get(fromChildIndex));
		//3�������ڵ�ؼ��ָ���Ϊfrom�ڵ�Ĺؼ���
		paren.keys.set(parentIndex - 1, nodeFrom.keys.get(fromKeyIndex));
		//4��ɾ���ھӽڵ������Ϣ
		nodeFrom.keys.remove(fromKeyIndex);
		nodeFrom.childs.remove(fromChildIndex);
	}

	//���ýڵ��븸�ڵ�ϲ�
	//һ�ɲ�����������ϲ��ķ�ʽ
	boolean mergeWithParent(Node node, int parentIndex) {
		Node paren = node.parent;
		//�����ھ�
		if (parentIndex < paren.childs.size() - 1) {
			return mergeWithNeighbour(node, parentIndex);
		} else if (parentIndex == paren.childs.size() - 1) {
			//�������ھӣ����ýڵ�+Ki->���ھ� 
			parentIndex -= 1;
			return mergeWithNeighbour(paren.childs.get(parentIndex),
					parentIndex);
		}
		return false;
	}

	//��node���ھӽڵ�ϲ�
	private boolean mergeWithNeighbour(Node node, int parentIndex) {
		Node paren = node.parent;
		//���ھ�+Ki -> node
		//1�������ؼ��ּ���node��
		Node neighbour = paren.childs.get(parentIndex + 1);
		int parentKey = paren.keys.get(parentIndex);
		node.keys.add(parentKey);
		//2�����ֵܽڵ����node��
		mergeNode(node, neighbour);
		//3��ɾ�����ڵ�Ki
		//������ڵ�Ϊ���ڵ����������Ƿ�����ؼ�������
		if (isRoot(paren)) {
			//������ڵ�Ϊ���ڵ���ɾ����Ϊ�սڵ㣬��node����Ϊ���ڵ�
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
		//4�����Ը��ڵ��Ƿ���������
		balanceTheNode(paren, ppIndex);
		return true;
	}

	//�ýڵ��Ƿ�Ϊ���ڵ�
	boolean isRoot(Node node) {
		return null == node.parent;
	}

	//��node2 ��ӵ�node1 ĩβ
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
