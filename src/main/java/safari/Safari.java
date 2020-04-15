package safari;
import safari.animals.*;
import safari.human.Human;
import safari.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Safari {
    private int maxIter;
    private static SafariMap map;
    private static List<Animal> allAnimals = new ArrayList<>();

    public Safari(int width, int height){
        map = new SafariMap(width, height);
    }

    public SafariMap getMap(){
        return map;
    }

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }

    public void placeRandomSafariObjects(){

    }

    public static void main(String[] args){
        Safari safari = new Safari(2, 2);
        //--------------------------------------------------------------------------------------------------------------
        Zebra zebra = new Zebra(map.getFreePositions().get(3), safari);
        map.showFreePositions();
        map.placeSafariObject( zebra, map.getFreePositions().get(0), safari);
        map.showFreePositions();
        //---------------------------------------------------------------------------------
    }
}


