package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;


public class LoginCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        //ConsoleHelper.writeMessage("Enter your credit card number (12 digits):");
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        String cardNumber = ConsoleHelper.readString();

       // ConsoleHelper.writeMessage("Enter your PIN (4 digits):");
        String pin = ConsoleHelper.readString();

        if(isValidCredentials(cardNumber, pin))
        {
            //ConsoleHelper.writeMessage("Verification successful. Welcome!");
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));
        }
        else
        {
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),cardNumber));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            execute(); // Рекурсивный вызов для повторного запроса данных
        }

    }
    private boolean isValidCredentials(String cardNumber, String pin)
    {
        return validCreditCards.containsKey(cardNumber) && validCreditCards.getString(cardNumber).equals(pin);
    }
}
