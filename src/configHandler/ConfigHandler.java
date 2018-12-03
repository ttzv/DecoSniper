package configHandler;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {

    private File cfgFile;
    private File defCfgFile;

    private Properties properties;
    private Properties defProps;

    /**
     * Creates ConfigHandler object, defines default path of storing config, prepares File objects for storings properties
     */
    public ConfigHandler(){

        String fileSeparator = System.getProperty("file.separator");
        String cfgFilePath = "cfg" + fileSeparator + "config.properties";
        String defCfgFilePath = "cfg" + fileSeparator + "default.properties";

        cfgFile = new File(cfgFilePath);
        defCfgFile = new File(defCfgFilePath);

        this.defProps = new Properties();
        this.properties = new Properties();
    }


    /**
     * Loads Properties object from given File
     * @param file File with stored Properties
     * @param properties Properties object used to store properties loaded from File
     * @throws IOException if File or Properties object doesn't exist
     */
    private void loadCfgFile(File file, Properties properties) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        properties.load(fis);
        fis.close();
    }

    /**
     * Saves given properties to a given File
     * @param file File used to store properties
     * @param properties Properties to save
     * @throws IOException
     */
    private void saveCfgFile(File file, Properties properties) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        properties.store(fos, "");
        fos.close();
    }

    /**
     * This method has no real use
     * @param properties
     */
    private void useProperties(Properties properties) {

    }

    /**
     * Method used for configuring default properties, this probably should be abstract. Use put() method to configure default properties file.
     * <pre>Example:
     * {@code defprops.put("KEY" ,"VALUE")}
     * </pre>
     */
    private void createDefaultProps() throws IOException {

        //Do not change line below
        saveCfgFile(defCfgFile, defProps);
    }

    /**
     * Default method used to detect, initialize and load Properties and corresponding Files. Put this at the start of your app.
     * @throws IOException if some Files are not found
     */
    public void firstRun() throws IOException {
        if((!defCfgFile.exists() || defCfgFile.length() == 0) && (!cfgFile.exists() || cfgFile.length() == 0)){
            defCfgFile.getParentFile().mkdir();
            defCfgFile.createNewFile();
            cfgFile.createNewFile();
            createDefaultProps();
            System.out.println("created default");
        }

        if(cfgFile.exists()){
            if(cfgFile.length() == 0){
                System.out.println(cfgFile.getName() + "is empty, loading defaults");
            } else {
               loadCfgFile(cfgFile, properties);
                System.out.println("Loaded custom props");
            }
        }
        if(!properties.isEmpty()){
            useProperties(properties);
            System.out.println("used custom props");
        } else if(defCfgFile.exists()){
            if(defCfgFile.length() != 0) {
                loadCfgFile(defCfgFile, defProps);
                useProperties(defProps);
                System.out.println("loaded and used def props");
            } else {
                System.out.println(defCfgFile.getName() + "is empty, loading defaults");
            }
        }
    }

    /**
     * Method for saving state of your custom Properties object (that received changes during the program operation). Preferably use this when app closes or on some button.
     * @throws IOException
     */
    public void saveCustomProperties() throws IOException {
        saveCfgFile(cfgFile, properties);
    }

    /**
     * @return custom Properties object
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Method for modifying or adding new custom properties
     * @param key key value (String)
     * @param val Value to store at given key (String)
     */
    public void setCustomProperty(String key, String val){
        properties.put(key, val);
    }

}
