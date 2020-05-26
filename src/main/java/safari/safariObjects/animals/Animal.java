package safari.safariObjects.animals;

import safari.safariMap.Position;
import safari.safariMap.SafariMap;
import safari.safariObjects.IActionable;
import safari.safariObjects.SafariObject;

import java.util.Random;

/**
 * Abstract class to handle with animal
 */
public abstract class Animal extends SafariObject implements IAnimal, IActionable {
    /**
     * Energy level of the Animal
     */
    protected int energyLevel = 20;
    /**
     * Sleep time of the Animal (number of iterations during with Animal was asleep)
     */
    protected int sleepTime = 0;
    /**
     * If Animal is asleep
     */
    protected boolean isAsleep = false;
    /**
     * Static variable it counts ids
     */
    private static int idCount = 0;
    /**
     * Individual id of the Animal
     */
    protected int id;

    /**
     * Animal constructor
     * @param position Position to place new Animal
     * @param map SafariMap where Animal will live
     */
    public Animal(Position position, SafariMap map){
        this.id = idCount;
        idCount++;
        this.position = position;
        System.out.println("Młody/a " + this.getClass().getSimpleName() + " na safari");
        //znajdz miejsce dla zwierzaka
        map.placeSafariObject(this, position);
        //place in allAnimalsAndHumans
        map.getAllAnimalsAndHumans().add(this);
        //zapamiętaj mapę na której żyje
        this.map = map;
        //zwiększ liczbę zapamiętanych zwierząt
        this.map.increaseAnimalAmount();
    }

    /**
     *Makes Animal to take some action
     * If Animal energyLevel equals 0 Animal dies
     * If Animal is sleeping and energyLevel still is less then 4 sleep longer
     * If Animal is sleeping ane energyLevel is now more then 4 wake up the Animal
     * If Animal is not sleeping but energyLevel is less then 4 Animal fall asleep with 70% probability
     * If Animal despire to low energyLevel did't fall asleep make one move
     * If Animal has higher energyLevel make all moves allowed in this energyLevel
     * @param movesOnEnergy tab with number of moves allowed in all energyLevel (there are 3 energy levels [1,6], [7, 14], [15, up])
     */
    public void makeAction(int[] movesOnEnergy){
        //jesli poziom energii spadl do 0 zwierze umiera
        if(energyLevel <= 0){
            disappear(map);
        }
        //w zaleznosci od poziomu energii podejmij czynnosc
        if(isAsleep){
            if(energyLevel < 4){
                //jesli spi i poziom energii jest nadal mniejszy niz 4 pozwol spac dalej
                energyLevel++; // zwieksz energie
                sleepTime++;
                System.out.println(this.getClass().getSimpleName() + " śpi");
            }
            else{
                //jesli poziom energii wzrosl juz powyzej 4 obodz zwierzaka
                isAsleep = false;
                System.out.println(this.getClass().getSimpleName() + " obudził się");
            }
        }
        //jesli zwierze nie spi a poziom energii jest bardzo niski < 4
        else if(energyLevel < 4){
            //zwierze moze zaraz umrzec wiec powinno isc spac, ma jednak pewien wybor
            //zasnie z prawdopodobienstwem 70%, losujemy liczby z zakresu do 0-99, jesli wylosujem < 20 zwierze zasnie
            Random probability = new Random();
            if(probability.nextInt(100) < 70){
                isAsleep = true;
                sleepTime++;
                System.out.println(this.getClass().getSimpleName() + " zasnął");
            }
            else{
                //jesli zwierze nie zasnelo wykonaj ruch
                move();
            }
        }else {
            makeAllMoves(movesOnEnergy);
        }
    }

    /**
     * Move Animal on the map, make one step
     */
    public void move(){
        //sprobuj wykonac ruch
        Position nextPosition = Position.randomPosition(map);
        this.react(nextPosition);
    }

    /**
     * Makes Animal to make all allowed moves in current energyLevel
     * @param movesOnEnergy tab with number of moves allowed in all energyLevel (there are 3 energy levels [1,6], [7, 14], [15, up])
     */
    public void makeAllMoves(int[] movesOnEnergy){
        if(energyLevel <= 6){
            //kazde zwierz ktore ma poziom energii ponizej i rowne 6 ma jeden ruch do wykonania
            for(int i = 0; i < movesOnEnergy[0]; i++){
                move();
            }
        }
        else if(energyLevel <= 14){
            //drugi poziom energetyczny, w tabeli animalMovesOnEnergy zapisana jest liczba ruchow dla kazdego zwierzecia, pierwszy indeks - numer zwierzecia, drugi indeks - stan energetyczny
            for(int i = 0; i < movesOnEnergy[1]; i++){
                move();
            }
        }
        else if(energyLevel > 15){
            //trzeci poziom energetyczny, w tabeli animalMovesOnEnergy zapisana jest liczba ruchow dla kazdego zwierzecia, pierwszy indeks numer zwierzecia, drugi indeks - stan energetyczny
            for(int i = 0; i < movesOnEnergy[2]; i++){
                move();
            }
        }
    }

    /**
     * Decreases Animal's energyLevel
     */
    protected void decreaseEnergy(){
        //odejmij energie jaka zwierze utracilo na probe przemieszczenia
        if(energyLevel-2 > 0){
            //jezeli jeszcze ma sily to podczas ruchu odejmuje sie mu dwie jednostki energii
            this.energyLevel-=2;
        }else if (energyLevel-1 > 0){
            //gdy jest już na skraju sił
            this.energyLevel-=1;
        }else{
            //zwierze umarło z wycieńczenia
            this.disappear(this.map);
        }

        /*else if(energyLevel-1 >= 0){
            //u schylku zycia odejmuje sie zaledwie jedna jednostke energii
            this.energyLevel-=1;
        }else{
            //zwierze umarło z wycieńczenia
            this.disappear(this.map);
        }*/
    }

    /**
     * Reacts to SafariObject on given position
     * @param position Position to react
     */
    protected abstract void react(Position position);

    /**
     * Reproduce Animals
     * It means place new Animal on the SafariMap
     */
    protected abstract void reproduce();

    /**
     * Eats given SafariObject
     * @param object SafariOject to eat
     */
    protected abstract void eat(SafariObject object);

    /**
     * Formats information about Animal to String appropriate to write it later to csv file
     * @return String with information about Animal
     */
    public String animalToFile(){
        // class, id, energyLevel, isAsleep, sleepTime, (x;y)
        return getClass().getSimpleName() + ", " + id + ", " + energyLevel + ", " + isAsleep + ", " + sleepTime + ", " + "(" + position.getX() + " " + position.getY() + ")";
    }

    /**
     * Makes Animal to disappear
     * @param map SafariMap where object lives
     */
    @Override
    public void disappear(SafariMap map){
        super.disappear(map);
        map.getAllAnimalsAndHumans().remove(this);
        map.decreaseAnimalAmount();
    }

    /**
     * Formats information about Animal object to the string
     * @return String with information about Animal object
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ "{" +
                "id= " + id +
                ", energyLevel=" + energyLevel +
                ", isAsleep=" + isAsleep +
                ", sleepTime=" + sleepTime +
                ", position=" + position.toString() +
                '}';
    }
}
