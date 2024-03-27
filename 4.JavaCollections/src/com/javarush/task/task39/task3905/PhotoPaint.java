package com.javarush.task.task39.task3905;

public class PhotoPaint
{
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor)
    {

        // Проверяем, находится ли начальная точка в пределах изображения
        if (x < 0 || x >= image[0].length || y < 0 || y >= image.length)
        {
            return false;
        }

        // Проверяем, совпадает ли цвет начальной точки с целевым цветом заливки
        if (image[y][x] == desiredColor)
        {
            return false;
        }

        // Запускаем заливку
        fill(image, x, y, image[y][x], desiredColor);
        return true;
    }

    private void fill(Color[][] image, int x, int y, Color startColor, Color targetColor)
    {
        // Проверяем, находится ли точка в пределах изображения
        if (x < 0 || x >= image[0].length || y < 0 || y >= image.length)
        {
            return;
        }

        // Проверяем, совпадает ли цвет точки с начальным цветом
        if (image[y][x] != startColor)
        {
            return;
        }

        // Устанавливаем цвет точки в целевой цвет заливки
        image[y][x] = targetColor;

        // Рекурсивно вызываем fill для соседних точек
        fill(image, x - 1, y, startColor, targetColor); // Левая точка
        fill(image, x + 1, y, startColor, targetColor); // Правая точка
        fill(image, x, y - 1, startColor, targetColor); // Верхняя точка
        fill(image, x, y + 1, startColor, targetColor); // Нижняя точка
    }
}
