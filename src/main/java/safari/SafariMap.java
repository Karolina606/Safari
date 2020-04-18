package safari;

import safari.animals.Animal;

import java.util.*;

public class SafariMap{
    private int width;
    private int height;
    private Map<Position, SafariObject> map = new HashMap<>();
    private List<SafariObject> allAnimalsAndHumans = new ArrayList<>();
    private List<Position> allPositions = new LinkedList<>();
    private List<Position> freePositions = new LinkedList<>();

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
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<Position, SafariObject> getMap(){
        return map;
    }

    public List<SafariObject> getAllAnimalsAndHumans() {
        return allAnimalsAndHumans;
    }

    public List<Position> getFreePositions() {
        return freePositions;
    }
    public List<Position> getAllPositions() {
        return allPositions;
    }

    public void removeFromFreePositions(Position position){
        if(freePositions.contains(position)){
            freePositions.remove(position);
        }
        else {
            System.out.println("Nie ma takiej pozycji na liscie wolnych: "+ position.toString());
        }
    }

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

    public Position getFreePosition(){
        // jesli nie ma wolnej pozycji zwraca (-1, -1)
        if(freePositions.isEmpty()){
            return new Position(-1,-1);
        }
        else{
            Random rand = new Random();
            //losuje indeks w zakresie wielkości listy wolnych pozycji
            int randomIndex = rand.nextInt(freePositions.size());
            return freePositions.get(randomIndex);
        }
    }

    // umieszcza obiekt tylko w SafariMap
    public void placeSafariObject(SafariObject object, Position position){
        if(object.getPosition() != null){
            freeUpPosition(object.getPosition());
        }
        //jesli cos bylo na tej pozycji to musi to albo zjesc albo tu nie stawac po prostu
        //stawia obiekt na nowe miejsce
        map.put(position, object);
        //zapisanie wewnątrz obiektu nowej pozycji
        object.position = position;
        //System.out.println("Freeplace size: " + freePositions.size());
        //zabierz zajeta pozycje z listy wolnych
        freePositions.remove(position);
        //System.out.println("Freeplace size: " + freePositions.size());
    }

    public void printSafariMap(){
        for(SafariObject obj: map.values()){
            if(obj instanceof Animal){
                System.out.println(obj.toString());
            }
            else{
                System.out.println("null");
            }
        }
    }
}
