/**
 * A leaf node representing a PowerPoint (pptx) presentation.
 */
public class PptxFileDetails extends FileDetails {
    private final int slides;
    private final int size;

    public PptxFileDetails(String path, String fileName, int slides, int size) {
        super(path, fileName);
        this.slides = slides;
        this.size = size;
    }

    public int getSlides() {
        return slides;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(FileDetailsVisitor visitor) {
        visitor.visit(this);
    }
}
