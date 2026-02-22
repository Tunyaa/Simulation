package simulation.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tunyaa
 */
public class WorldMap {

    private final int width;
    private final int height;
    private final int len;
    private List<Integer> positions;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        len = width * height;
        positions = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            positions.add(i);
        }
    }

    public void showMap() {
        int l = 1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
//                if (l == point) {
//                    System.out.print("8 ");
//
//                } else {
//                    System.out.print(0 + " ");
//                }
                l++;

            }
            System.out.println("");
        }
    }

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

    public List<Integer> getPositions() {
        return positions;
    }
    
    
}
