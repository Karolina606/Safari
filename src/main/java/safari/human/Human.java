package safari.human;

import safari.Position;
import safari.Safari;
import safari.SafariObject;
import safari.animals.Animal;
import safari.animals.Lion;

import java.util.List;

/**
 * Class to handle with Human objects
 */
public class Human extends SafariObject {
    private Gun gun;

    /**
     *Makes a Human and places it on Safari on pointed Position
     * @param position Postion to place Human in
     * @param safari Safari he lives on
     */
    public Human(Position position, Safari safari){
        safari.getMap().placeSafariObject(this, position, safari);
        gun = new Gun();
    }

    /**
     * Searchs for Lion on the Safari
     * @param listOfAnimals list of all Animals on the Safari
     * @return Lion's Position if Lion is on the Safari, returns new Point (-1, -1) if it is not there
     */
    private Position lookForTheAim(List<Animal> listOfAnimals){
        //szuka lwa na liście wszystkich zwierząt
        for(Animal animal: listOfAnimals){
            if(animal instanceof Lion){
                return animal.getPosition();
            }
        }
        //kiedy nie ma żadnego lwa na safari
        return new Position(-1, -1);
    }
}
