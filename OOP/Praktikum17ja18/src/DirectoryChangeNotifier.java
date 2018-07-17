import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.nio.file.*;

public class DirectoryChangeNotifier {
    public static void main(String[] args) {
        Path currentDirPath = Paths.get(".");
        try {
            WatchService watcher =
                    currentDirPath.getFileSystem().newWatchService();
            currentDirPath.
                    register(watcher,
                            StandardWatchEventKinds.ENTRY_CREATE);
            System.out.println("Watching the current directory: "
                    + currentDirPath.toAbsolutePath());
            while (true) {
                WatchKey watchKey = watcher.take();
                List<WatchEvent<?>> events = watchKey.pollEvents();
                for (WatchEvent<?> event : events) {
                    Path path = ((WatchEvent<Path>) event).context();
                    System.out.println(
                            "The following file was created: '"
                                    + path + "'.");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            throw new RuntimeException(e);
        }
    }
}