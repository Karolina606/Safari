/**
 * Animals
 */
package safari.safariObjects.animals;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;
import safari.safariObjects.SafariObject;
import safari.safariObjects.plants.Tree;

/**
 * Class represents elephant object
 * Elephant is subclass of {@link Animal}
 */
public class Elephant extends Animal{
    /**
     * Quantity of all Elephants on the safari
     */
    private static int quantity = 0;
    /**
     * List contains number of moves available to make on each of three energyLevel ranges <br>
     * index 0 -> energyLevel range [1,6] <br>
     * index 1 -> energyLevel range [7, 14] <br>
     * index 2 -> energyLevel range [15, up] <br>
     */
    protected static final int[] movesOnEnergy = {1, 2, 3};

    /**
     * Constructor for Elephant class <br>
     * Set new Elephant position and place it on a map <br>
     * @param position Position to put new Zebra
     * @param map SafariMap where new Zebra lives
     */
    public Elephant(Position position,  SafariMap map){
        super(position, map);
        quantity++;
    }

    /**
     * Makes Elephant to take some action
     * @param movesOnEnergy tab with number of moves allowed in all energyLevel (there are 3 energy levels [1,6], [7, 14], [15, up])
     */
    @Override
    public void makeAction(int[] movesOnEnergy) {
        super.makeAction(movesOnEnergy);
    }

    /**
     * Makes Elephant to take some action
     */
    @Override
    public void makeAction(){
        makeAction(movesOnEnergy);
    }

    /**
     * Reacts to SafariObject on given position <br>
     * If there is no object relocate to that position <br>
     * If there is a Tree eat leaves from it and relocate to that position <br>
     * If there is another Elephant reproduce <br>
     * If there is another SafariObject do not make move it can be dangerous <br>
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
        else if(object instanceof Tree){
            eat(object);
        }
        else if(object instanceof Elephant && position != this.getPosition()){
            reproduce();
        }
    }

    /**
     * Reproduce Animals <br>
     * It means place new Elephant on the SafariMap if there is a free position <br>
     * If there is no more free Positions prints information about that <br>
     */
    @Override
    protected void reproduce() {
        //sprawdz czy jest wolna pozycja na safari
        Position freePosition = Position.randomFreePosition(this.map);
        if(freePosition.equals(-1, -1)){
            System.out.println("Nie ma wolnych pozycji, nie można umieścić nowego słonia");
        }
        else{
            //dodanie nowego zwierzaka pod wskazaną pozycję i do listy zwierzaków
            new Elephant(freePosition, this.map);
        }
    }

    /**
     * Eat leaves from Tree given as a SafariObject <br>
     * Decrease quantity of leaves on thet Tree <br>
     * Increase Elephant's energyLevel <br>
     * @param object SafariObject actually a Tree object
     */
    @Override
    protected void eat(SafariObject object) {
        //down casting
        Tree tree = (Tree) object;
        //sprawdz czy ma jeszcze liscie
        if(tree.getLeavesQuantity() > 0){
            //zjedz liscia z drzewa
            tree.decreaseLeavesQuantity();
            //dodaj sobie energii
            if(tree.getEnergyValue() != 0) {
                this.energyLevel += tree.getEnergyValue();
                System.out.println("Słoń zjadł liść z drzewa");
            }
        }
    }

    /**
     * Formats information about Animal to String appropriate to write it later to csv file
     * @return String with information about Animal in format:
     *          class, id, energyLevel, isAsleep, sleepTime, (x y), quantity
     */
    @Override
    public String animalToFile(){
        // class, id, energyLevel, isAsleep, sleepTime, (x;y), quantity
        return super.animalToFile() + ", " + quantity;
    }

    /**
     * Makes the Elephant to disappear form SafariMap <br>
     * Decrease Elephants' quantity <br>
     * @param map SafariMap where object lives
     */
    @Override
    public void disappear(SafariMap map) {
        super.disappear(this.map);
        quantity--;
    }
}
