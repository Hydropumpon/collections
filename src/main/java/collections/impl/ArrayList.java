package collections.impl;

import collections.Iterator;
import collections.List;
import lombok.Data;

import java.util.Arrays;

@Data
public class ArrayList<E> implements List<E>
{
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MINIMAL_CAPACITY = 1;
    private static final int MAXIMAL_CAPACITY = Integer.MAX_VALUE - 8;
    private E[] elements;
    private int size;

    public int getCapacity()
    {
        return this.elements.length;
    }

    private boolean isEnlargeNeeded()
    {
        return this.size == elements.length;
    }

    private boolean isTrimNeeded()
    {
        return this.size == elements.length / 2;
    }

    private boolean isFull()
    {
        return this.size == MAXIMAL_CAPACITY;
    }

    private int increaseCapacity(int capacity)
    {
        if (capacity < this.elements.length)
        {
            capacity = MAXIMAL_CAPACITY;
        }
        return capacity;
    }

    private int decreaseCapacity(int capacity)
    {
        return Math.max(capacity, MINIMAL_CAPACITY);
    }

    private E[] changeCapacity(int capacity)
    {
        return Arrays.copyOf(this.elements, capacity);
    }

    public ArrayList(int capacity)
    {
        checkInitCapacity(capacity);
        this.elements = (E[]) new Object[capacity];
    }

    public ArrayList()
    {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    private void checkInitCapacity(int capacity)
    {
        if (capacity < MINIMAL_CAPACITY)
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean add(E e)
    {
        if (isFull())
        {
            return false;
        }
        if (isEnlargeNeeded())
        {
            this.elements = changeCapacity(increaseCapacity(elements.length * 2));
        }
        this.elements[size] = e;
        this.size++;
        return true;
    }

    @Override
    public E remove(int index)
    {
        validateIndex(index);
        E removed = this.elements[index];
        int quaMoved = size - index - 1;
        if (quaMoved > 0)
        {
            System.arraycopy(elements, index + 1, elements, index, quaMoved);
        }
        this.size--;
        this.elements[this.size] = null;
        if (isTrimNeeded())
        {
            this.elements = changeCapacity(decreaseCapacity(elements.length / 2));
        }
        return removed;
    }

    public int getSize()
    {
        return this.size;
    }

    @Override
    public E get(int index)
    {
        validateIndex(index);
        return elements[index];
    }

    private void validateIndex(int index)
    {
        if ((index < 0) || (index >= this.size))
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<E> iterator()
    {
        return new Iterator<>()
        {
            int cursor = 0;

            @Override
            public boolean hasNext()
            {
                return cursor < size;
            }

            @Override
            public E next()
            {
                return elements[cursor++];
            }
        };
    }
}
