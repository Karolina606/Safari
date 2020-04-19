package safari.animals;

import safari.Position;
import safari.SafariMap;
import safari.SafariObject;

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
            decreaseEnergy();
        }
        else if(object instanceof Zebra || object instanceof Elephant){
            attack(object, map);
            map.placeSafariObject(this, position);
            decreaseEnergy();
        }
        else if(object instanceof Lion && position != this.getPosition()){
            reproduction(map);
            decreaseEnergy();
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
        }
    }

    protected void eat(SafariObject object, SafariMap map) {
        //dodawanie energii z jedzenia, słoń daje więcej energii, zebra mniej
        if(object instanceof Elephant){
            energyLevel += 8;
        }
        else if(object instanceof Zebra){
            energyLevel += 6;
        }
        object.disappear(map);
    }

    private void attack(SafariObject object, SafariMap map){
        System.out.println("Lew atakuje: " + object.getClass().getSimpleName());
        eat(object, map);
    }

    @Override
    public String objectToFile() {
        // class, id, energyLevel, isAsleep, sleepTime, (x;y), quantity
        return super.objectToFile() + ", " + quantity;
    }

    @Override
    public void disappear(SafariMap map) {
        super.disappear(map);
        quantity--;
    }
}
