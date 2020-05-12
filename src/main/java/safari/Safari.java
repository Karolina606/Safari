package safari;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import safari.fileManager.FileManager;
import safari.safariMap.Position;
import safari.safariMap.SafariMap;
import safari.safariMap.SafariMapCreator;
import safari.safariObjects.SafariObject;
import safari.safariObjects.human.Human;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 15.04.2020
 * Class runs and control the simulation
 * @author Karolina Nogacka
 */
public class Safari{
    private static SafariMap map;
    private static FileManager fileManager = new FileManager();

    /**
     * Safari constructor
     * @param width width of the SafariMap
     * @param height height of the SafariMap
     */
    public Safari(int width, int height){
        map = new SafariMap(width, height);
    }

    /**
     * Handles with starting parameters, parses them and runs simulation
     * @param args arguments
     */
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
        defaultDimensions.add(5);
        List<Integer> defaultIterations = new ArrayList<>();
        defaultIterations.add(10);
        List<Integer> defaultZebrasAndElep = new ArrayList<>();
        defaultZebrasAndElep.add(3);
        List<Integer> defaultLions = new ArrayList<>();
        defaultLions.add(1);

        parser.addArgument("-width", "-w").type(Integer.class).nargs(1).setDefault(defaultDimensions).help("Safari width");
        parser.addArgument("-height", "-he").type(Integer.class).nargs(1).setDefault(defaultDimensions).help("Safari height");
        parser.addArgument("-maxIter", "-i").type(Integer.class).nargs(1).setDefault(defaultIterations).help("Max iterations of simulation");

        parser.addArgument("-elephant", "-e").type(Integer.class).nargs(1).setDefault(defaultZebrasAndElep).help("How many elephants on safari");
        parser.addArgument("-zebra", "-z").type(Integer.class).nargs(1).setDefault(defaultZebrasAndElep).help("How many zabras on safari");
        parser.addArgument("-lion", "-l").type(Integer.class).nargs(1).setDefault(defaultLions).help("How many lions on safari");
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
        SafariMapCreator.placeRandomSafariObjects(elephants, zebras, lions, map);

        //rozpocznij symulacje
        for(int i = 0; i < maxIter && !map.getAllAnimalsAndHumans().isEmpty(); i++){
            System.out.println("################################ Iteration: " + (i+1) +  " ################################");
            safari.putHumanOnSafari();

            //zapisz stan poczatkowy
            if(i == 0){
                fileManager.saveToReportArray(map.getAllAnimalsAndHumans(), i);
            }

            //zwierzeta
            SafariObject currentSafariObject;
            for(int j = 0; j < map.getAllAnimalsAndHumans().size(); j++) {
                currentSafariObject = map.getAllAnimalsAndHumans().get(j);
                System.out.println(currentSafariObject.toString());
                currentSafariObject.makeAction();
                System.out.println(currentSafariObject.toString());
                System.out.println("---------");
            }
            fileManager.saveToReportArray(map.getAllAnimalsAndHumans(), i+1);
        }
        fileManager.writeToFile("report.csv");
    }


    /**
     * Try to put human on Safari if there is an empty position (with 40% probability in each iteration)
     */
    public void putHumanOnSafari(){
        Random random = new Random(); //sprowadzania na safari czlowieka z pewnym prawdopodobienstwem
        //czy pojawi sie czlowkiek? czlowiek pojawi sie z prawdopodobienstwem 40%
        if(random.nextInt(100) < 40){
            Position positionForHuman;
            positionForHuman = Position.randomFreePosition(map);
            if(positionForHuman.equals(-1, -1)){
                System.out.println("Nie ma wolnego miejsca dla czlowieka");
            }
            else{
                new Human(positionForHuman, map);
                //czlowiek chce zapolowac na lwa
                map.getMap().get(positionForHuman).makeAction();
            }
        }
    }
}


