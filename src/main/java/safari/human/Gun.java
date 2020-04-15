package safari.human;

import safari.Position;
import safari.Safari;
import safari.SafariMap;

/**
 * Class to deal with gun
 */
public class Gun {
    private int quantityOfBullets;

    /**
     *Makes Gun object and sets it's quantityOfBullets to 10
     */
    public Gun(){
        quantityOfBullets = 10;
    }

    /**
     * Fires the gun to kill Lion
     * it will kill a Lion if it lives on Safari and when there is amunition left
     * @param position position of a Lion
     * @param safari safari where a Lion lives
     */
    public void shoot(Position position, Safari safari){
        //jesli nie ma wiecej amunicji
        if(quantityOfBullets <= 0){
            System.out.println("There is no more ammunition");
        }
        //jesli nie ma lwa na safari
        else if(position.getX() == -1 && position.getY() == -1){
            System.out.println("There is no lion to hunt");
        }
        //jesli na safari jest lew
        else{
            safari.getMap().freeUpPosition(position, safari);
        }
    }
}
