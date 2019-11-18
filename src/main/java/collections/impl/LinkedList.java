package collections.impl;

import collections.Iterator;
import collections.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LinkedList<E> implements List<E>
{
	private int size;
	private Node<E> first;
	private Node<E> last;

	@Override
	public boolean add(E e)
	{
		Node<E> lastNode = this.last;
		Node<E> newNode = new Node<>(e, null, lastNode);
		this.last = newNode;
		if (lastNode == null)
		{
			this.first = newNode;
		} else
		{
			lastNode.next = newNode;
		}
		this.size++;
		return true;
	}

	private void validateIndex(int index)
	{
		if ((index < 0) || (index >= this.size))
		{
			throw new IndexOutOfBoundsException();
		}
	}

	public boolean add(E e, int index)
	{
		if (index == size - 1)
		{
			return add(e);
		}
		Node<E> node = getNodeByIndex(index);
		Node<E> next = node.next;
		Node<E> newNode = new Node<>(e, next, node);
		node.next = newNode;
		next.prev = newNode;
		size++;
		return true;
	}

	@Override
	public E remove(int index)
	{
		Node<E> removeNode = getNodeByIndex(index);
		E item = removeNode.item;
		Node<E> prev = removeNode.prev;
		Node<E> next = removeNode.next;
		if (prev == null)
		{
			this.first = next;
		} else
		{
			prev.next = next;
			removeNode.prev = null;
		}

		if (next == null)
		{
			this.last = prev;
		} else
		{
			next.prev = prev;
			removeNode.prev = null;
		}
		removeNode.item = null;

		size--;
		return item;
	}

	private Node<E> getNodeByIndex(int index)
	{
		validateIndex(index);
		Node<E> node;
		int i;
		if (index < this.size >> 1)
		{
			node = this.first;
			for (i = 0; i < index; ++i)
			{
				node = node.next;
			}
			return node;
		} else
		{
			node = this.last;
			for (i = this.size - 1; i > index; --i)
			{
				node = node.prev;
			}
			return node;
		}
	}

	@Override
	public E get(int index)
	{
		return getNodeByIndex(index).item;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new Iterator<>()
		{
			Node<E> cursor = first;

			@Override
			public boolean hasNext()
			{
				return (cursor != null);
			}

			@Override
			public E next()
			{
				E item = cursor.item;
				cursor = cursor.next;
				return item;
			}
		};
	}

	public int getSize()
	{
		return this.size;
	}

	@Data
	private class Node<E>
	{
		E item;
		Node<E> next;
		Node<E> prev;

		Node(E e, Node<E> next, Node<E> prev)
		{
			this.item = e;
			this.next = next;
			this.prev = prev;
		}


	}


}
