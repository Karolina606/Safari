package safari.safariMap;

import safari.safariObjects.SafariObject;

import java.util.List;
import java.util.Map;

public interface ISafariMap {

    int getWidth();

    int getHeight();

    Map<Position, SafariObject> getMap();

    List<SafariObject> getAllAnimalsAndHumans();

    List<Position> getFreePositions();

    List<Position> getAllPositions();

    void removeFromFreePositions(Position position);

    void showFreePositions();

    void freeUpPosition(Position position);

    Position getFreePosition();

    void placeSafariObject(SafariObject object, Position position);

    void printSafariMap();
}
