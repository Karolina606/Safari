package safari.safariObjects.plants;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;

/**
 * Class represents tree object in simulation
 * Tree is subclass of {@link Plant}
 */
public class Tree extends Plant{
    private int quantity = 0;
    private int leavesQuantity;
    /**
     * Makes Tree object and put it on Safari, sets energyValue on 1 and leavesQuantity to 20
     *
     * @param position Position to put the Plant on it
     * @param map SafariMap where Tree is going to be placed
     */
    public Tree(Position position, SafariMap map) {
        super(position, map);
        super.energyValue = 1;
        leavesQuantity = 20;
        quantity++;
    }

    /**
     * Gets leavesQuantity on this tree
     * @return leavesQuantity
     */
    public int getLeavesQuantity() {
        return leavesQuantity;
    }

    /**
     * Decreases leavesQuantity, amount of leaves on this tree
     */
    public void decreaseLeavesQuantity(){
        //co jesli nie ma juz lisci
        if(leavesQuantity <= 0){
            System.out.println("There is no more leaves on this tree");
        }
        //jesli liscie jeszcze sa na drzewie
        else{
            leavesQuantity--;
        }
    }
}
