package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E>
{

    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet()
    {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        int capacity = Math.max(16, (int) Math.ceil(collection.size() / 0.75f));
        map = new HashMap<>(capacity);
        addAll(collection);
    }

    @Override
    public boolean add(E e)
    {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator()
    {
        return this.map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return this.map.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.map.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return this.map.containsKey(o);
    }

    @Override
    public void clear()
    {
        this.map.clear();
    }

    @Override
    public boolean remove(Object o)
    {
        return this.map.remove(o) != null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        try
        {
            AmigoSet<E> clone = (AmigoSet<E>) super.clone();
            clone.map = (HashMap<E, Object>) map.clone();
            return clone;
        }
        catch (Exception e)
        {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        out.writeInt(HashMapReflectionHelper.<Integer>callHiddenMethod(map, "capacity"));
        out.writeFloat(HashMapReflectionHelper.<Float>callHiddenMethod(map, "loadFactor"));
        // Write out size
        out.writeInt(map.size());
        // Write out all elements in the proper order.
        for (E e : map.keySet())
            out.writeObject(e);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        map = new HashMap<>(capacity, loadFactor);
        // Read in size
        int size = in.readInt();
        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++) 
        {
            E e = (E) in.readObject();
            map.put(e, PRESENT);
        }
    }

}
