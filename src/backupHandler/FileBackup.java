package backupHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Calendar;

public class FileBackup {

    private File saveFile;

    private Path srcPath;
    private Path backupPath;

    /**
     * Creates new object responsible for making backups of given file in given directory
     */
    public FileBackup(){
        this.backupPath = null;
        this.srcPath = null;
        this.saveFile = null;
    }


    /**
     * Creates new backup of file given in srcPath field, backup is located in directory given in backupPath field
     * Backups are stored in folders named with date of their creation
     * Before using this method set source and target paths using setters
     */
    public void backup(){

        if(this.srcPath == null || this.backupPath == null){
            throw new NullPointerException("SourcePath or BackupPath not set");
        }

        backupPath = backupPath.resolve(cDate());

        System.out.println(backupPath);

        File target = new File(backupPath.toString());

        if(target.mkdir()) {
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
        this.saveFile = new File(srcPath.toUri());
    }

    public void setBackupPath(Path backupPath) {
        this.backupPath = backupPath;
    }
}
