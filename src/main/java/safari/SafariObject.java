/**
 * 15.04.2020
 */
package safari;

/**
 * Class to deal with all object living on Safari
 * @author karolina
 *
 */
public class SafariObject {
    protected Position position;

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
     */
    public void disappear(){

    }
}