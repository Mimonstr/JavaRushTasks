package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* 
Целостность информации
*/

public class Solution
{
    public static void main(String... args) throws Exception
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception
    {
        try
        {
            // Получаем экземпляр MessageDigest с использованием алгоритма MD5
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            // Обновляем хеш с входными данными
            md5Digest.update(byteArrayOutputStream.toByteArray());
            // Получаем байты MD5 хеша
            byte[] md5Bytes = md5Digest.digest();
            // Преобразуем байты в строку шестнадцатеричных символов
            StringBuilder md5Hex = new StringBuilder();
            for (byte b : md5Bytes)
            {
                md5Hex.append(String.format("%02x", b));
            }
            if (md5Hex.toString().equals(md5)) return true;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

            return false;
    }
}
