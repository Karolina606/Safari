package safari;

import safari.animals.Elephant;
import safari.animals.Lion;
import safari.animals.Zebra;
import safari.human.Human;
import safari.plants.Grass;
import safari.plants.Tree;

import java.util.Random;
import java.util.Scanner;

public class Safari {
    private static int maxIter;
    private static SafariMap map;

    public Safari(int width, int height){
        map = new SafariMap(width, height);
    }

    public void placeRandomSafariObjects(){
        //mamy 5 roznych rodzajow obiektow do rozmieszcznia Lion, Zebra, Elephant, Tree, Grass
        Random random = new Random(); // pomoze nam w losowaniu
        //niech kazde z nich zajmuje nie wiecej niz 1/5 planszy na poczatku
        int rangeForSafariObjects = (map.getHeight()*map.getWidth())/5;

        int startNumberForSafariObjects;

        //tutaj random free position nie moze zwrocic (-1, -1) bo mamy nowa plansze
        //lwy
        startNumberForSafariObjects = random.nextInt(rangeForSafariObjects)+1;
        for(int i = 0; i < startNumberForSafariObjects; i++){
            new Lion(Position.randomFreePosition(map), map);
        }
        //zebry
        startNumberForSafariObjects = random.nextInt(rangeForSafariObjects)+1;
        for(int i = 0; i < startNumberForSafariObjects; i++){
            new Zebra(Position.randomFreePosition(map), map);
        }
        //slonie
        startNumberForSafariObjects = random.nextInt(rangeForSafariObjects)+1;
        for(int i = 0; i < startNumberForSafariObjects; i++){
            new Elephant(Position.randomFreePosition(map), map);
        }
        //trawy
        startNumberForSafariObjects = random.nextInt(rangeForSafariObjects)+1;
        for(int i = 0; i < startNumberForSafariObjects; i++){
            new Grass(Position.randomFreePosition(map), map);
        }
        //drzewa
        startNumberForSafariObjects = random.nextInt(rangeForSafariObjects)+1;
        for(int i = 0; i < startNumberForSafariObjects; i++){
            new Tree(Position.randomFreePosition(map), map);
        }
    }

    public static void main(String[] args){
        //Safari safari = new Safari(2, 2);
        //--------------------------------------------------------------------------------------------------------------
        /*Zebra zebra = new Zebra(Position.randomFreePosition(map), map);
        map.showFreePositions();
        map.placeSafariObject( zebra, map.System.out.println("Dodano: "+ this.getClass().getSimpleName() + "na pozycje: " + position.toString());getFreePositions().get(0));
        //zebra.disappear(map);
        map.showFreePositions();
        //----------------------
        Position tempPos = Position.randomFreePosition(map);
        if(tempPos.equals(-1, -1)){
            System.out.println("There is no more place for new animals");
        }
        else{
            Lion lion = new Lion(tempPos, map);
        }

        tempPos = Position.randomFreePosition(map);
        if(tempPos.equals(-1, -1)){
            System.out.println("There is no more place for new animals");
        }
        else{
            Lion lion = new Lion(tempPos, map);
        }

        tempPos = Position.randomFreePosition(map);
        if(tempPos.equals(-1, -1)){
            System.out.println("There is no more place for new animals");
        }
        else{
            Lion lion = new Lion(tempPos, map);
        }

        map.showFreePositions();

        map.printSafariMap();
        //---------------------------------------------------------------------------------*/
        /*Elephant elephant1 = new Elephant(Position.randomFreePosition(map), map);
        Elephant elephant2 = new Elephant(Position.randomFreePosition(map), map);

        map.placeSafariObject(elephant1, elephant2.getPosition());
        map.printSafariMap();*/

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj szerokość i wysokość planszy: ");
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        Safari safari = new Safari(width, height);

        System.out.println("Podaj ilość iteracji: ");
        maxIter = scanner.nextInt();
        safari.placeRandomSafariObjects();

        //rozpocznij symulacje
        Random random = new Random(); //sprowadzania na safari czlowieka z pewnym prawdopodobienstwem
        Position positionForHuman;
        for(int i = 0; i < maxIter; i++){
            System.out.println("################################ Iteration: " + i +  " ################################");
            //czy pojawi sie czlowkiek? czlowiek pojawi sie z prawdopodobienstwem 10%
            if(random.nextInt(100) < 10){
                positionForHuman = Position.randomFreePosition(map);
                if(positionForHuman.equals(-1, -1)){
                    System.out.println("Nie ma wolnego miejsca dla czlowieka");
                }
                else{
                    new Human(positionForHuman, map);
                }
            }

            //zwierzeta
            SafariObject currentSafariObject;
            for(int j = 0; j < map.getAllAnimalsAndHumans().size(); j++){
                currentSafariObject = map.getAllAnimalsAndHumans().get(j);
                System.out.println(currentSafariObject.toString());
                currentSafariObject.makeAction(map);
                System.out.println(currentSafariObject.toString());
                System.out.println("---------");

            }
        }
    }
}


