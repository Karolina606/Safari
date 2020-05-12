package safari.safariObjects.animals;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;
import safari.safariObjects.SafariObject;
import safari.safariObjects.plants.Grass;

/**
 * Class represents zebra object in simulation
 * Zebra is subclass of {@link Animal}
 */
public class Zebra extends Animal{
    private static int quantity = 0;
    //tablica przechowuje ilosc ruchow do wykonania w zadanych stanach energetycznych {0 = [1,6], 1 = [7, 14], 2 = [15, up]}
    //0,1,2 --> indeksy
    //[1,6], [7, 14], [15, up] --> stany energetyczne
    protected static final int[] movesOnEnergy = {1, 3, 4};

    /**
     * Constructor for Zebra class
     * Set new Zebra position and place it on a map
     * @param position Position to put new Zebra
     * @param map SafariMap where new Zebra lives
     */
    public Zebra(Position position, SafariMap map){
        super(position, map);
        quantity++;
    }

    /**
     * Makes Zebra to make some action
     * @param movesOnEnergy tab with number of moves allowed in all energyLevel (there are 3 energy levels [1,6], [7, 14], [15, up])
     */
    @Override
    public void makeAction(int[] movesOnEnergy) {
        super.makeAction(movesOnEnergy);
    }

    /**
     * Makes Zebra to take some action
     */
    @Override
    public void makeAction(){
        makeAction(movesOnEnergy);
    }

    /**
     * Reacts to SafariObject on given position
     * If there is no object relocate to that position
     * If there is a Grass eat it and relocate to that position
     * If there is another Zebra reproduce
     * If there is another SafariObject do not make move it can be dangerous
     * @param position Position to react
     */
    @Override
    protected void react(Position position) {
        //pobierz obiekt ktory jest na tej pozycji
        SafariObject object = map.getMap().get(position);

        //reaguj na to co znajduje sie na tej pozycji
        if(object == null){
            map.placeSafariObject(this, position);
            decreaseEnergy();
        }
        else if(object instanceof Grass){
            eat(object);
            map.placeSafariObject(this, position);
            decreaseEnergy();
        }
        else if(object instanceof Zebra && position != this.getPosition()){
            reproduce();
            decreaseEnergy();
        }
    }

    /**
     * Reproduce Animals
     * It means place new Zebra on the SafariMap if there is a free position
     * If there is no more free Positions prints information about that
     */
    @Override
    protected void reproduce() {
        //sprawdz czy jest wolna pozycja na safari
        Position freePosition = Position.randomFreePosition(map);
        if(freePosition.equals(-1, -1)){
            System.out.println("Nie ma wolnych pozycji, nie można umieścić nowej zebry");
        }
        else{
            //dodanie nowego zwierzaka pod wskazaną pozycję i do listy zwierzaków
            new Zebra(freePosition, map);
        }
    }

    /**
     * Eat given SafariObject
     * Makes the object disappears
     * Increase Zebra's energyLevel
     * @param object SafariObject to eat
     */
    protected void eat(SafariObject object) {
        //down casting
        Grass grass = (Grass) object;
        //zjada trawe wiec jej energia rosnie
        energyLevel += grass.getEnergyValue();
        grass.disappear(map);
    }

    /**
     * Formats information about Animal to String appropriate to write it later to csv file
     * @return String with information about Animal in format:
     *          class, id, energyLevel, isAsleep, sleepTime, (x y), quantity
     */
    @Override
    public String animalToFile() {
        // class, id, energyLevel, isAsleep, sleepTime, (x;y), quantity
        return super.animalToFile() + ", " + quantity;
    }

    /**
     * Makes the Zebra to disappear form SafariMap
     * Decrease Zabras' quantity
     * @param map SafariMap where object lives
     */
    @Override
    public void disappear(SafariMap map) {
        super.disappear(map);
        quantity--;
    }
}
