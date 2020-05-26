package safari.safariObjects;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;

/**
 * Abstract class to deal with all object living on Safari
 */
public abstract class SafariObject {
    /**
     * Current Positon of SafariObject
     */
    protected Position position;
    /**
     * SafariMap where SafariObject lives
     */
    public SafariMap map;

    /**
     *Gets SafarisObject's Position
     * @return object from class Position
     */
    public Position getPosition(){
        return position;
    }

    /**
     *Sets SafariObject's Position
     * @param position position to set
     */
    public void setPosition(Position position){
        this.position = position;
    }

    /**
     * Remove SafariObject from Safari
     * @param map SafariMap where object lives
     */
    public void disappear(SafariMap map){
        map.freeUpPosition(this.position);
        System.out.println("Zniknął obiekt: " + this.getClass().getSimpleName());
    }
}
