package simulation.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import simulation.Model.Entity.Entity;

/**
 *
 * @author tunyaa
 */
// Методы для взаимодействия с мировым полем
public class WorldGrid {

    private int width;     // Ширина поля
    private int height;   // Высота поля
    private int len;         // Количество позиций поля   

    public void createField(int width, int height) {
        this.width = width;
        this.height = height;
        this.len = width * height;
    }

    // Очистить карту !!!!!!!!!!!!!!ИЗМЕНИТЬ УБРАТЬ 0
    public void clearField() {
        this.width = 0;
        this.height = 0;
        this.len = 0;
    }

    // Возвращает позицию по ряд \ колонна
    public int getPositionByRowСolumn(int row, int column) {
        return (row - 1) * width + column;
    }

//    // Возвращает ряд \ колонна по позиции
//    public int[] getRowСolumnByPosition(int position) {
//
//        int[] rowColumn = new int[2];
//        // Присваевает номер линии
//        rowColumn[0] = (int) Math.ceil((double) position / width);
//        // Присваивает номер колонны
//        rowColumn[1] = width - (rowColumn[0] * width - position);
//        return rowColumn;
//    }

    // Проверка: Цель рядом с позицией? (в диапазоне 1й клетки от позиции)
    public boolean isLocatedNearby(int position, int targetPosition) {
        RowColumn relativeRowColumn = getRelativeRowColumn(position, targetPosition);
        if (Math.abs(relativeRowColumn.getCol()) <= 1 && Math.abs(relativeRowColumn.getRow()) <= 1) {
            return true;
        }
        return false;
    }

    // Возвращает координату поля по позиции
    public RowColumn getRowColumnByPosition(int position) {
        int row = (int) Math.ceil((double) position / width);
        int col = width - (row * width - position);
        return new RowColumn(row, col);
    }

    // ДОЛГО?? ПЕРЕДЕЛАТЬ НА [][]
    // Возвращает относительную координату цели от позиции
    public RowColumn getRelativeRowColumn(int position, int targetPosition) {
        RowColumn targetRowColumn = getRowColumnByPosition(targetPosition);
        RowColumn positionRowColumn = getRowColumnByPosition(position);
        int col = targetRowColumn.getCol() - positionRowColumn.getCol();
        int row = targetRowColumn.getRow() - positionRowColumn.getRow();
        return new RowColumn(row, col);
    }

    // Возвращает середину между позицией и целью
    public int getMidPosition(int position, int targetPosition) {

        RowColumn relativeRowColumn = getRelativeRowColumn(position, targetPosition);

        
        int row = relativeRowColumn.getRow() / 2;
        int col = relativeRowColumn.getCol() / 2;
        relativeRowColumn.setRow(row);
        relativeRowColumn.setCol(col);

        targetPosition = position + col + (width * row);
        return targetPosition;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWorldLen() {
        return len;
    }

}
