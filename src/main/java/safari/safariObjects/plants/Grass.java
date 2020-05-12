package safari.safariObjects.plants;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;

/**
 * Class represents grass object in simulation
 * Grass is subclass of {@link Plant}
 */
public class Grass extends Plant{
    private int quantity = 0;
    /**
     * Makes Grass object and put it on Safari and sets energyValue on 2
     *
     * @param position Position to put the Plant on it
     * @param map SafariMap where Grass is going to be placed
     */
    public Grass(Position position, SafariMap map) {
        super(position, map);
        super.energyValue = 2;
        quantity++;
    }

    /**
     * Makes Grass to disappear
     * @param map SafariMap where object grows
     */
    @Override
    public void disappear(SafariMap map) {
        super.disappear(map);
        quantity--;
    }
}
