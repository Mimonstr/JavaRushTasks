package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller
{
    private View view; // Представления
    private HTMLDocument document; // Модель
    private File currentFile; // текущий файл

    public Controller(View view)
    {
        this.view = view;
    }

    public HTMLDocument getDocument()
    {
        return document;
    }

    // Инициализация
    public void init()
    {
        createNewDocument();
    }
    public void exit()
    {
        System.exit(0);
    }

    public void resetDocument()
    {
        UndoListener undoListener = view.getUndoListener();
        if (document != null)
        {
            document.removeUndoableEditListener(undoListener);
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(undoListener);
        view.update();

    }
    public void setPlainText(String text)
    {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try
        {
            new HTMLEditorKit().read(stringReader,document, 0);
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }

    }
    public String getPlainText()
    {
        StringWriter stringWriter = new StringWriter();
        try
        {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public static void main(String[] args)
    {
        // Создаем объект представления
        View view = new View();
        
        // Создаем контроллер, используя представление
        Controller controller = new Controller(view);

        // Устанавливаем у представления контроллер
        view.setController(controller);

        // Инициализируем представление
        view.init();
        // Инициализируем контроллер
        controller.init();

    }

    public void createNewDocument()
    {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        currentFile = null;
    }

    public void openDocument()
    {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        if (jFileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION)
        {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try
            {
                FileReader fileReader = new FileReader(currentFile);
                new HTMLEditorKit().read(fileReader, document, 0);
                view.resetUndo();
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
        }

    }

    public void saveDocument()
    {
        if(currentFile == null) saveDocumentAs();
        else
        {
            view.selectHtmlTab();
            view.setTitle(currentFile.getName());
            try
            {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs()
    {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION)
        {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try
            {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());

            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }

        }
    }
}
