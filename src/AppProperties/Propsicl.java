package AppProperties;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Class Propsicl - short for Properties Init-Config-Load
 * Multipurpose class used for tasks related to properties
 *  */
public class Propsicl {

    /**
     * field with default properties, used only if main properties object is empty/
     */
    private Properties defaultProps;

    /**
     * field with properties used during program work, can store and load form this
     */
    private Properties props;

    /**
     * default path for saving properties
     */
    private Path propsPath;
    private String defPropsName;
    private String mainPropsName;

    public Propsicl(){

        defaultProps = new Properties();
        propsPath = FileSystems.getDefault().getPath("cfg");

        defPropsName = "default.props";
        mainPropsName = "main.props";

    }

    /**
     * Method used for checking if files with properties exists
     * THIS DOES NOT CREATE ANYTHING, ONLY CHECKS
     * @return boolean array where first index represents default props and second represents main props
     * for example, if array[0]==true then defProps.properties exists in given path, same with main props file
     */
    private boolean[] checkFilesExisting() throws IOException {
        boolean[] res = new boolean[2];

        Path resolvedPath;
        resolvedPath = propsPath.resolve(defPropsName);
        res[0] = Files.exists(resolvedPath);

        resolvedPath = propsPath.resolve(mainPropsName);
        res[1] = Files.exists(resolvedPath);

        return res;
    }

    private void createPropsDir() throws IOException{
        if(!Files.exists(propsPath)){
            Files.createDirectory(propsPath);
        }
    }

    public void createPropsFiles() throws IOException{
        createPropsDir();

        boolean[] res = checkFilesExisting();

        Path resolvedPath;
        resolvedPath = propsPath.resolve(defPropsName);
        if(!res[0]){
            Files.createFile(resolvedPath);
        }
        resolvedPath = propsPath.resolve(mainPropsName);
        if(!res[1]){
            Files.createFile(resolvedPath);
        }

    }
    public void defaultPropsVals(){
        //insert all PUTS here
        defaultProps.put(PDef.BackupDir, "test1");
        defaultProps.put(PDef.SteamIdDir, "test2");

    }

    public void loadProperties(Properties properties, Path path) throws IOException {
        properties.load(new FileInputStream(path.toFile()));
    }

    public void saveProperties(Properties properties, Path path) throws IOException{
        properties.store(new FileOutputStream(path.toFile()), this.getClass().toString());
    }

    public void saveProperties(Properties properties, Path path, String comment) throws IOException{
        properties.store(new FileOutputStream(path.toFile()), comment);
    }

    public void contentCheck(){
        //placeholder object for storing loaded non-default properties
        //load from default props file and store in default object
        try {
            loadProperties(defaultProps, propsPath.resolve(defPropsName));
        } catch (IOException io){
            io.printStackTrace();
        }

        //load from mainProps and store in placeholder object
        Properties placeholder = new Properties();

        try{
            loadProperties(placeholder, propsPath.resolve(mainPropsName));
        } catch (IOException io){
            io.printStackTrace();
        }

        //compare and decide which to operate on
        if( placeholder.keySet().size() < defaultProps.keySet().size()){
            this.props = new Properties(defaultProps);
        } else {
            this.props = new Properties(placeholder);
        }

        if(this.props.keySet().size() <= 0){
            throw new PropsNullException();
        }
    }

    public String retrieveProp(String key){
        props.getProperty(key);
    }














}
