package safari.safariObjects.animals;

import safari.safariMap.SafariMap;

/**
 * Interface declares Animal's required methods
 */
public interface IAnimal {
    /**
     * Makes Animal to make some action
     * @param movesOnEnergy table of moves possible to make depends on energyLevel
     */
    void makeAction(int[] movesOnEnergy);

    /**
     * Makes Animal to make one move
     */
    void move();

    /**
     * Makes Animal to disappear form SafariMap
     * @param map current SafariMap to disappear form
     */
    void disappear(SafariMap map);

    /**
     * Writes information about Animal to the file
     * @return String with information about Animal prepared to write to the file
     */
    String animalToFile();
}
