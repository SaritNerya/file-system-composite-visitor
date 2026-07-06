import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Interactive console demo for the File System simulation.
 * <p>
 * Reads {@code src/files.txt}, builds the Composite tree via
 * {@link FileDetailsFactory}, and lets the user run any of the four
 * Visitor operations on it (count / statistics / short print / total size).
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        fileMenu(scanner);
    }

    /**
     * Reads the description file and builds the Composite tree, using a map
     * from full path to the already-created {@link FileDetails} so that each
     * new element can be attached to its parent directory as soon as it is
     * parsed.
     */
    public static FileDetails readFileDetails(String path) throws IOException {
        Map<String, FileDetails> files = new HashMap<>();
        DirectoryDetails root = new DirectoryDetails(null, "root");
        files.put("", root);
        Files.lines(Paths.get(path))
                .map(FileDetailsFactory::getFileDetails)
                .peek(f -> files.put(f.getFullName(), f))
                .peek(f -> ((DirectoryDetails) files.get(f.getPath())).addFile(f))
                .collect(Collectors.toList());
        return root;
    }

    public static void fileMenu(Scanner scanner) throws IOException {
        String path = "files.txt";
        FileDetails root = readFileDetails(path);
        System.out.println("Choose from the following options:\n" +
                "q: quit\n" +
                "c: countFiles\n" +
                "st: statistics\n" +
                "sh: short\n" +
                "sz: size");
        String myString;
        while (!(myString = scanner.nextLine()).equals("q")) {
            switch (myString) {
                case "c":
                    FileCountVisitor statsVisitorC = new FileCountVisitor();
                    root.accept(statsVisitorC);
                    System.out.println("Found " + statsVisitorC.getCount() + " files");
                    break;
                case "sz":
                    SizeCalculatorVisitor statsVisitorSZ = new SizeCalculatorVisitor();
                    root.accept(statsVisitorSZ);
                    System.out.println("the total size is " + statsVisitorSZ.getSizeAll() + " bytes");
                    break;
                case "st":
                    StatisticsVisitor statsVisitorST = new StatisticsVisitor();
                    root.accept(statsVisitorST);
                    break;
                case "sh":
                    ShortPrintVisitor statsVisitorSH = new ShortPrintVisitor();
                    root.accept(statsVisitorSH);
                    break;
                default:
                    System.out.println("Unknown option: " + myString);
            }
            System.out.println("Choose from the following options:\n" +
                    "q: quit\n" +
                    "c: countFiles\n" +
                    "st: statistics\n" +
                    "sh: short\n" +
                    "sz: size");
        }
    }
}
