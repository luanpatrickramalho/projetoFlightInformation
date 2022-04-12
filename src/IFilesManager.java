import java.util.List;

public interface IFilesManager {
    void writeLines(String pathFile, List<String> fileLine,boolean append);
}