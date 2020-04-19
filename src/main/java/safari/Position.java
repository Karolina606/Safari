package safari;

import java.util.Random;

/**
 * Calss to handle with positions and coordinates
 */
public class Position {
    private int x;
    private int y;

    /**
     * Makes an Position object with given coordinates
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    public Position(int x, int y){
        setPosition(x, y);
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
     * Sets vertical and horizontal coordinates
     * @param x horizontal coordinate wanted to set
     * @param y vertical coordinate wanted to set
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
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


    public boolean equals(int x, int y) {
        if(this.x == x && this.y == y)
            return true;
        else
            return false;
    }

    /**
     * Checks if given Object is equals to this object (object calling method)
     * @param o given Object
     * @return true if objects are equals and false if objects are not equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
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
