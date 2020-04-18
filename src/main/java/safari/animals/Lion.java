package safari.animals;

import safari.Position;
import safari.SafariMap;
import safari.SafariObject;
import safari.human.Human;

public class Lion extends Animal{
    private static int quantity = 0;

    public Lion(Position position,SafariMap map){
        super(position, map);
        quantity++;
    }

    @Override
    public void makeAction(SafariMap map) {
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
        else if(object instanceof Zebra || object instanceof Elephant || object instanceof Human){
            attack(object, map);
        }
        else if(object instanceof Lion){
            reproduction(map);
        }
        else{
            System.out.println("Lew nie wykonał ruchu");
        }
    }

    @Override
    protected void reproduction(SafariMap map) {
        //sprawdz czy jest wolna pozycja na safari
        Position freePosition = Position.randomFreePosition(map);
        if(freePosition.equals(-1, -1)){
            System.out.println("Nie ma wolnych pozycji, nie można umieścić nowego lwa");
        }
        else{
            //dodanie nowego zwierzaka pod wskazaną pozycję i do listy zwierzaków
            new Lion(freePosition, map);
            System.out.println("Młode lwiątko na pozycję: " + freePosition.toString());
        }
    }

    protected void eat(SafariObject object, SafariMap map) {
        //dodawanie energii z jedzenia, słoń daje najwięcej energii, zebra mniej, człowiek najmniej
        if(object instanceof Elephant){
            energyLevel += 8;
        }
        else if(object instanceof Zebra){
            energyLevel += 6;
        }
        else{
            //to musi być człowiek
            energyLevel += 3;
        }
        object.disappear(map);
    }

    private void attack(SafariObject object, SafariMap map){
        System.out.println("Lew atakuje: " + object.getClass().getSimpleName());
        eat(object, map);
    }
}
