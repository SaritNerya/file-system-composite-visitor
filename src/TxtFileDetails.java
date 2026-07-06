/**
 * A leaf node representing a plain text file.
 */
public class TxtFileDetails extends FileDetails {
    private final int words;
    private final int size;

    public TxtFileDetails(String path, String fileName, int words, int size) {
        super(path, fileName);
        this.words = words;
        this.size = size;
    }

    public int getWords() {
        return words;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(FileDetailsVisitor visitor) {
        visitor.visit(this);
    }
}
