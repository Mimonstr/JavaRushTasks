package com.javarush.task.task40.task4006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

/* 
Отправка GET-запроса через сокет
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url)
    {
        try
        {
            String host = url.getHost();
            String path = url.getPath().isEmpty() ? "/" : url.getPath();
            int port = url.getPort() != -1 ? url.getPort() : 80;

            Socket socket = new Socket(host, port);
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("GET " + path + " HTTP/1.1");
            out.println("Host: " + host);
            out.println();

            String responseLine;
            while ((responseLine = in.readLine()) != null)
            {
                System.out.println(responseLine);
            }

            socket.close();

        }
        catch (UnknownHostException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}