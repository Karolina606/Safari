package safari.animals;

import safari.Position;
import safari.Safari;
import safari.SafariMap;
import safari.SafariObject;

public abstract class Animal extends SafariObject {
    //energy level 0-10
    int energyLevel = 10;
    boolean isAsleep;
    public Position position;

    public Animal(Position position, Safari safari){
        this.position = position;
        safari.getMap().placeSafariObject(this, position, safari);
        //place in allAnimals
        safari.getAllAnimals().add(this);
    }
    public abstract void move(int horizontalMax, int verticalMax);
    public String positionToString(){
        return "Position: " + Integer.toString(position.getX()) + ", " + Integer.toString(position.getY());
    }
}
