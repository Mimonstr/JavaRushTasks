package com.javarush.task.task40.task4004;

import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point
{
    public int x;
    public int y;

    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

public class Solution
{
    public static void main(String[] args)
    {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon)
    {
        int intersections = 0;
        int n = polygon.size();

        for (int i = 0; i < n; i++)
        {
            Point p1 = polygon.get(i);
            Point p2 = polygon.get((i + 1) % n);

            // Проверяем, лежит ли точка на горизонтальной линии между вершинами
            if (point.y == p1.y && point.y == p2.y)
            {
                if ((point.x >= Math.min(p1.x, p2.x)) && (point.x <= Math.max(p1.x, p2.x)))
                {
                    return false; // Граничная точка
                }
            }

            // Проверяем пересечение луча с ребром многоугольника
            if ((point.y > Math.min(p1.y, p2.y)) && (point.y <= Math.max(p1.y, p2.y)) &&
                    (point.x <= Math.max(p1.x, p2.x)) && (p1.y != p2.y))
            {
                double xIntersection = (point.y - p1.y) * (p2.x - p1.x) / (p2.y - p1.y) + p1.x;
                if (p1.x == p2.x || point.x <= xIntersection)
                {
                    intersections++;
                }
            }
        }

        return intersections % 2 != 0;
    }

}

