import java.util.ArrayList;
import java.util.List;

/**
 * The <b>Composite</b> of the design pattern: a directory that can hold any
 * number of children, which may themselves be files or nested directories.
 */
public class DirectoryDetails extends FileDetails {
    private final List<FileDetails> files = new ArrayList<>();

    public DirectoryDetails(String path, String name) {
        super(path, name);
    }

    /**
     * Adds a child (file or sub-directory) to this directory.
     */
    public void addFile(FileDetails fileDetails) {
        this.files.add(fileDetails);
    }

    public List<FileDetails> getFiles() {
        return files;
    }

    @Override
    public void accept(FileDetailsVisitor visitor) {
        visitor.visit(this);
    }
}
