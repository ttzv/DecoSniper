package backupHandler;

import utility.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Calendar;

public class FileBackup {

    private File saveFile;

    private Path srcFile;
    private Path backupPath;

    /**
     * Creates new object responsible for making backups of given file in given directory
     * @param srcFile file to backup
     * @param backupPath directory for saving backups
     */
    public FileBackup(Path srcFile, Path backupPath){
        this.backupPath = backupPath;
        this.srcFile = srcFile;
        this.saveFile = new File(srcFile.toUri());
    }

    public FileBackup(){

    }

    /**
     * Creates new backup of file given in FileBackup constructor, backup is located in directory also given in constructor
     * Backups are stored in folders named with date of their creation
     */
    public void backup(){

        backupPath = backupPath.resolve(cDate());

        System.out.println(backupPath);

        File target = new File(backupPath.toString());

        if(target.mkdir()) {
            try {
                Files.copy(srcFile, backupPath.resolve(srcFile.getFileName()));
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

}
