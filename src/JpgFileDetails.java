/**
 * A leaf node representing a jpg image file.
 */
public class JpgFileDetails extends FileDetails {
    private final int width;
    private final int height;
    private final int size;

    public JpgFileDetails(String path, String fileName, int width, int height, int size) {
        super(path, fileName);
        this.width = width;
        this.height = height;
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(FileDetailsVisitor visitor) {
        visitor.visit(this);
    }
}
