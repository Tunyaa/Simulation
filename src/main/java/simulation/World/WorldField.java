package simulation.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import simulation.Model.Entity;

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

    public void getCoordinateByPosition(int position) {

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
    public int getPositionByLineСolumn(int line, int column) {
        return (line - 1) * len + column;
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

}

class RowColumn {

    int row;
    int col;

    public RowColumn(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

}
