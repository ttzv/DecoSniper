package userInterface.stages.steamDir;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveDetector {

    private DirectoryChooser directoryChooser;
    private File steamIdDir;
    private Stage primaryStage;
    private Path gameSaveDir;
    private boolean dirSet = false;


    public SaveDetector(Stage primaryStage) {
        this.primaryStage = primaryStage;

        directoryChooser  = new DirectoryChooser();
        directoryChooser.setTitle("Path to SteamID folder");

        this.gameSaveDir = FileSystems.getDefault().getPath("582010", "remote", "SAVEDATA1000");
        System.out.println(gameSaveDir);

    }

    public void show(){
        steamIdDir = directoryChooser.showDialog(primaryStage);
        dirSet = true;
    }

    private File getSteamIdDir(){
        return steamIdDir;
    }

    /**
     * resolves path given by user against MHW save file location
     * @return true if savefile exists, otherwise false;
     */
    private boolean savePathResolve(){
        if(dirSet) {
            Path providedPath = getSteamIdDir().toPath();
            System.out.println(providedPath);
            gameSaveDir = providedPath.resolve(gameSaveDir);
            System.out.println(gameSaveDir);
            //return true if file exists
            return Files.exists(gameSaveDir);
        } else {
            return false;
        }

    }

    public Path getGameSaveDir() {
        if(savePathResolve())
            return gameSaveDir;
        else
            return null;
    }
}
