package AppProperties;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Properties;

/**
 * Class Propsicl - short for Properties Init-Config-Load
 * Multipurpose class used for tasks related to properties
 *  */
public abstract class Propsicl {

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
    private boolean modifiable;

    public Propsicl(){

        //false until create() method is called to prevent changing default properties during runtime
        modifiable = false;

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

    private void createPropsFiles() throws IOException{

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

    /**
     * Method used for defining default properties. Override this method at the start of application when object extending propsicl is created but before <i>init()</i> method.
     * To define property use <pre>
     * @code
     * </pre>
     */
    public abstract void defaultPropsVals();/*{
        ///insert all PUTS here

            defaultProps.put(PDef.BackupDir, "test1");
            defaultProps.put(PDef.SteamIdDir, "test2");

            }*/

    public void defPropSet(String key, String val){
        if(!modifiable) {
            defaultProps.put(key, val);
        } else {
            System.out.println("Cannot modify default properties after create() method was called, modify defaults properly by overriding defaultPropsVals() method of Propsicl");
        }
    }

    public void setProperty(String key, String val){
        if(modifiable) {
            props.put(key, val);
        } else {
            System.out.println("properties object not initialized, use init() method before making any changes");
        }
    }

    private void loadProperties(Properties properties, Path path) throws IOException{
        FileInputStream fis = new FileInputStream(path.toFile());
        properties.load(fis);
        fis.close();
    }

    /**
     * Save without comment
     * @param properties properties object to save
     * @param path location to save in
     * @throws IOException
     */
    private void saveProperties(Properties properties, Path path) throws IOException{
        FileOutputStream fos = new FileOutputStream(path.toFile());
        properties.store(fos, this.getClass().toString());
        fos.close();
    }

    private void saveProperties(Properties properties, Path path, String comment) throws IOException{
        FileOutputStream fos = new FileOutputStream(path.toFile());
        properties.store(fos, comment);
        fos.close();
    }


    /**
     * Loads defaults form default.props file and main props from main.props file, initializes main properties object with defaults list and populates it with vaules red from main.props.
     */
    private void create(){
        //load from default props file and store in default object
        try {
            loadProperties(defaultProps, propsPath.resolve(defPropsName));
        } catch (IOException io){
            io.printStackTrace();
        }

        //load from main props and store in main object
        this.props = new Properties(defaultProps);
        try{
            loadProperties(props, propsPath.resolve(mainPropsName));
        } catch (IOException io){
            io.printStackTrace();
        }

        System.out.println("Default properties loaded: " + defaultProps.keySet().size());
        System.out.println("Properties loaded: " + props.keySet().size());


        modifiable = true;
    }



    /**
     * Retrieve property stored under given key in currently loaded properties, depending on whether main properties was found, otherwise returned property comes from default predefined properties.
     * If key is not found NULL is returned
     * @param key String value of key identificator, for ease of use apply static fields of Pdef class here
     * @return String value of property stored under given key or NULL if key was not found
     */
    public String retrieveProp(String key){

        return props.getProperty(key);

    }

    /**
     * Saves any modification of properties in main properties file
     * @throws IOException
     */
    public void saveFile() throws IOException {

        Calendar calendar = Calendar.getInstance(); //get date for comment
        saveProperties(props, propsPath.resolve(this.mainPropsName), "Date of saving: " + calendar.getTime().toString());
    }

    private void saveDefaultProps() throws IOException {

        Calendar calendar = Calendar.getInstance(); //get date for comment
        saveProperties(defaultProps, propsPath.resolve(this.defPropsName), "Date of saving: " + calendar.getTime().toString());

    }

    /**
     * Method that encapsulates creating directories, files, initialization of properties etc, use at the start of application.
     * @throws IOException
     */
    public void init() throws IOException {

        //Create directory tree
        this.createPropsDir();
        //Create .props files
        this.createPropsFiles();
        //put something in defaults and save it
        this.defaultPropsVals();
        this.saveDefaultProps();
        //load form files and create objects
        this.create();
    }

}
