package collections.impl;

import collections.Map;

import java.util.Objects;


public class HashMap<K, V> implements Map<K, V>
{
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] table;
    private static final double THRESHOLD_LEVEL = 0.75d;
    private int size;

    class Node<K, V>
    {
        private K key;
        private V value;
        private int hashCode;

        Node(K key, V value)
        {
            this.key = key;
            this.value = value;
            this.hashCode = Objects.hashCode(this.key);
        }

        public Node(K key)
        {
            this.key = key;
            this.hashCode = Objects.hashCode(this.key);
        }
    }

    public int getSize()
    {
        return size;
    }

    public HashMap()
    {
        table = new Node[DEFAULT_CAPACITY];
    }

    private int getHash(K key)
    {
        return Objects.hashCode(key);
    }

    private int getHashIndex(int hashCode, Node<K, V>[] table)
    {
        return hashCode & (table.length - 1);
    }

    private boolean isIndexFree(int index)
    {
        return table[index] == null;
    }

    private boolean isEnlargeRequired()
    {
        return size == (int) Math.round(table.length * THRESHOLD_LEVEL) - 1;
    }

    private void reCountHash(Node<K, V>[] tmp)
    {
        for (Node<K, V> node : this.table)
        {
            if (node != null)
            {
                tmp[getHashIndex(node.hashCode, tmp)] = node;
            }
        }
        this.table = tmp;
    }

    private void increaseCapacity()
    {
        Node<K, V>[] tmp = new Node[table.length * 2];
        reCountHash(tmp);
    }

    @Override
    public boolean insert(K key, V value)
    {
        Node<K, V> node = new Node<>(key, value);
        int index = getHashIndex(node.hashCode, this.table);
        if (isIndexFree(index))
        {
            if (isEnlargeRequired())
            {
                increaseCapacity();
                return insert(key, value);
            }
            this.size++;
        }
        table[index] = node;
        return true;
    }

    @Override
    public V get(K key)
    {
        int index = getHashIndex(getHash(key), this.table);
        if (isIndexFree(index))
        {
            return null;
        }
        return this.table[index].value;
    }

    @Override
    public boolean delete(K key)
    {
        int index = getHashIndex(getHash(key), this.table);
        if (isIndexFree(index))
        {
            return false;
        }
        this.size--;
        this.table[index] = null;
        return true;
    }
}
