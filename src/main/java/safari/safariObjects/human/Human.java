package safari.safariObjects.human;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;
import safari.safariObjects.SafariObject;
import safari.safariObjects.animals.Lion;

import java.util.List;

/**
 * Class represents human object in simulation
 * Human is subclass of {@link SafariObject}
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
        map.getAllAnimalsAndHumans().add(this);
        gun = new Gun();
        this.map = map;
        System.out.println("Dodano: "+ this.getClass().getSimpleName() + " na pozycje: " + position.toString());
    }

    public void makeAction(){
        shoot();
    }

    /**
     * Searches for Lion on the Safari
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
     */
    public void shoot(){
        gun.shoot(lookForTheAim(this.map.getAllAnimalsAndHumans()), this.map);
        //System.out.println("---------");
    }
    /**
     * Formats information abut Human object to the string
     * @return String with information about Human object
     */
    @Override
    public String toString() {
        return "Human "+ "{" +
                ", position=" + position.toString() +
                '}';
    }
}
