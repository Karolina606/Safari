package safari.animals;

import safari.Position;
import safari.SafariMap;
import safari.SafariObject;
import safari.plants.Tree;

public class Elephant extends Animal{
    private static int quantity = 0;
    private Tree tree;


    public Elephant(Position position,  SafariMap map){
        super(position, map);
        quantity++;
    }

    @Override
    public void makeAction(SafariMap map){
        super.makeAction(map, 0);
    }

    @Override
    protected void react(Position position, SafariMap map) {
        //pobierz obiekt ktory jest na tej pozycji
        SafariObject object = map.getMap().get(position);

        //reaguj na to co znajduje sie na tej pozycji
        if(object == null){
            map.placeSafariObject(this, position);
            decreaseEnergy();
        }
        else if(object instanceof Tree){
            eat(object);
        }
        else if(object instanceof Elephant){
            reproduction(map);
        }
    }

    @Override
    protected void reproduction(SafariMap map) {
        //sprawdz czy jest wolna pozycja na safari
        Position freePosition = Position.randomFreePosition(map);
        if(freePosition.equals(-1, -1)){
            System.out.println("Nie ma wolnych pozycji, nie można umieścić nowego słonia");
        }
        else{
            //dodanie nowego zwierzaka pod wskazaną pozycję i do listy zwierzaków
            new Elephant(freePosition, map);
            //System.out.println("Młody słonik na pozycję: " + freePosition.toString());
        }
    }

    @Override
    protected void eat(SafariObject object) {
        //down casting
        Tree tree = (Tree) object;
        //sprawdz czy ma jeszcze liscie
        if(tree.getLeavesQuantity() > 0){
            //zjedz liscia z drzewa
            tree.decreaseLeavesQuantity();
            //dodaj sobie energii
            this.energyLevel += tree.getEnergyValue();
            System.out.println("Słoń zjadł liść z drzewa");
        }
    }

    @Override
    public void disappear(SafariMap map) {
        super.disappear(map);
        quantity--;
    }
}
