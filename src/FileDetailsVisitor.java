/**
 * The Visitor interface of the Visitor design pattern.
 * <p>
 * Each new operation on the {@link FileDetails} tree (counting files,
 * short-printing, size calculation, statistics...) is implemented as a
 * separate class implementing this interface, instead of being added
 * directly to the file/directory classes. This keeps the element classes
 * focused purely on data, while operations can be added freely without
 * modifying them.
 */
public interface FileDetailsVisitor {
    void visit(DirectoryDetails directoryDetails);
    void visit(Mp3FileDetails mp3FileDetails);
    void visit(JpgFileDetails jpgFileDetails);
    void visit(HtmlFileDetails htmlFileDetails);
    void visit(TxtFileDetails txtFileDetails);
    void visit(PptxFileDetails pptxFileDetails);
    void visit(DocxFileDetails docxFileDetails);
}
