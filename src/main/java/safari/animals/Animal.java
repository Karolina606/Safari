package safari.animals;

import safari.Position;
import safari.SafariMap;
import safari.SafariObject;

import java.util.Random;

public abstract class Animal extends SafariObject {
    int energyLevel = 20;
    int sleepTime = 0;
    boolean isAsleep = false;
    public Position position;
    //liczba ruchow dla poszczegolnych gatunkow w zaleznosci od ich poziomu energii
    //pierwszy indeks numer zwierzecia {0 = Elephant, 1 = Zebra, 2 = Lion}
    //drugi indeks to numer stanu energetycznego {0 = [1,6], 1 = [7, 14], 2 = [15, up]}
    protected final int[][] animalsMovesOnEnergy = {{1, 2, 3}, {1, 3, 4}, {1, 4, 5}};

    public Animal(Position position, SafariMap map){
        this.position = position;
        map.placeSafariObject(this, position);
        //place in allAnimals
        map.getAllAnimalsAndHumans().add(this);
        System.out.println("Młody/młoda "+ this.getClass().getSimpleName() + " na pozycje: " + position.toString());
    }

    public void makeAction(SafariMap map, int spieceNumber){
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
        else if(!isAsleep && energyLevel < 4){
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
                move(map);
            }
        }
        else if(energyLevel <= 6){
            //kazde zwierz ktore ma poziom energii ponizej i rowne 6 ma jeden ruch do wykonania
            move(map);
        }
        else if(energyLevel > 6 && energyLevel <= 14){
            //drugi poziom energetyczny, w tabeli animalMovesOnEnergy zapisana jest liczba ruchow dla kazdego zwierzecia, pierwszy indeks - numer zwierzecia, drugi indeks - stan energetyczny
            for(int i = 0; i < animalsMovesOnEnergy[spieceNumber][1]; i++){
                move(map);
            }
        }
        else if(energyLevel > 15){
            //trzeci poziom energetyczny, w tabeli animalMovesOnEnergy zapisana jest liczba ruchow dla kazdego zwierzecia, pierwszy indeks numer zwierzecia, drugi indeks - stan energetyczny
            for(int i = 0; i < animalsMovesOnEnergy[spieceNumber][2]; i++){
                move(map);
            }
        }
    }

    public void move(SafariMap map){
        //sprobuj wykonac ruch
        Position nextPosition = Position.randomPosition(map);
        this.react(nextPosition, map);
    }

    protected void decreaseEnergy(){
        //odejmij energie jaka zwierze utracilo na probe przemieszczenia
        if(energyLevel-2 >= 0){
            //jezeli jeszcze ma sily to podczas ruchu odejmuje sie mu dwie jednostki energii
            this.energyLevel-=2;
        }else{
            //u schylku zycia odejmuje sie zaledwie jedna jednostke energii
            this.energyLevel-=1;
        }
    }

    public abstract void makeAction(SafariMap map);

    protected void react(Position position, SafariMap map){}

    protected void reproduction(SafariMap map){}

    protected void eat(SafariObject object){}

    public String positionToString(){
        return "Position: " + Integer.toString(position.getX()) + ", " + Integer.toString(position.getY());
    }

    @Override
    public void disappear(SafariMap map){
        super.disappear(map);
        map.getAllAnimalsAndHumans().remove(this);
    }

    /**
     * Format Animal object to the string
     * @return String with information about Animal Object
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ "{" +
                "energyLevel=" + energyLevel +
                ", isAsleep=" + isAsleep +
                ", sleepTime=" + sleepTime +
                ", position=" + position.toString() +
                '}';
    }
}
