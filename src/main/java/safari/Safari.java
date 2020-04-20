package safari;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import safari.animals.Animal;
import safari.animals.Elephant;
import safari.animals.Lion;
import safari.animals.Zebra;
import safari.human.Human;
import safari.plants.Grass;
import safari.plants.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Safari {
    private static int maxIter;
    private static SafariMap map;
    private ArrayList toRaport = new ArrayList();

    public Safari(int width, int height){
        map = new SafariMap(width, height);
    }

    public static void main(String[] args){
        int width = 0;
        int height = 0;
        int maxIter = 0;
        int elephants = 0;
        int zebras = 0;
        int lions = 0;

        //parsowanie argumentów wejściowych
        ArgumentParser parser = ArgumentParsers.newFor("Safari").build().description("Run safari simulation");
        //argumenty domyślne
        List<Integer> defaultDimensions = new ArrayList<>();
        defaultDimensions.add(4);
        List<Integer> defaultIterations = new ArrayList<>();
        defaultIterations.add(10);
        List<Integer> defaultAnimals = new ArrayList<>();
        defaultAnimals.add(1);

        parser.addArgument("-width", "-w").type(Integer.class).nargs(1).setDefault(defaultDimensions).help("Safari width");
        parser.addArgument("-height", "-he").type(Integer.class).nargs(1).setDefault(defaultDimensions).help("Safari height");
        parser.addArgument("-maxIter", "-i").type(Integer.class).nargs(1).setDefault(defaultIterations).help("Max iterations of simulation");

        parser.addArgument("-elephant", "-e").type(Integer.class).nargs(1).setDefault(defaultAnimals).help("How many elephants on safari");
        parser.addArgument("-zebra", "-z").type(Integer.class).nargs(1).setDefault(defaultAnimals).help("How many zabras on safari");
        parser.addArgument("-lion", "-l").type(Integer.class).nargs(1).setDefault(defaultAnimals).help("How many lions on safari");
        try {
            Namespace res = parser.parseArgs(args);
            System.out.println(res);
            width = (int) ((List)res.getAttrs().get("width")).get(0);
            height = (Integer) ((List) res.getAttrs().get("height")).get(0);
            maxIter = (Integer) ((List) res.getAttrs().get("maxIter")).get(0);
            zebras= (int) ((List)res.getAttrs().get("zebra")).get(0);
            elephants = (Integer) ((List) res.getAttrs().get("elephant")).get(0);
            lions = (Integer) ((List) res.getAttrs().get("lion")).get(0);
        } catch (ArgumentParserException e) {
            e.printStackTrace();
        }
        Safari safari = new Safari(width, height);
        safari.placeRandomSafariObjects(elephants, zebras, lions);

        //rozpocznij symulacje
        for(int i = 0; i < maxIter; i++){
            System.out.println("################################ Iteration: " + (i+1) +  " ################################");
            safari.tryToPutHumanOnSafari();

            //zapisz stan poczatkowy
            if(i == 0){
                safari.saveToRaportArray(map.getAllAnimalsAndHumans(), i);
            }

            //zwierzeta
            SafariObject currentSafariObject;
            for(int j = 0; j < map.getAllAnimalsAndHumans().size(); j++) {
                currentSafariObject = map.getAllAnimalsAndHumans().get(j);
                System.out.println(currentSafariObject.toString());
                currentSafariObject.makeAction(map);
                System.out.println(currentSafariObject.toString());
                System.out.println("---------");
            }
            safari.saveToRaportArray(map.getAllAnimalsAndHumans(), i+1);
        }
        safari.writeToFile("raport.csv");
    }

    public void placeRandomSafariObjects(int elephants, int zebras, int lions){
        //mamy 5 roznych rodzajow obiektow do rozmieszcznia Lion, Zebra, Elephant, Tree, Grass
        Random random = new Random(); // pomoze nam w losowaniu
        //niech kazde z nich zajmuje nie wiecej niz 1/5 planszy na poczatku
        int safariSize = map.getHeight()*map.getWidth();

        int startNumberForSafariObjects;
        Position position;
        //tutaj random free position nie moze zwrocic (-1, -1) bo mamy nowa plansze
        //lwy
        //startNumberForSafariObjects = random.nextInt(safariSize/6);
        for(int i = 0; i < lions; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else{
                new Lion(position, map);
            }
        }
        //zebry
        //startNumberForSafariObjects = random.nextInt(safariSize/6)+1;
        for(int i = 0; i < zebras; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else{
                new Zebra(position, map);
            }
        }
        //slonie
        //startNumberForSafariObjects = random.nextInt(safariSize/6)+1;
        for(int i = 0; i < elephants; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else{
                new Elephant(position, map);
            }
        }
        //trawy
        startNumberForSafariObjects = random.nextInt(safariSize/4)+1;
        for(int i = 0; i < startNumberForSafariObjects; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else {
                new Grass(position, map);
            }
        }
        //drzewa
        startNumberForSafariObjects = random.nextInt(safariSize/4)+1;
        for(int i = 0; i < startNumberForSafariObjects; i++){
            position = Position.randomFreePosition(map);
            if(position.equals(-1, -1)){
                System.out.println("Nie ma wiecej wolnych pozycji");
            }
            else {
                new Tree(Position.randomFreePosition(map), map);
            }
        }
    }

    public void tryToPutHumanOnSafari(){
        Random random = new Random(); //sprowadzania na safari czlowieka z pewnym prawdopodobienstwem
        Position positionForHuman;
        //czy pojawi sie czlowkiek? czlowiek pojawi sie z prawdopodobienstwem 40%
        if(random.nextInt(100) < 40){
            positionForHuman = Position.randomFreePosition(map);
            if(positionForHuman.equals(-1, -1)){
                System.out.println("Nie ma wolnego miejsca dla czlowieka");
            }
            else{
                new Human(positionForHuman, map);
                map.getMap().get(positionForHuman).makeAction(map);
            }
        }
    }

    public void saveToRaportArray(List<SafariObject> list, int iteration){
        toRaport.add("Iteration: " + ((Integer) iteration).toString());

        toRaport.add("class, id, energyLevel, isAsleep, sleepTime, position, population");
        for(SafariObject object: list){
            if(object instanceof Animal){
                toRaport.add(((Animal) object).objectToFile());
            }
        }
        toRaport.add("");
    }

    public void writeToFile(String fileName){
        Path path = Paths.get(fileName);
        try {
            Files.write(path, toRaport);
        } catch (IOException ex) {
            System.out.println("Nie mogę zapisać pliku!");
        }
    }
}


