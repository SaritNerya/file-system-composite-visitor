/**
 * A leaf node representing an mp3 audio file.
 */
public class Mp3FileDetails extends FileDetails {
    private final int lengthSec;
    private final int size;

    public Mp3FileDetails(String path, String fileName, int lengthSec, int size) {
        super(path, fileName);
        this.lengthSec = lengthSec;
        this.size = size;
    }

    public int getLengthSec() {
        return lengthSec;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(FileDetailsVisitor visitor) {
        visitor.visit(this);
    }
}
