import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FilesManagerJavaNio2 implements IFilesManager {
    @Override
    public void writeLines(String pathFile, List<String> fileLines,boolean append) {
        Path path = Paths.get(pathFile);
        try {
            if (append) {
                Files.write(path, fileLines, StandardOpenOption.APPEND);
            } else {
                Files.write(path, fileLines);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
