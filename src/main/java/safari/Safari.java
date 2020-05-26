package safari;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import safari.fileManager.FileManager;
import safari.safariMap.Position;
import safari.safariMap.SafariMap;
import safari.safariMap.SafariMapCreator;
import safari.safariObjects.IActionable;
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
    /**
     * Contains SafariMap where Animals lives
     */
    private static SafariMap map;
    /**
     * FileManager object it will save information about Animals in each iteration
     */
    private static final FileManager fileManager = new FileManager();
    /**
     * Max iteration in this simulation number
     */
    private static int maxIter = 0;
    /**
     * Variable stores one and only object of Safari class
     */
    static Safari instance = null;

    /**
     * Safari constructor
     * @param width width of the SafariMap
     * @param height height of the SafariMap
     */
    public Safari(int width, int height){
        //chcemy tylko jeden obiekt klasy Safari
        if(instance == null){
            map = new SafariMap(width, height);
            instance = this;
        }else{
            System.out.println("Nie można utworzyć kolejnego obiektu klasy Safari");
        }
    }

    /**
     * Handles with starting parameters, parses them and runs simulation
     * @param args arguments
     */
    public static void main(String[] args){
        /**
         * Width of the SafariMap
         */
        int width = 0;
        /**
         * Height of the SafariMap
         */
        int height = 0;
        /**
         * Number od elephants to put on safari
         */
        int elephants = 0;
        /**
         * Number od zebras to put on safari
         */
        int zebras = 0;
        /**
         * Number od lions to put on safari
         */
        int lions = 0;
        /**
         * If all values set by user positive
         */
        boolean allValuesPositive = true;

        //----------------------------------------------------------parsowanie argumentów wejściowych---------------------------------------------------------------------------------
        ArgumentParser parser = ArgumentParsers.newFor("Safari").build().description("Run safari simulation");
        //argumenty domyślne
        //domyslne wymaiary planszy
        List<Integer> defaultDimensions = new ArrayList<>();
        defaultDimensions.add(5);
        //domyslana liczba iteracji
        List<Integer> defaultIterations = new ArrayList<>();
        defaultIterations.add(10);
        //domyslna liczba zebr i sloni jest taka sama
        List<Integer> defaultZebrasAndElep = new ArrayList<>();
        defaultZebrasAndElep.add(3);
        //domyslana liczba lwow
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
            //sprawdz czy wszystkie dodatnie
            if(width < 0 || height < 0 || maxIter < 0 || zebras < 0 || elephants < 0 || lions < 0 ){
                allValuesPositive = false;
                throw new ArithmeticException("\nAll numbers must be positive");
            }
        } catch (ArgumentParserException e) {
            e.printStackTrace();
        }
        Safari safari = new Safari(width, height);
        SafariMapCreator.placeRandomSafariObjects(elephants, zebras, lions, map);

        //----------------------------------------------------------rozpocznij symulacje-----------------------------------------------------------------------------------
        //tylko wtedy gdy nie zostały wykonane jeszcze wszystkie iteracje, są jeszcze zwierzęta na safari i początkowe wartości wprowadzone przez uzytkownika sa dodatnie
        for(int i = 0; i < maxIter && map.getAnimalAmount()>0 && allValuesPositive; i++){
            System.out.println("################################ Iteration: " + (i+1) +  " ################################");
            safari.putHumanOnSafari();

            //zapisz stan poczatkowy
            if(i == 0){
                fileManager.saveToReportArray(map.getAllAnimalsAndHumans(), i);
            }

            //zwierzeta
            IActionable currentSafariObject;
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
                (new Human(positionForHuman, map)).makeAction();
                //czlowiek chce zapolowac na lwa
                //map.getMap().get(positionForHuman).makeAction();
            }
        }
    }
}


