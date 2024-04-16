package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute()throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean hasMoney = false;
        for(CurrencyManipulator manipulator: CurrencyManipulatorFactory.getAllCurrencyManipulators())
        {
            if(manipulator.hasMoney())
            {
                hasMoney = true;
                ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
            }
        }
        if (!hasMoney)
        {
             ConsoleHelper.writeMessage(res.getString("no.money"));
        }

    }
}
