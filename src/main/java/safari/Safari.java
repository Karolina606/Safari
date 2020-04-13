package safari;
import safari.animals.*;
import safari.human.Human;
import safari.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Safari {
    int width;
    int height;

    // 2D tab of objects on safari
    Object[][] safari;
    List<Animal> allAnimals = new ArrayList<>();

    public Safari(int width, int height){
        this.width = width;
        this.height = height;
        this.safari = new Object[width][height];
    }

    public static void main(String[] args){
        Safari safari1 = new Safari(10, 10);
        Random r = new Random();
        int x, y;
        for(int i = 0; i<=2; i++){
            do{
                x = r.nextInt(10);
                y = r.nextInt(10);
            }while(safari1.safari[x][x] != null);

            safari1.safari[x][y] = (Animal) new Zebra(x, y);
            safari1.allAnimals.add((Animal) safari1.safari[x][y]);
            System.out.println(safari1.allAnimals.get(i).positionToString() + " Now we are: " + Zebra.getQuantity());
        }
    }
}


