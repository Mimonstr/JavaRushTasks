package com.javarush.task.task32.task3205;

import java.io.Reader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/* 
Создание прокси-объекта
*/

public class Solution
{
    public static void main(String[] args)
    {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy()
    {
        SomeInterfaceWithMethods originalObject = new SomeInterfaceWithMethodsImpl();
        ClassLoader classLoader = originalObject.getClass().getClassLoader();
        Class<?>[] interfaces = originalObject.getClass().getInterfaces();
        InvocationHandler handler = new CustomInvocationHandler(originalObject);

        return (SomeInterfaceWithMethods) Proxy.newProxyInstance(classLoader, interfaces, handler);

    }
}
