package utility;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Utility {

    public static final String fileSeparator = System.getProperty("file.separator");

    public static final Path defBakPath = FileSystems.getDefault().getPath("backup");

}
