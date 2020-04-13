package safari.animals;

public class Zebra extends Animal{
    private static int quantity = 0;

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        Zebra.quantity = quantity;
    }

    public Zebra(int x, int y){
        super(x, y);
        System.out.println("One more zebra");
        quantity++;
    }

    public void move(int horizontalMax, int verticalMax){

    }

}
