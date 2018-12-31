package backupHandler;

import java.io.IOException;
import java.nio.file.*;
import java.util.Calendar;

public class FileBackup {


    private Path srcPath;
    private Path backupPath;

    /**
     * Creates new object responsible for making backups of given file in given directory
     */
    public FileBackup(Path backupPath, Path srcPath){
        this.backupPath = FileSystems.getDefault().getPath("");
        this.srcPath = srcPath;
    }

    public FileBackup(){
        this(null, null);
    }


    /**
     * Creates new backup of file given in srcPath field, backup is located in directory given in backupPath field
     * Backups are stored in folders named with date of their creation
     * Before using this method set source and target paths using setters
     */
    public void backup() throws IOException{

        if(this.srcPath == null || this.backupPath == null){
            throw new NullPointerException("SourcePath or BackupPath not set");
        }

        backupPath = backupPath.resolve(cDate());

        System.out.println(backupPath);

        Files.createDirectory(backupPath);

        if(Files.exists(backupPath)) {
            try {
                Files.copy(srcPath, backupPath.resolve(srcPath.getFileName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @return String representing date of calling this method
     */
    public String cDate () {

        Calendar date = Calendar.getInstance();
        String cDate = "" + date.get(Calendar.DAY_OF_MONTH) + "-" +
                date.get(Calendar.MONTH) + "-" +
                date.get(Calendar.YEAR) + "_" +
                date.get(Calendar.HOUR) + "-" +
                date.get(Calendar.MINUTE) + "-" +
                date.get(Calendar.SECOND);
        return cDate;
    }

    public void setSrcPath(Path srcPath) {
        this.srcPath = srcPath;
    }

    public void setBackupPath(Path backupPath) {
        this.backupPath = backupPath;
    }
}
