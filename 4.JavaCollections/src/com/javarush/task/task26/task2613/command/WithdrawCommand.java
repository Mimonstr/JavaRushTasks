package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int expectedAmount;
        do
        {
            try
            {
                //ConsoleHelper.writeMessage("Enter the amount:");
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                expectedAmount = Integer.parseInt(ConsoleHelper.readString());
                if (!manipulator.isAmountAvailable(expectedAmount))
                {
                    //ConsoleHelper.writeMessage("Insufficient funds.");
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
                Map<Integer, Integer> withdrawResult = manipulator.withdrawAmount(expectedAmount);
                ConsoleHelper.writeMessage("Transaction successful. Withdrawn banknotes:");
                for (Map.Entry<Integer, Integer> entry : withdrawResult.entrySet())
                {
                    //ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(), entry.getValue()));
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),entry.getKey(), entry.getValue()));
                }
                break;
            }
            catch (NumberFormatException e)
            {
                //ConsoleHelper.writeMessage("Invalid amount format.");
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
            catch (NotEnoughMoneyException e)
            {
                //ConsoleHelper.writeMessage("Insufficient funds.");
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }while (true);

    }
}
