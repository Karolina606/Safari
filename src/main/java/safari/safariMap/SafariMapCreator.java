package safari.safariMap;

import safari.safariObjects.animals.Elephant;
import safari.safariObjects.animals.Lion;
import safari.safariObjects.animals.Zebra;
import safari.safariObjects.plants.Grass;
import safari.safariObjects.plants.Tree;

import java.util.Random;

/**
 * It is responsible for making SafariMap setup
 */
public class SafariMapCreator {

    /**
     * Setups SafariMap with Animals of given numbers and random number of grasses and trees (on random positions)
     * If there is no more free positions it prints information about it
     * @param elephants amount of Elephants to place
     * @param zebras amount of Zebras to place
     * @param lions amount of Lions to place
     * @param map SafariMap where SafariObjects will be placed
     */
    public static void placeRandomSafariObjects(int elephants, int zebras, int lions, SafariMap map){
        //mamy 5 roznych rodzajow obiektow do rozmieszcznia Lion, Zebra, Elephant, Tree, Grass
        Random random = new Random(); // pomoze nam w losowaniu
        //niech kazde z nich zajmuje nie wiecej niz 1/5 planszy na poczatku
        int safariSize = map.getHeight()*map.getWidth();

        int startNumberForSafariObjects;
        Position position;
        //lwy
        //startNumberForSafariObjects = random.nextInt(safariSize/6);
        for(int i = 0; i < lions; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else{
                new Lion(position, map);
            }
        }
        //zebry
        //startNumberForSafariObjects = random.nextInt(safariSize/6)+1;
        for(int i = 0; i < zebras; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else{
                new Zebra(position, map);
            }
        }
        //slonie
        //startNumberForSafariObjects = random.nextInt(safariSize/6)+1;
        for(int i = 0; i < elephants; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else{
                new Elephant(position, map);
            }
        }
        //trawy
        startNumberForSafariObjects = random.nextInt(safariSize/4)+1;
        for(int i = 0; i < startNumberForSafariObjects; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else {
                new Grass(position, map);
            }
        }
        //drzewa
        startNumberForSafariObjects = random.nextInt(safariSize/4)+1;
        for(int i = 0; i < startNumberForSafariObjects; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else {
                new Tree(position, map);
            }
        }
    }
}
