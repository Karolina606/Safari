package safari.safariObjects.animals;

import safari.safariMap.SafariMap;

public interface IAnimal {
    void makeAction(int[] movesOnEnergy);
    void move();
    void disappear(SafariMap map);
    String animalToFile();
}
