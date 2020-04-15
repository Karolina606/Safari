package safari;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.objects.NativeDebug.map;

public class SafariMap{
    private int widht;
    private int height;
    private Map<Position, SafariObject> map = new HashMap<>();
    private List<Position> freePositions = new LinkedList<>();

    public SafariMap(int width, int height){
        this.widht = width;
        this.height = height;

        // make all position free
        Position tempPosition;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                tempPosition = new Position(j, i);
                freePositions.add(tempPosition);
                //ustawiamy naszą mape
                map.put(tempPosition, null);
            }
        }
        //test:
        showFreePositions();
    }

    public int getWidht() {
        return widht;
    }

    public int getHeight() {
        return height;
    }

    public List<Position> getFreePositions() {
        return freePositions;
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

    public void freeUpPosition(Position position, Safari safari){
        //dodaj ta pozycje do listy wolnych pozycji, usuń zwierzę z listy allAnimals, na jego miejsce w SafariMap wstaw null
        freePositions.add(position);
        safari.getAllAnimals().remove(map.get(position));
        map.put(position, null);
    }

    public SafariObject checkWhatsOnPosition(Position position){
        //tutaj musisz zrobić późniejszą obsługe nullException
        return map.get(position);
    }

    public void placeSafariObject(SafariObject object, Position position, Safari safari){
        //zwalnianie jego starego miejsca
        map.put(object.getPosition(), null);
        //jesli cos bylo na tej pozycji to musi to albo zjesc albo tu nie stawac po prostu
        map.put(position, object);
        freeUpPosition(object.position, safari);
        //zapisanie wewnątrz obiektu nowej pozycji
        object.position = position;
        //zabierz zajeta pozycje z listy wolnych
        freePositions.remove(position);

    }
}
