import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
​
public class FailureNotifier {
    public static void main(String[] args) throws IOException {
        Path currentDirPath = Paths.get(args[0]);
        List<String> filesWithFail = new ArrayList<>();
        try {
            WatchService watcher =
                    currentDirPath.getFileSystem().newWatchService();
            currentDirPath.
                    register(watcher,
                            StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("Watching the current directory: "
                    + currentDirPath.toAbsolutePath());
            WatchKey watchKey = watcher.take();
            while (true) {
                List<WatchEvent<?>> events = watchKey.pollEvents();
                for (WatchEvent<?> event : events) {
                    System.out.println(
                            "The following file was created: '"
                                    + event.context() + "'.");
                    Path currentFile = Paths.get(currentDirPath.toString(), event.context().toString());
                    if (currentFile.toString().endsWith(".txt")) {
                        List<String> content = Files.readAllLines(currentFile, StandardCharsets.UTF_8);
                        if (!filesWithFail.contains(event.context().toString())) {
                            filesWithFail.add(event.context().toString());
                            for (String s : content) {
                                if (s.toLowerCase().equals("failure")) {
                                    System.out.println("Found an error in file " + event.context() + "!");
                                }
                            }
                        }​
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            throw new RuntimeException(e);
        }
    }
}