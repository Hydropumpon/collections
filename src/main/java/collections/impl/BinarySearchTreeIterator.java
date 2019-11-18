package collections.impl;

import collections.Iterator;
import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;

@Data
public class BinarySearchTreeIterator<E extends Comparable<E>> implements Iterator<E>
{
	Deque<BinarySearchTree<E>.Node<E>> nodeDeque;

	BinarySearchTreeIterator(BinarySearchTree<E> tree)
	{
		this.nodeDeque = new ArrayDeque<>();
		if (tree.getRoot()!=null)
		{
			populate(tree.getRoot(), nodeDeque);
		}
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
	@Override
	public boolean hasNext()
	{
		return !nodeDeque.isEmpty();
	}

	@Override
	public E next()
	{
		return nodeDeque.removeLast().getValue();
	}
}
