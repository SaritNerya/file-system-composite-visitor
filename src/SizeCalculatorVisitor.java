/**
 * A concrete Visitor that sums up the size (in bytes) of every file in the
 * tree rooted at the element it is applied to.
 */
public class SizeCalculatorVisitor implements FileDetailsVisitor {

    private int sizeAll = 0;

    public int getSizeAll() {
        return sizeAll;
    }

    @Override
    public void visit(DirectoryDetails directoryDetails) {
        for (FileDetails file : directoryDetails.getFiles()) {
            file.accept(this);
        }
    }

    @Override
    public void visit(Mp3FileDetails mp3FileDetails) {
        sizeAll += mp3FileDetails.getSize();
    }

    @Override
    public void visit(JpgFileDetails jpgFileDetails) {
        sizeAll += jpgFileDetails.getSize();
    }

    @Override
    public void visit(HtmlFileDetails htmlFileDetails) {
        sizeAll += htmlFileDetails.getSize();
    }

    @Override
    public void visit(TxtFileDetails txtFileDetails) {
        sizeAll += txtFileDetails.getSize();
    }

    @Override
    public void visit(PptxFileDetails pptxFileDetails) {
        sizeAll += pptxFileDetails.getSize();
    }

    @Override
    public void visit(DocxFileDetails docxFileDetails) {
        sizeAll += docxFileDetails.getSize();
    }
}
