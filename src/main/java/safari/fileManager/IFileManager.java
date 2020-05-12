package safari.fileManager;

import safari.safariObjects.SafariObject;

import java.util.List;

public interface IFileManager {
    void saveToReportArray(List<SafariObject> list, int iteration);

    void writeToFile(String fileName);
}
