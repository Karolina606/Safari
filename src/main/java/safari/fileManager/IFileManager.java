package safari.fileManager;

import safari.safariObjects.IActionable;

import java.util.List;

/**
 * Interface declares FileManager's required methods
 */
public interface IFileManager {
    /**
     * Adds information Objects from list to the Array
     * @param list List of Objects (especially SafariObjects) implementing IActionable
     * @param iteration integer number of iteration
     */
    void saveToReportArray(List<IActionable> list, int iteration);

    /**
     * Writes infromation to the file
     * @param fileName name of the file to write information to
     */
    void writeToFile(String fileName);
}
