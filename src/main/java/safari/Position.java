package safari;

import java.util.Objects;

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
     * Sets horizontal coordinates
     * @param x horizontal coordinate wanted to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets vertical coordinate
     * @return y vertical coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets vertical coordinates
     * @param y vertical coordinate wanted to set
     */
    public void setY(int y) {
        this.y = y;
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
