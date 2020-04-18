package safari.plants;

import safari.Position;
import safari.SafariMap;
import safari.SafariObject;

/**
 * Abstract class to deal with plants
 */
public abstract class Plant extends SafariObject {
    protected int energyValue;
    protected Position position;

    /**
     * Makes Plant object and put it on Safari
     * @param position Position to put the Plant on it
     * @param map SafariMap where Plant is going to be placed
     */
    public Plant(Position position, SafariMap map){
        map.placeSafariObject(this, position);
        System.out.println("Dodano: "+ this.getClass().getSimpleName() + " na pozycje: " + position.toString());
    }

    /**
     * Gets energy value of the plant
     * @return energyValue of the plant
     */
    public int getEnergyValue(){
        return energyValue;
    }


}
