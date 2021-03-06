package safari.safariObjects.human;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;

/**
 * Class to deal with gun
 */
public class Gun {
    /**
     * Quantity of bullets left
     */
    private int quantityOfBullets;

    /**
     *Makes Gun object and sets it's quantityOfBullets to 5
     */
    public Gun(){
        quantityOfBullets = 5;
    }

    /**
     * Fires the gun to kill Lion <br>
     * it will kill a Lion if it lives on Safari and when there is amunition left <br>
     * @param position position of a Lion or (-1, -1) when there is no Lion
     * @param map SafariMap where a Lion lives
     */
    public void shoot(Position position, SafariMap map){
        //jesli nie ma wiecej amunicji
        if(quantityOfBullets <= 0){
            System.out.println("Nie ma więcej amunicji");
        }
        //jesli nie ma lwa na safari
        else if(position.getX() == -1 && position.getY() == -1){
            System.out.println("Na safari nie ma lwa do polowania");
        }
        //jesli na safari jest lew
        else{
            //znajdz lwa który jest na znalezionej pozycji i spraw by zniknął
            map.getMap().get(position).disappear(map);
            System.out.println("Human zastrzelił Lion");
            quantityOfBullets--;
        }
    }
}
