package safari.safariObjects.plants;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;
import safari.safariObjects.SafariObject;

/**
 * Abstract class to deal with plant object
 * Plant is subclass of {@link SafariObject}
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
        this.map = map;
        System.out.println("Dodano: "+ this.getClass().getSimpleName() + " na pozycje: " + position.toString());
    }

    /**
     * Gets energy value of the plant
     * @return energyValue of the plant
     */
    public int getEnergyValue(){
        return energyValue;
    }

    @Override
    public void makeAction() {
        System.out.println("Plant on position: " + position.toString());
    }
}
