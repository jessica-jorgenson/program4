package pro4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	public Node root;
	public ArrayList<Node> nodes = new ArrayList<Node>();
	public int[][] matrix;// Edges will be represented as adjacency Matrix
	int size;

	public void setRoot(Node n) {
		this.root = n;
	}

	public Node getRoot() {
		return this.root;
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	// This method will be called to make connect two nodes
	public void connectNode(Node start, Node end) {
		if (matrix == null) {
			size = nodes.size();
			matrix = new int[size][size];
		}

		int startIndex = nodes.indexOf(start);
		int endIndex = nodes.indexOf(end);
		matrix[startIndex][endIndex] = 1;
		matrix[endIndex][startIndex] = 1;
	}

	private Node getUnvisitedChildNode(Node n) {

		int index = nodes.indexOf(n);
		int j = 0;
		while (j < size) {
			if (matrix[index][j] == 1 && ((Node) nodes.get(j)).visited == false) {
				return (Node) nodes.get(j);
			}
			j++;
		}
		return null;
	}

	// BFS traversal of a tree
	public void BFS() {

		// BFS uses Queue data structure
		Queue<Node> q = new LinkedList<Node>();
		q.add(this.root);
		printNode(this.root);
		root.visited = true;
		while (!q.isEmpty()) {
			Node n = (Node) q.remove();
			Node child = null;
			while ((child = getUnvisitedChildNode(n)) != null) {
				child.visited = true;
				printNode(child);
				q.add(child);
			}
		}
		// Clear visited property of nodes for other method
		clearNodes();
	}

	// DFS traversal of a tree
	public void DFS() {
		// DFS uses Stack data structure
		Stack<Node> s = new Stack<Node>();
		s.push(this.root);
		root.visited = true;
		printNode(root);
		while (!s.isEmpty()) {
			Node n = (Node) s.peek();
			Node child = getUnvisitedChildNode(n);
			if (child != null) {
				child.visited = true;
				printNode(child);
				s.push(child);
			} else {
				s.pop();
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}

	// Utility methods for clearing visited property of node
	private void clearNodes() {
		int i = 0;
		while (i < size) {
			Node n = (Node) nodes.get(i);
			n.visited = false;
			i++;
		}
	}

	// Utility methods for printing the node's label
	private void printNode(Node n) {
		System.out.print(n.data + " ");
	}

}