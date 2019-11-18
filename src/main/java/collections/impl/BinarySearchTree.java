package collections.impl;

import collections.Iterable;
import collections.Iterator;

// monster mode сделать на объъектах с дженериками
public class BinarySearchTree<E extends Comparable<E>> implements Iterable
{
	private Node<E> root;

	Node<E> getRoot()
	{
		return root;
	}

	class Node<E>
	{
		E value;
		Node<E> left = null;
		Node<E> right = null;

		Node(E value)
		{
			this.value = value;
		}

		Node<E> getLeft()
		{
			return left;
		}

		E getValue()
		{
			return value;
		}

		Node<E> getRight()
		{
			return right;
		}
	}

	@Override
	public Iterator<E> iterator()
	{
		return new BinarySearchTreeIterator<>(this);
	}

	private void insert(Node<E> curNode, Node<E> newNode)
	{
		if (newNode.value.compareTo(curNode.value) < 0)
		{
			if (curNode.left == null)
			{
				curNode.left = newNode;
			} else
			{
				insert(curNode.left, newNode);
			}
		} else
		{
			if (curNode.right == null)
			{
				curNode.right = newNode;
			} else
			{
				insert(curNode.right, newNode);
			}
		}
	}

	private String walkTree(Node<E> node)
	{
		String result = "";
		if (node.left != null)
		{
			result = walkTree(node.left);
		}

		result += node.value;

		if (node.right != null)
		{
			result += walkTree(node.right);
		}

		return result;
	}

	public String show()
	{
		return walkTree(root);
	}


	public void add(E value)
	{
		Node<E> newNode = new Node<>(value);
		if (root == null)
		{
			root = newNode;
		} else
		{
			insert(root, newNode);
		}
	}

	private void reverseTree(Node<E> node)
	{
		Node<E> temp = node.right;
		node.right = node.left;
		node.left = temp;
		if (node.left != null)
		{
			reverseTree(node.left);
		}
		if (node.right != null)
		{
			reverseTree(node.right);
		}

	}

	public void reverse()
	{
		reverseTree(root);
	}

}



