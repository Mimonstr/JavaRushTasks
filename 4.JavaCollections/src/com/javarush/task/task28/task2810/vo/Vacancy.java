package com.javarush.task.task28.task2810.vo;

import com.javarush.task.task28.task2810.model.Strategy;

import java.net.URL;
import java.util.Objects;

public class Vacancy
{
    private String title;
    private String salary;
    private String city;
    private String companyName;
    private String siteName;
    private String url;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(title, vacancy.title) && Objects.equals(salary, vacancy.salary) && Objects.equals(city, vacancy.city) && Objects.equals(companyName, vacancy.companyName) && Objects.equals(siteName, vacancy.siteName) && Objects.equals(url, vacancy.url);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(title, salary, city, companyName, siteName, url);
    }

    /**
     * SETTERS
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }


    /**
     * GETTERS
     */
    public String getTitle()
    {
        return title;
    }

    public String getSalary()
    {
        return salary;
    }

    public String getCity()
    {
        return city;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public String getSiteName()
    {
        return siteName;
    }

    public String getUrl()
    {
        return url;
    }
}
