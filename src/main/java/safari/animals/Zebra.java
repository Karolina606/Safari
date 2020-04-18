package safari.animals;

import safari.Position;
import safari.SafariMap;
import safari.SafariObject;
import safari.plants.Grass;

public class Zebra extends Animal{
    private static int quantity = 0;

    public static int getQuantity() {
        return quantity;
    }

    public Zebra(Position position, SafariMap map){
        super(position, map);
        quantity++;
    }

    @Override
    public void makeAction(SafariMap map){
        super.makeAction(map, 2);
    }

    @Override
    protected void react(Position position, SafariMap map) {
        //pobierz obiekt ktory jest na tej pozycji
        SafariObject object = map.getMap().get(position);

        //reaguj na to co znajduje sie na tej pozycji
        if(object == null){
            map.placeSafariObject(this, position);
        }
        else if(object instanceof Grass){
            eat(object, map);
        }
        else if(object instanceof Zebra){
            reproduction(map);
        }
        else{
            System.out.println("Zebra nie wykonała ruchu");
        }
    }

    @Override
    protected void reproduction(SafariMap map) {
        //sprawdz czy jest wolna pozycja na safari
        Position freePosition = Position.randomFreePosition(map);
        if(freePosition.equals(-1, -1)){
            System.out.println("Nie ma wolnych pozycji, nie można umieścić nowej zebry");
        }
        else{
            //dodanie nowego zwierzaka pod wskazaną pozycję i do listy zwierzaków
            new Zebra(freePosition, map);
            System.out.println("Młoda zebra na pozycję: " + freePosition.toString());
        }
    }

    protected void eat(SafariObject object, SafariMap map) {
        //down casting
        Grass grass = (Grass) object;
        //zjada trawe wiec jej energia rosnie
        energyLevel += grass.getEnergyValue();
        grass.disappear(map);
    }

}
