package collections.impl;

import collections.Iterable;
import collections.Iterator;
import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

// monster mode сделать на объъектах с дженериками
public class BinarySearchTree<E extends Comparable<E>> implements Iterable
{
	private Node<E> root;

	Node<E> getRoot()
	{
		return root;
	}

	private class Node<E>
	{
		E value;
		Node<E> left;
		Node<E> right;

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

	@Data
	private static class BinarySearchTreeIterator<E extends Comparable<E>> implements Iterator<E>
	{
		Deque<BinarySearchTree<E>.Node<E>> nodeDeque;

		BinarySearchTreeIterator(BinarySearchTree<E> tree)
		{
			this.nodeDeque = new ArrayDeque<>();
			if (tree.getRoot() != null)
			{
				populate(tree.getRoot(), nodeDeque);
			}
		}

		@Override
		public boolean hasNext()
		{
			return !nodeDeque.isEmpty();
		}

		@Override
		public E next()
		{
			if (!hasNext()) throw new NoSuchElementException();
			return nodeDeque.removeLast().getValue();
		}

		private void populate(BinarySearchTree<E>.Node<E> tree, Deque<BinarySearchTree<E>.Node<E>> nodeDeque)
		{
			if (tree.getLeft() != null)
			{
				populate(tree.getLeft(), nodeDeque);
			}
			nodeDeque.push(tree);
			if (tree.getRight() != null)
			{
				populate(tree.getRight(), nodeDeque);
			}
		}
	}


	public void reverse()
	{
		if (root != null) reverseTree(root);
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
}



