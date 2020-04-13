package safari.animals;

import safari.Position;

public abstract class Animal {
    //energy level 0-10
    int energyLevel = 10;
    boolean isAsleep;
    public Position position;

    public Animal(int x, int y){
        position = new Position(x, y);
    }
    public abstract void move(int horizontalMax, int verticalMax);
    public String positionToString(){
        return "Position: " + Integer.toString(position.getX()) + ", " + Integer.toString(position.getY());
    }
}
