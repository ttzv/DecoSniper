package userInterface.dirPicker;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class SaveDetector {

    private DirectoryChooser directoryChooser;
    private File steamIdDir;
    private Stage primaryStage;
    private Path gameSaveDir;


    public SaveDetector(Stage primaryStage) {
        this.primaryStage = primaryStage;

        directoryChooser  = new DirectoryChooser();
        directoryChooser.setTitle("Path to SteamID folder");

        this.gameSaveDir = FileSystems.getDefault().getPath("582010", "SAVEDATA1000");
        System.out.println(gameSaveDir);

    }

    public void show(){
        steamIdDir = directoryChooser.showDialog(primaryStage);
    }

    private File getSteamIdDir(){
        return steamIdDir;
    }

    /**
     * resolves path given by user against MHW save file location
     * @return true if savefile exists, otherwise false;
     */
    private boolean savePathResolve(){
        Path providedPath = getSteamIdDir().toPath();
        System.out.println(providedPath);
        gameSaveDir = providedPath.resolve(gameSaveDir);
        System.out.println(gameSaveDir);
        File saveFile = new File (gameSaveDir.toUri());
        if(saveFile.exists())
            return true;
        else
            return false;
    }

    public Path getGameSaveDir() {
        if(savePathResolve())
            return gameSaveDir;
        else
            return null;
    }
}
