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
    private static int quantity = 0;
    //tablica przechowuje ilosc ruchow do wykonania w zadanych stanach energetycznych {0 = [1,6], 1 = [7, 14], 2 = [15, up]}
    //0,1,2 --> indeksy
    //[1,6], [7, 14], [15, up] --> stany energetyczne
    protected static final int[] movesOnEnergy = {1, 2, 3};

    /**
     * Constructor for Elephant class
     * Set new Elephant position and place it on a map
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
     * Reacts to SafariObject on given position
     * If there is no object relocate to that position
     * If there is a Tree eat leaves from it and relocate to that position
     * If there is another Elephant reproduce
     * If there is another SafariObject do not make move it can be dangerous
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
     * Reproduce Animals
     * It means place new Elephant on the SafariMap if there is a free position
     * If there is no more free Positions prints information about that
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
     * Eat leaves from Tree given as a SafariObject
     * Decrease quantity of leaves on thet Tree
     * Increase Elephant's energyLevel
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
            this.energyLevel += tree.getEnergyValue();
            System.out.println("Słoń zjadł liść z drzewa");
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
     * Makes the Elephant to disappear form SafariMap
     * Decrease Elephants' quantity
     * @param map SafariMap where object lives
     */
    @Override
    public void disappear(SafariMap map) {
        super.disappear(this.map);
        quantity--;
    }
}
