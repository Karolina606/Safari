package safari.safariMap;

/**
 * Interface declares Position's required methods
 */
public interface IPosition {
    /**
     * Gets x coordinate
     * @return integer x coordinate
     */
    int getX();
    /**
     * Gets y coordinate
     * @return integer y coordinate
     */
    int getY();

    /**
     * Should check if Position has given coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @return true if coordinates are the same false otherwise
     */
    boolean equals(int x, int y);

    /**
     * Formats object of class Position to String
     * @return String with informations about Position object
     */
    String toString();
}
