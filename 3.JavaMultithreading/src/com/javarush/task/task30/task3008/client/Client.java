package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client
{
    protected Connection connection;
    private volatile boolean clientConnected = false;
    protected String getServerAddress()
    {
        ConsoleHelper.writeMessage("Введите адрес сервера:");
        return ConsoleHelper.readString();
    }
    protected int getServerPort()
    {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        return ConsoleHelper.readInt();
    }
    protected String getUserName()
    {
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSendTextFromConsole()
    {
        return true;
    }
    protected SocketThread getSocketThread()
    {
        return new SocketThread();
    }
    protected void sendTextMessage(String text)
    {

        try
        {
            Message message = new Message(MessageType.TEXT, text);
            connection.send(message);
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Не удалось отправить сообщение");
            clientConnected = false;
        }
    }
    public void run()
    {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        try
        {
            synchronized (this)
            {
                wait();
            }
        }
        catch (InterruptedException e)
        {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            return;
        }
        if (clientConnected) ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        else ConsoleHelper.writeMessage( "Произошла ошибка во время работы клиента.");

        // Пока не будет введена команда exit, считываем сообщения с консоли и отправляем их на сервер
        while (clientConnected)
        {
            String text = ConsoleHelper.readString();
            if (text.equalsIgnoreCase("exit"))
                break;
            if (shouldSendTextFromConsole()) sendTextMessage(text);
        }
    }

    public class SocketThread extends Thread
    {
        protected void processIncomingMessage(String message)
        {
            // Выводим текст сообщения в консоль
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName)
        {
            // Выводим информацию о добавлении участника
            ConsoleHelper.writeMessage("Участник '" + userName + "' присоединился к чату.");
        }

        protected void informAboutDeletingNewUser(String userName)
        {
            // Выводим информацию о выходе участника
            ConsoleHelper.writeMessage("Участник '" + userName + "' покинул чат.");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this)
            {
                Client.this.notify();
            }
        }
        protected void clientHandshake() throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message message = connection.receive();
                if (message.getType() == (MessageType.NAME_REQUEST))
                {
                    String username = getUserName();
                    connection.send(new Message(MessageType.USER_NAME, username));
                }
                else if (message.getType() == (MessageType.NAME_ACCEPTED))
                {
                    notifyConnectionStatusChanged(true);
                    return;
                }
                else
                {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message message = connection.receive();
                if (message.getType() == (MessageType.TEXT)) processIncomingMessage(message.getData());
                else if (message.getType() == (MessageType.USER_ADDED)) informAboutAddingNewUser(message.getData());
                else if (message.getType() == (MessageType.USER_REMOVED))
                    informAboutDeletingNewUser(message.getData());
                else
                {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        public void run()
        {
            String address = getServerAddress();
            int port = getServerPort();
            try
            {
                Socket socket = new Socket(address,port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException | ClassNotFoundException e)
            {
                notifyConnectionStatusChanged(false);
            }

        }
    }

    public static void main(String[] args)
    {
        Client client = new Client();
        client.run();
    }
}
