package safari.human;

import safari.Position;
import safari.SafariMap;
import safari.SafariObject;
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
     * @param map SafariMap human is going to live on
     */
    public Human(Position position, SafariMap map){
        map.placeSafariObject(this, position);
        gun = new Gun();
        System.out.println("Dodano: "+ this.getClass().getSimpleName() + " na pozycje: " + position.toString());
    }

    public void makeAction(SafariMap map){
        shoot(lookForTheAim(map.getAllAnimalsAndHumans()), map);
    }

    /**
     * Searchs for Lion on the Safari
     * @param listOfAnimals list of all Animals on the Safari
     * @return Lion's Position if Lion is on the Safari, returns new Point (-1, -1) if it is not there
     */
    private Position lookForTheAim(List<SafariObject> listOfAnimals){
        //szuka lwa na liście wszystkich zwierząt
        for(SafariObject animal: listOfAnimals){
            if(animal instanceof Lion){
                return animal.getPosition();
            }
        }
        //kiedy nie ma żadnego lwa na safari
        return new Position(-1, -1);
    }

    /**
     * Tries to shoot the Lion on found Position, Position
     * @param position possible Position of the Lion or Position (-1, -1) if it is not on Safari
     * @param map SafariMap where Lion lives
     */
    public void shoot(Position position, SafariMap map){
        gun.shoot(lookForTheAim(map.getAllAnimalsAndHumans()), map);
    }
}
