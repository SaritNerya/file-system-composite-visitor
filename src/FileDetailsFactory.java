import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Factory that parses a single line of the {@code files.txt} description
 * format and builds the matching {@link FileDetails} instance (file or
 * directory).
 * <p>
 * <b>Note:</b> this class was supplied by the course staff as part of the
 * exercise skeleton and is included here unmodified (aside from removing a
 * leftover local debug snippet) so that the project builds and runs as a
 * complete, working sample.
 */
public class FileDetailsFactory {

    private static final String EXTENTION_CAPTURE_PATTERN = "^[^ .]*(\\.[^ ]*)?";
    private static final String MP3_CAPTURE_PATTERN = "(.*/)?([^ /]*) length in seconds: (\\d+), (\\d+) bytes";
    private static final String JPG_CAPTURE_PATTERN = "(.*/)?([^ /]*) (\\d+)x(\\d+), (\\d+) bytes";
    private static final String HTML_CAPTURE_PATTERN = "(.*/)?([^ /]*) lines: (\\d+), (\\d+) bytes";
    private static final String TXT_CAPTURE_PATTERN = "(.*/)?([^ /]*) words: (\\d+), (\\d+) bytes";
    private static final String PPTX_CAPTURE_PATTERN = "(.*/)?([^ /]*) slides: (\\d+), (\\d+) bytes";
    private static final String DOCX_CAPTURE_PATTERN = "(.*/)?([^ /]*) words: (\\d+), pages: (\\d+), (\\d+) bytes";
    private static final String DIRECTORY_CAPTURE_PATTERN = "(.*/)?([^ /]*)";

    public static FileDetails getFileDetails(String description) {
        Matcher matcher;
        switch (getExtension(description)) {
            case "":
                matcher = getMatcher(DIRECTORY_CAPTURE_PATTERN, description);
                return new DirectoryDetails(matcher.group(1), matcher.group(2));
            case "mp3":
                matcher = getMatcher(MP3_CAPTURE_PATTERN, description);
                return new Mp3FileDetails(matcher.group(1), matcher.group(2),
                        Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
            case "jpg":
                matcher = getMatcher(JPG_CAPTURE_PATTERN, description);
                return new JpgFileDetails(matcher.group(1), matcher.group(2),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4)),
                        Integer.parseInt(matcher.group(5)));
            case "html":
                matcher = getMatcher(HTML_CAPTURE_PATTERN, description);
                return new HtmlFileDetails(matcher.group(1), matcher.group(2),
                        Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
            case "pptx":
                matcher = getMatcher(PPTX_CAPTURE_PATTERN, description);
                return new PptxFileDetails(matcher.group(1), matcher.group(2),
                        Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
            case "txt":
                matcher = getMatcher(TXT_CAPTURE_PATTERN, description);
                return new TxtFileDetails(matcher.group(1), matcher.group(2),
                        Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
            case "docx":
                matcher = getMatcher(DOCX_CAPTURE_PATTERN, description);
                return new DocxFileDetails(matcher.group(1), matcher.group(2),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4)),
                        Integer.parseInt(matcher.group(5)));
            default:
                throw new RuntimeException("wrong file type");
        }
    }

    public static String getExtension(String fileDescription) {
        String extention = getMatcher(EXTENTION_CAPTURE_PATTERN, fileDescription).group(1);
        return extention == null ? "" : extention.substring(1);
    }

    public static Matcher getMatcher(String regex, String toMatch) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toMatch);
        matcher.find();
        return matcher;
    }
}
