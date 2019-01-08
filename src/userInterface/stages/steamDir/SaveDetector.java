package userInterface.stages.steamDir;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class SaveDetector {

    private DirectoryChooser directoryChooser;
    private File steamIdDir;
    private Stage stage;
    private Path gameSaveLoc;
    private Path gameSaveDir;
    private boolean dirSet = false;
    private SteamDirProperties sdProps;


    public SaveDetector(Stage stage) {
        this.stage = stage;

        directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Path to SteamID folder");

        this.gameSaveLoc = FileSystems.getDefault().getPath("582010", "remote", "SAVEDATA1000");
        sdProps = new SteamDirProperties();

        try {
            sdProps.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        steamIdDir = directoryChooser.showDialog(stage);
        dirSet = true;
        System.out.println(steamIdDir);
    }

    private File getSteamIdDir() {
        return steamIdDir;
    }

    /**
     * resolves path given by user against MHW save file location
     *
     * @return true if savefile exists, otherwise false;
     */
    private boolean savePathResolve() {
        if (dirSet) {
            Path providedPath = getSteamIdDir().toPath();
            gameSaveDir = providedPath.resolve(gameSaveLoc);
            //return true if file exists
            return Files.exists(gameSaveDir);
        } else {
            return false;
        }

    }

    Path getGameSaveDirProp() {
        String saveDir = sdProps.retrieveProp(SteamDirProperties.saveDir);

        if (!saveDir.isEmpty()) {
            return Paths.get(saveDir);
        } else {
            return null;
        }
    }

    Path getGameSaveDir() {
        if (savePathResolve()) {
            sdProps.setProperty(SteamDirProperties.saveDir, gameSaveDir.toString());
            System.out.println(sdProps.retrieveProp(SteamDirProperties.saveDir));
            try {
                sdProps.saveFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return gameSaveDir;
        } else {
            return null;
        }
    }
}



