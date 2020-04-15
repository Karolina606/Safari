package safari.animals;

import safari.Position;
import safari.Safari;
import safari.SafariMap;

public class Zebra extends Animal{
    private static int quantity = 0;

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        Zebra.quantity = quantity;
    }

    public Zebra(Position position, Safari safari){
        super(position, safari);
        System.out.println("One more zebra");
        quantity++;
    }

    public void move(int horizontalMax, int verticalMax){

    }

}
