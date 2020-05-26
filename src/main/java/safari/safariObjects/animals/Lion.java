package safari.safariObjects.animals;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;
import safari.safariObjects.SafariObject;

/**
 * Class represents lion object in simulation
 * Lion is subclass of {@link Animal}
 */
public class Lion extends Animal{
    /**
     * Quantity of Lions on the safari
     */
    private static int quantity = 0;
    /**
     * List contains number of moves available to make on each of three energyLevel ranges <br>
     * index 0 -> energyLevel range [1,6] <br>
     * index 1 -> energyLevel range [7, 14] <br>
     * index 2 -> energyLevel range [15, up] <br>
     */
    protected static final int[] movesOnEnergy = {1, 4, 5};

    /**
     * Constructor for Lion class <br>
     * Set new Lion position and place it on a map <br>
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
     * Reacts to SafariObject on given position <br>
     * If there is no object relocate to that position <br>
     * If there is a Zebra or an Elephant attack it and relocate to that position <br>
     * If there is another Lion reproduce <br>
     * If there is another SafariObject do not make move it can be dangerous or a tree for example <br>
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
     * Reproduce Animals <br>
     * It means place new Lion on the SafariMap if there is a free position <br>
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
     * Eat given SafariObject <br>
     * Makes the object disappears <br>
     * Increase Lions's energyLevel <br>
     * If it is a Elephant increase it with 8 <br>
     * If it is a Zebra increase it with 6 <br>
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
     * Attack given object <br>
     * Prints information about that <br>
     * Eat given SafariObject <br>
     * @param object SafariObject to attack
     */
    private void attack(SafariObject object){
        System.out.println("Lew atakuje: " + object.getClass().getSimpleName());
        eat(object);
    }

    /**
     * Formats information about Animal to String appropriate to write it later to csv file <br>
     * @return String with information about Animal in format:
     *          class, id, energyLevel, isAsleep, sleepTime, (x y), quantity
     */
    @Override
    public String animalToFile() {
        // class, id, energyLevel, isAsleep, sleepTime, (x;y), quantity
        return super.animalToFile() + ", " + quantity;
    }

    /**
     * Makes the Lion to disappear form SafariMap <br>
     * Decrease Lions' quantity <br>
     * @param map SafariMap where object lives
     */
    @Override
    public void disappear(SafariMap map) {
        super.disappear(this.map);
        quantity--;
    }
}
