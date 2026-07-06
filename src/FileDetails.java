/**
 * Base class of the Composite hierarchy used to represent both files and
 * directories in the simulated file system.
 * <p>
 * {@code FileDetails} plays the role of the <b>Component</b> in the Composite
 * design pattern: every concrete file type (mp3, jpg, html, txt, pptx, docx)
 * as well as {@link DirectoryDetails} (the Composite/container) extend it,
 * so client code can treat single files and whole directory trees uniformly.
 * <p>
 * It also declares the {@code accept} method required by the Visitor design
 * pattern, so that operations on the tree (counting files, printing,
 * calculating sizes, gathering statistics...) can be added without touching
 * this hierarchy again.
 */
public abstract class FileDetails {
    protected String name;
    private String path;

    public FileDetails(String path, String fileName) {
        this.path = path == null ? "" : path.substring(0, path.length() - 1);
        this.name = fileName;
    }

    public String getName() {
        return name;
    }

    /**
     * @return the full path of this element (path + name), used as the key
     *         under which it is registered while the tree is being built.
     */
    public String getFullName() {
        return (path.isEmpty() ? "" : path + "/") + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    /**
     * Entry point of the Visitor pattern: every concrete subclass forwards
     * the call to the matching {@code visit} overload on the given visitor.
     *
     * @param visitor the operation to run on this element
     */
    public abstract void accept(FileDetailsVisitor visitor);
}
