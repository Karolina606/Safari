package safari.safariMap;

import safari.safariObjects.IActionable;
import safari.safariObjects.SafariObject;

import java.util.List;
import java.util.Map;

/**
 * Interface declares SafariMap's required methods
 */
public interface ISafariMap {
    /**
     * Gets width of the SafariMap
     * @return integer width of the SafariMap
     */
    int getWidth();

    /**
     * Gets height of the SafariMap
     * @return integer height of the SafariMap
     */
    int getHeight();

    /**
     * Gets number of Animals left on the Safari
     * @return integer number of Animals left on the Safari
     */
    int getAnimalAmount();

    /**
     * Gets reference to the 2D map of objects, Position as keys, SafariObject as value
     * @return Map<Position, SafariObjet>
     */
    Map<Position, SafariObject> getMap();

    /**
     * Gets reference to the List of all Animals And Humans, SafariObjects that can make some action
     * @return List<IActionable> list of Objects implementing IActionable
     */
    List<IActionable> getAllAnimalsAndHumans();

    /**
     * Gets reference to the list of all Free positions
     * @return
     */
    List<Position> getFreePositions();

    /**
     * Gets list of all position on the map
     * @return List<Position>
     */
    List<Position> getAllPositions();

    /**
     * Removes given position from freePositions
     * @param position position to remove from list
     */
    void removeFromFreePositions(Position position);

    /**
     * Adds position to list of free positions and removes object on that position
     * @param position to free up
     */
    void freeUpPosition(Position position);

    /**
     * Gets one of the free positions
     * @return Position - free position on map or (-1, -1) when there is no free positions
     */
    Position getFreePosition();

    /**
     * Puts SafariObject on given position
     * @param object SafariObject to put
     * @param position position to put SafariObject
     */
    void placeSafariObject(SafariObject object, Position position);

    /**
     * Increases number of Animals left on the Safari
     */
    void increaseAnimalAmount();

    /**
     * Decrease number of Animals left on the Safari
     */
    void decreaseAnimalAmount();
}
