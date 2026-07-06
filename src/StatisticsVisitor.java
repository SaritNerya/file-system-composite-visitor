/**
 * A concrete Visitor that prints a per-type statistic for every file, and a
 * recursive leaf-file count for every directory. All averages are rounded
 * to the nearest integer.
 */
public class StatisticsVisitor implements FileDetailsVisitor {

    @Override
    public void visit(DirectoryDetails directoryDetails) {
        for (FileDetails file : directoryDetails.getFiles()) {
            file.accept(this);
        }

        FileCountVisitor countVisitor = new FileCountVisitor();
        directoryDetails.accept(countVisitor);
        System.out.println("Directory " + directoryDetails.getName() + " has " + countVisitor.getCount() + " files.");
    }

    @Override
    public void visit(Mp3FileDetails mp3FileDetails) {
        long bitrate = Math.round((double) mp3FileDetails.getSize() / mp3FileDetails.getLengthSec());
        System.out.println("The bitrate of " + mp3FileDetails.getName() + " is " + bitrate + " bytes per second.");
    }

    @Override
    public void visit(JpgFileDetails jpgFileDetails) {
        long pixels = (long) jpgFileDetails.getWidth() * jpgFileDetails.getHeight();
        long avgBytesPerPixel = Math.round((double) jpgFileDetails.getSize() / pixels);
        System.out.println("The picture " + jpgFileDetails.getName() + " has an average of " + avgBytesPerPixel + " bytes per pixel.");
    }

    @Override
    public void visit(HtmlFileDetails htmlFileDetails) {
        System.out.println("The file " + htmlFileDetails.getName() + " contains " + htmlFileDetails.getLines() + " lines.");
    }

    @Override
    public void visit(TxtFileDetails txtFileDetails) {
        System.out.println("The file " + txtFileDetails.getName() + " contains " + txtFileDetails.getWords() + " words.");
    }

    @Override
    public void visit(PptxFileDetails pptxFileDetails) {
        long avgSlideSize = Math.round((double) pptxFileDetails.getSize() / pptxFileDetails.getSlides());
        System.out.println("The average slide size in Presentation " + pptxFileDetails.getName() + " is " + avgSlideSize + ".");
    }

    @Override
    public void visit(DocxFileDetails docxFileDetails) {
        long avgWordsPerPage = Math.round((double) docxFileDetails.getWords() / docxFileDetails.getPages());
        System.out.println("The file " + docxFileDetails.getName() + " has an average of " + avgWordsPerPage + " words per page.");
    }
}
