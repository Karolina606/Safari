package safari.safariMap;

import java.util.Random;

/**
 * Calss to handle with positions and coordinates
 */
public class Position implements IPosition{
    /**
     * x coordinate
     */
    private int x;
    /**
     * y coordinate
     */
    private int y;

    /**
     * Makes an Position object with given coordinates
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets horizontal coordinate
     * @return x horizontal coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets vertical coordinate
     * @return y vertical coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * Gives random position on SafariMap
     * @param map SafariMap to find position on
     * @return position on SafariMap
     */
    public static Position randomPosition(SafariMap map){
        Random rand = new Random();
        //losujemy jedną z wszystkich pozycji na mapie nie ważne czy jest zajęta czy nie
        int randIndex = rand.nextInt(map.getHeight()*map.getWidth());
        //z wszystkich pozycji wybieramy tą która znajduje się pod wylosowanym indeksem
        return map.getAllPositions().get(randIndex);
    }

    /**
     * Gives free random position on SafariMap
     * @param map SafariMap to find position on
     * @return free position on SafariMap or Position (-1, -1) when there is no free Positions
     */
    public static Position randomFreePosition(SafariMap map){
        return map.getFreePosition();
    }

    /**
     * Checks if Position coordinates are the same with given coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @return true if both equals false if not
     */
    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }

    /**
     * Formats object of class Position to String
     * @return String with informations about Position object
     */
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
