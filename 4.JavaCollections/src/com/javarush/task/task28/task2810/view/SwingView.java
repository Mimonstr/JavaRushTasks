package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SwingView implements View
{
    private Controller controller;
    private JFrame frame;
    private JTextField searchField;
    private JTextArea resultArea;

    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html";


    public SwingView() {
        frame = new JFrame("Vacancy Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(10, 20, 80, 25);
        panel.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(100, 20, 165, 25);
        panel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(280, 20, 80, 25);
        panel.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setBounds(10, 60, 350, 180);
        panel.add(resultArea);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchString = searchField.getText();
                controller.onCitySelect(searchString);
            }
        });
    }

//    @Override
//    public void update(List<Vacancy> vacancies)
//    {
//        System.out.println("update");
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Vacancy vacancy : vacancies) {
//            stringBuilder.append("Title: ").append(vacancy.getTitle()).append("\n");
//            stringBuilder.append("Company: ").append(vacancy.getCompanyName()).append("\n");
//            stringBuilder.append("City: ").append(vacancy.getCity()).append("\n");
//            stringBuilder.append("Salary: ").append(vacancy.getSalary()).append("\n");
//            stringBuilder.append("URL: ").append(vacancy.getUrl()).append("\n");
//            stringBuilder.append("\n");
//        }
//        resultArea.setText(stringBuilder.toString());
//    }

    @Override
    public void update(List<Vacancy> vacancies)
    {
        try
        {
            String newContent = getUpdatedFileContent(vacancies);
            System.out.println(newContent);
            updateFile(newContent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies)
    {
        try
        {
            Document document = getDocument();
            Elements templateHidden = document.getElementsByClass("template");
            Element template = templateHidden.clone().removeAttr("style").removeClass("template").get(0);

            //remove all prev vacancies
            Elements prevVacancies = document.getElementsByClass("vacancy");

            for (Element redundant : prevVacancies)
            {
                if (!redundant.hasClass("template"))
                {
                    redundant.remove();
                }
            }

            //add new vacancies
            for (Vacancy vacancy : vacancies)
            {
                Element vacancyElement = template.clone();

                Element vacancyLink = vacancyElement.getElementsByAttribute("href").get(0);
                vacancyLink.appendText(vacancy.getTitle());
                vacancyLink.attr("href", vacancy.getUrl());
                Element city = vacancyElement.getElementsByClass("city").get(0);
                city.appendText(vacancy.getCity());
                Element companyName = vacancyElement.getElementsByClass("companyName").get(0);
                companyName.appendText(vacancy.getCompanyName());
                Elements salaryEls = vacancyElement.getElementsByClass("salary");
                Element salary = salaryEls.get(0);
                salary.appendText(vacancy.getSalary());

                templateHidden.before(vacancyElement.outerHtml());
            }
            return document.html();
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
        return "Some exception occurred";
    }

    protected Document getDocument() throws IOException
    {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
    private void updateFile(String content)
    {
        try(FileWriter fileWriter = new FileWriter(filePath))
        {
            fileWriter.write(content);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void show() {
        frame.setVisible(true);
    }
}
