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
    private Map<Integer, Entity> positionEntityMap; // Позиция - Сущность (Некоторые сущности могут занимать одну клетку)

    public void createMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.len = width * height;
    }

    // Очистить карту
    public void clearMap() {
        this.width = 0;
        this.height = 0;
        this.len = 0;
        this.positionEntityMap.clear();
    }
    
    public void getCoordinateByPosition(int position){
        
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
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLen() {
        return len;
    }

    public Map<Integer, Entity> getPositionEntityMap() {
        return positionEntityMap;
    }

    public void setPositionEntityMap(Map<Integer, Entity> positionEntityMap) {
        this.positionEntityMap = positionEntityMap;
    }

}
