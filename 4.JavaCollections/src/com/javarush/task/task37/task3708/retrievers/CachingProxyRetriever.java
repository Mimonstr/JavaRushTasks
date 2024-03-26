package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.RemoteStorage;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever
{
    OriginalRetriever originalRetriever;

    LRUCache<Long, Object> cache = new LRUCache<>(10);

    public CachingProxyRetriever(Storage storage)
    {
        originalRetriever = new OriginalRetriever(storage);
    }
    @Override
    public Object retrieve(long id)
    {
        Object cachedObject = cache.find(id);
        if (cachedObject != null)
        {
            //System.out.println("Retrieving object from cache...");
            return cachedObject;
        }
        else
        {
            //System.out.println("Object not found in cache. Retrieving from storage...");
            Object retrievedObject = originalRetriever.retrieve(id);
            if (retrievedObject != null)
            {
                cache.set(id, retrievedObject);
            }
            return retrievedObject;
        }
    }
}
