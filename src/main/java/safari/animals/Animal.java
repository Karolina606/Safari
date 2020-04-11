package safari.animals;

public abstract class Animal {
    //energy level 0-10
    int energyLevel = 10;
    boolean isAsleep;
    int positionX;
    int positionY;

    public Animal(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public abstract void move(int horizontalMax, int verticalMax);
}
