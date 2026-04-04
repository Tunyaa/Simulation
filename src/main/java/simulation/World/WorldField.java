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
public class WorldField {

    private int width;     // Ширина поля
    private int height;   // Высота поля
    private int len;         // Количество позиций поля   

    public void createField(int width, int height) {
        this.width = width;
        this.height = height;
        this.len = width * height;
    }

    // Очистить карту
    public void clearField() {
        this.width = 0;
        this.height = 0;
        this.len = 0;
    }

    // Движения по полю побавить движение по диагонали!!!!!
//    public void moveTo(String direction) {
//        int newPoint;
//        if (direction.equals("w")) {
//            newPoint = point - width;
//            if (newPoint >= 1) {
//                point = newPoint;
//            }
//        }
//        if (direction.equals("s")) {
//            newPoint = point + width;
//            if (point < len) {
//                point = newPoint;
//            }
//        }
//        if (direction.equals("a")) {
//            newPoint = point - 1;
//            if (newPoint > (width * (point / width))) {
//                point = newPoint;
//            }
//        }
//        if (direction.equals("d")) {
//            if (point < (width * ((point - 1) / width + 1))) {
//                point += 1;
//            }
//        }
//    }
    // ручное передвижение
//    public void render() throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String s;
//        while (true) {
//            showMap();
//            System.out.println("point - " + point);
//            s = reader.readLine();
//            if (s.equals("q")) {
//                return;
//            }
//            moveTo(s);
//
//        }
//    }
    // Возвращает позицию по ряд \ колонна
    public int getPositionByRowСolumn(int row, int column) {
        return (row - 1) * len + column;
    }

    // Возвращает ряд \ колонна по позиции
    public int[] getRowСolumnByPosition(int position) {

        int[] rowColumn = new int[2];
        // Присваевает номер линии
        rowColumn[0] = (int) Math.ceil((double) position / width);
        // Присваивает номер колонны
        rowColumn[1] = width - (rowColumn[0] * width - position);
        return rowColumn;
    }

    public RowColumn getRowColumnByPosition(int position) {
        int row = (int) Math.ceil((double) position / width);
        int col = width - (row * width - position);
        return new RowColumn(row, col);
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
// ДОЛГО?? ПЕРЕДЕЛАТЬ НА [][]
    public RowColumn getRelativeRowColumn(int position, int targetPosition) {
        RowColumn targetRowColumn = getRowColumnByPosition(targetPosition);
        RowColumn positionRowColumn = getRowColumnByPosition(position);
        int col = targetRowColumn.getCol() - positionRowColumn.getCol();
        int row = targetRowColumn.getRow() - positionRowColumn.getRow();
        return new RowColumn(row, col);
    }

}
