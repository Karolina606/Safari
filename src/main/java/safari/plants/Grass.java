package safari.plants;

import safari.Position;
import safari.Safari;

public class Grass extends Plant{
    private int quantity = 0;
    /**
     * Makes Grass object and put it on Safari and sets energyValue on 2
     *
     * @param position Position to put the Plant on it
     * @param safari   on this Safari Plant will exists
     */
    public Grass(Position position, Safari safari) {
        super(position, safari);
        super.energyValue = 2;
        quantity++;
    }
}
