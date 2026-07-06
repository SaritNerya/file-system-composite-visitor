/**
 * A concrete Visitor that counts the number of leaf files (not directories)
 * in the tree rooted at the element it is applied to.
 */
public class FileCountVisitor implements FileDetailsVisitor {

    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void visit(DirectoryDetails directoryDetails) {
        for (FileDetails file : directoryDetails.getFiles()) {
            file.accept(this);
        }
    }

    @Override
    public void visit(Mp3FileDetails mp3FileDetails) {
        count++;
    }

    @Override
    public void visit(JpgFileDetails jpgFileDetails) {
        count++;
    }

    @Override
    public void visit(HtmlFileDetails htmlFileDetails) {
        count++;
    }

    @Override
    public void visit(TxtFileDetails txtFileDetails) {
        count++;
    }

    @Override
    public void visit(PptxFileDetails pptxFileDetails) {
        count++;
    }

    @Override
    public void visit(DocxFileDetails docxFileDetails) {
        count++;
    }
}
