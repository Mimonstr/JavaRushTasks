package com.javarush.task.task34.task3410.model;

/**
 *  Игровые объекты типа "дом" не поддерживают логики столкновений
 *  (игрок или ящики могут свободно передвигаться по ним).
 *  Что касается остальных объектов, то они не должны проходить сквозь друг друга,
 *  они должны сталкиваться. Например, ящик нельзя протолкнуть сквозь стену.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }
    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        int nextX = this.getX();
        int nextY = this.getY();

        // Рассчитываем координаты следующей позиции в соответствии с направлением
        switch (direction)
        {
            case LEFT:
                nextX -= Model.FIELD_CELL_SIZE;
                break;
            case RIGHT:
                nextX += Model.FIELD_CELL_SIZE;
                break;
            case UP:
                nextY -= Model.FIELD_CELL_SIZE;
                break;
            case DOWN:
                nextY += Model.FIELD_CELL_SIZE;
                break;
        }

        // Проверяем, совпадают ли координаты следующей позиции с координатами объекта gameObject
        return (nextX == gameObject.getX() && nextY == gameObject.getY());
    }
}
