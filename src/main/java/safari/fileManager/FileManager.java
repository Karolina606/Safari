package safari.fileManager;

import safari.safariObjects.SafariObject;
import safari.safariObjects.animals.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements IFileManager{
    private ArrayList<String> toReport = new ArrayList();

    /**
     * Saves information about Animals in iteration to array
     * @param list list of Animals
     * @param iteration number of iteration in simulation
     */
    public void saveToReportArray(List<SafariObject> list, int iteration){
        toReport.add("Iteration: " + ((Integer) iteration).toString());

        toReport.add("class, id, energyLevel, isAsleep, sleepTime, position, population");
        for(SafariObject object: list){
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
