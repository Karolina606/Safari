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
     * Makes living organis to do action
     * @param map SafariMap where living organism makes action
     */
    public void makeAction(SafariMap map){}

    /**
     * Remove SafariObject from Safari
     * @param map SafariMap where object lives
     */
    public void disappear(SafariMap map){
        map.freeUpPosition(this.position);
        System.out.println("Zniknął obiekt: " + this.getClass().getSimpleName());
    }
}
