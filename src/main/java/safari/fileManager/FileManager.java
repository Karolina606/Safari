package safari.fileManager;

import safari.safariObjects.IActionable;
import safari.safariObjects.animals.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class handles with files in the program
 * Helps in saving information about objects
 */
public class FileManager implements IFileManager{
    /**
     * Stores information about each simulation to save it later to the file
     */
    private final ArrayList<String> toReport = new ArrayList<>();

    /**
     * Saves information about Animals in iteration to array
     * @param list list of IActionable where we are looking for all Animals
     * @param iteration number of iteration in simulation
     */
    public void saveToReportArray(List<IActionable> list, int iteration){
        toReport.add("Iteration: " + ((Integer) iteration).toString());

        toReport.add("class, id, energyLevel, isAsleep, sleepTime, position, population");
        for(IActionable object: list){
            if(object instanceof Animal){
                toReport.add(((Animal) object).animalToFile());
            }
        }
        toReport.add("");
    }

    /**
     * Writes saved information about animals to file
     * @param fileName file path or name
     */
    public void writeToFile(String fileName){
        Path path = Paths.get(fileName);
        try {
            Files.write(path, toReport);
        } catch (IOException ex) {
            System.out.println("Nie mogę zapisać pliku!");
        }
    }
}
