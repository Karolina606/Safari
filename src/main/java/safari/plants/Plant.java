package safari.plants;

import safari.Position;
import safari.Safari;
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
     * @param safari on this Safari Plant will exists
     */
    public Plant(Position position, Safari safari){
        safari.getMap().placeSafariObject(this, position, safari);
    }

    /**
     * Gets energy value of the plant
     * @return energyValue of the plant
     */
    public int getEnergyValue(){
        return energyValue;
    }


}
