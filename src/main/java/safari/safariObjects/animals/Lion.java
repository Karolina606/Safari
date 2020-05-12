package safari.safariObjects.animals;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;
import safari.safariObjects.SafariObject;

/**
 * Class represents lion object in simulation
 * Lion is subclass of {@link Animal}
 */
public class Lion extends Animal{
    private static int quantity = 0;
    //tablica przechowuje ilosc ruchow do wykonania w zadanych stanach energetycznych {0 = [1,6], 1 = [7, 14], 2 = [15, up]}
    //0,1,2 --> indeksy
    //[1,6], [7, 14], [15, up] --> stany energetyczne
    protected static final int[] movesOnEnergy = {1, 4, 5};

    /**
     * Constructor for Lion class
     * Set new Lion position and place it on a map
     * @param position Position to put new Zebra
     * @param map SafariMap where new Zebra lives
     */
    public Lion(Position position,SafariMap map){
        super(position, map);
        quantity++;
    }

    /**
     * Makes Lion to take some action
     * @param movesOnEnergy tab with number of moves allowed in all energyLevel (there are 3 energy levels [1,6], [7, 14], [15, up])
     */
    @Override
    public void makeAction(int[] movesOnEnergy) {
        super.makeAction(movesOnEnergy);
    }

    /**
     * Makes Lion to take some action
     */
    @Override
    public void makeAction(){
        makeAction(movesOnEnergy);
    }

    /**
     * Reacts to SafariObject on given position
     * If there is no object relocate to that position
     * If there is a Zebra or an Elephant attack it and relocate to that position
     * If there is another Lion reproduce
     * If there is another SafariObject do not make move it can be dangerous or a tree for example
     * @param position Position to react
     */
    @Override
    protected void react(Position position) {
        //pobierz obiekt ktory jest na tej pozycji
        SafariObject object = this.map.getMap().get(position);

        //reaguj na to co znajduje sie na tej pozycji
        if(object == null){
            this.map.placeSafariObject(this, position);
            decreaseEnergy();
        }
        else if(object instanceof Zebra || object instanceof Elephant){
            attack(object);
            this.map.placeSafariObject(this, position);
            decreaseEnergy();
        }
        else if(object instanceof Lion && position != this.getPosition()){
            reproduce();
            decreaseEnergy();
        }
    }

    /**
     * Reproduce Animals
     * It means place new Lion on the SafariMap if there is a free position
     * If there is no more free Positions prints information about that
     */
    @Override
    protected void reproduce() {
        //sprawdz czy jest wolna pozycja na safari
        Position freePosition = Position.randomFreePosition(this.map);
        if(freePosition.equals(-1, -1)){
            System.out.println("Nie ma wolnych pozycji, nie można umieścić nowego lwa");
        }
        else{
            //dodanie nowego zwierzaka pod wskazaną pozycję i do listy zwierzaków
            new Lion(freePosition, map);
        }
    }

    /**
     * Eat given SafariObject
     * Makes the object disappears
     * Increase Lions's energyLevel
     * If it is a Elephant increase it with 8
     * If it is a Zebra increase it with 6
     * @param object SafariObject to eat
     */
    @Override
    protected void eat(SafariObject object) {
        //dodawanie energii z jedzenia, słoń daje więcej energii, zebra mniej
        if(object instanceof Elephant){
            energyLevel += 8;
        }
        else if(object instanceof Zebra){
            energyLevel += 6;
        }
        object.disappear(this.map);
    }

    /**
     * Attack given object
     * Prints information about that
     * Eat given SafariObject
     * @param object SafariObject to attack
     */
    private void attack(SafariObject object){
        System.out.println("Lew atakuje: " + object.getClass().getSimpleName());
        eat(object);
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
     * Makes the Lion to disappear form SafariMap
     * Decrease Lions' quantity
     * @param map SafariMap where object lives
     */
    @Override
    public void disappear(SafariMap map) {
        super.disappear(this.map);
        quantity--;
    }
}
