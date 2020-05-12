package safari.safariMap;

import safari.safariObjects.SafariObject;
import safari.safariObjects.animals.Animal;

import java.util.*;

/**
 * Class represents 2D map of safari with animals and plants on it
 * SafariMap implements {@link ISafariMap}
 */
public class SafariMap implements ISafariMap {
    private int width;
    private int height;
    private Map<Position, SafariObject> map = new HashMap<>();
    private List<SafariObject> allAnimalsAndHumans = new ArrayList<>();
    private List<Position> allPositions = new LinkedList<>();
    private List<Position> freePositions = new LinkedList<>();

    /**
     * SafariMap constructor
     * @param width width of the SafariMap
     * @param height height of the SafariMap
     */
    public SafariMap(int width, int height){
        this.width = width;
        this.height = height;

        // tworzy mape i ustawia wolne pozycje
        Position tempPosition;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                tempPosition = new Position(j, i);
                allPositions.add(tempPosition);

                //ustawiamy naszą mape, klucze to wszystkie miejsca na niej
                map.put(tempPosition, null);

                //ustawianie także aktualnych wolnych pozycji
                freePositions.add(tempPosition);
            }
        }
    }

    /**
     * Gets SafariMap width
     * @return int SafariMap width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets SafariMap height
     * @return int SafariMap height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets 2D map, HashMap
     * keys: Position
     * values: SafariObject
     * @return Map<Position, SafariObject>
     */
    public Map<Position, SafariObject> getMap(){
        return map;
    }

    /**
     * Gets list of all Animals and Humans on Safari
     * @return List<SafariObject>
     */
    public List<SafariObject> getAllAnimalsAndHumans() {
        return allAnimalsAndHumans;
    }

    /**
     * Gets list of all position on the map
     * @return List<Position>
     */
    public List<Position> getAllPositions() {
        return allPositions;
    }

    /**
     * Gets list of free positions on the map
     * @return List<Position>
     */
    public List<Position> getFreePositions() {
        return freePositions;
    }

    /**
     * Gets one of the free positions
     * @return Position - free position on map or (-1, -1) when there is no free positions
     */
    public Position getFreePosition(){
        // jesli nie ma wolnej pozycji zwraca (-1, -1)
        if(freePositions.isEmpty()){
            return new Position(-1,-1);
        }
        else{
            Random rand = new Random();
            //losuje indeks w zakresie wielkości listy wolnych pozycji
            int randomIndex = rand.nextInt(freePositions.size());
            Position position = freePositions.get(randomIndex);
            removeFromFreePositions(position);
            return position;
        }
    }

    /**
     * Removes given position from freePositions
     * @param position position to remove from list
     */
    public void removeFromFreePositions(Position position){
        if(freePositions.contains(position)){
            freePositions.remove(position);
        }
        else {
            System.out.println("Nie ma takiej pozycji na liscie wolnych: "+ position.toString());
        }
    }

    /**
     * Adds position to list of free positions and removes object on that position
     * @param position to free up
     */
    public void freeUpPosition(Position position){
        if(freePositions.contains(position)){
            System.out.println("Ta pozycja jest już wolna: " + position.toString());
        }
        else{
            //dodaj ta pozycje do listy wolnych pozycji, na jego miejsce w SafariMap wstaw null
            freePositions.add(position);
            map.put(position, null);
        }
    }

    /**
     * Shows free positions
     */
    public void showFreePositions(){
        System.out.println("---- Free positions ----");
        for(Position freePos: freePositions){
            if(freePos == null){
                System.out.println("null");
            }
            else {
                System.out.println(freePos.toString());
            }
        }
        System.out.println();
    }

    /**
     * Puts SafariObject on given position
     * @param object SafariObject to put
     * @param position position to put SafariObject
     */
    // umieszcza obiekt tylko w SafariMap
    public void placeSafariObject(SafariObject object, Position position){

        if(object.getPosition() != null){
            freeUpPosition(object.getPosition());
        }
        //jesli cos bylo na tej pozycji to musi to albo zjesc albo tu nie stawac
        //stawia obiekt na nowe miejsce
        map.put(position, object);
        //zapisanie wewnątrz obiektu nowej pozycji
        object.setPosition(position);

        //System.out.println("Freeplace size: " + freePositions.size());
        //zabierz zajeta pozycje z listy wolnych
        freePositions.remove(position);
        //System.out.println("Freeplace size: " + freePositions.size());
    }

    /**
     * Prints SafariMap
     */
    public void printSafariMap(){
        for(Position pos: map.keySet()){
            if(map.get(pos) instanceof Animal){
                System.out.println(pos.toString() + "-------> "+ map.get(pos) .toString());
            }
            else{
                System.out.println("null");
            }
        }
    }
}
