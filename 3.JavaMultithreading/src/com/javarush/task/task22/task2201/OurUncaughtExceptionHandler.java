package com.javarush.task.task22.task2201;

public class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
        final String string = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName()))
        {
            System.out.println(getFormattedStringForFirstThread(t, e, string));
        }
        else if (Solution.SECOND_THREAD_NAME.equals(t.getName()))
        {
            System.out.println(getFormattedStringForSecondThread(t, e, string));
        }
        else
        {
            System.out.println(getFormattedStringForOtherThread(t, e, string));
        }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String string)
    {
        //RuntimeException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : 3#

        return String.format(string, e.getClass().getSimpleName(), e.getCause(), t.getName());
    }

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String string)
    {
        //java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : StringForSecondThreadTooShortException : 2#
        return String.format(string,e.getCause(), e.getClass().getSimpleName(), t.getName());
    }

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String string)
    {
       // 1# : StringForFirstThreadTooShortException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1
       // return String.format(string,t.getName(), e.toString().substring(e.toString().lastIndexOf('.')+1), e.getMessage());
        return String.format(string, t.getName(), e.getClass().getSimpleName(), e.getCause());
    }
}

